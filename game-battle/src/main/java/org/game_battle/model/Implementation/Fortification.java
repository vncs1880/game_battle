package org.game_battle.model.Implementation;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.game_battle.view.UI;

public class Fortification {
	
	private static final Logger LOG = LogManager.getLogger(Player.class);

	
	
	public void Fortification(List<Country> countries, Board board , String playerName) {
		
		for (Country country : countries) {
			List<Country> neighbours = new CopyOnWriteArrayList<>(country.getNeighbours());
			for (Country neighbour : neighbours) {
				if ((board.getOwner(country) != board.getOwner(neighbour))
						|| (country.getName().equals(neighbour.getName()))) {
					neighbours.remove(neighbour);
				}
			}
			LOG.info("Elligible territory neighbours owned by "+board.getOwner(country).getName()+": "+neighbours);
			if (country.getArmies() > 0 && neighbours.size() > 0) {
				Country selected = UI.selectCountry("Fortification phase",
						"Want to move armies from " + country + " to a neighbour?", neighbours);
				if (selected != null && country.getArmies()>0) {
					int n_armies = UI.askNumber("Fortification phase",
							"How many armies from " + country + " to " + selected, 0, country.getArmies());
					country.setArmyQty(country.getArmies() - n_armies);
					LOG.info("Player " + playerName + " moved " + n_armies + " army from " + country.getName() + " to "
							+ selected.getName() + " previous army qty was " + selected.getArmies());
					selected.setArmyQty(selected.getArmies() + n_armies);//DONE bug here. not really updating selected
																			// army qty
					break;
				}
			} else {
				LOG.info(country.getName() + ": No armies or no neighbours to move around.");
			}
		}

		/*
		 * Once the move is made or the player forfeits his fortification phase, the
		 * player’s turn ends and it is now the next player’s turn.
		 */

	}

}

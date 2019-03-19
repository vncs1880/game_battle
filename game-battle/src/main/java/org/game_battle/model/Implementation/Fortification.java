package org.game_battle.model.Implementation;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.game_battle.view.UI;

public class Fortification {
	
	private static final Logger LOG = LogManager.getLogger(Player.class);

	
	
	public void Fortification(List<Country> countries, Board board , String playerName) {
		/*
		 * Once he declares that he will not attack anymore (or cannot attack because
		 * none of his countries that have an adjacent country controlled by another
		 * player is containing more than one army), the fortification phase begins. In
		 * the fortification phase, the player may move any number of armies from one of
		 * his owed countries to the other, provided that there is a path between these
		 * two countries that is composed of countries that he owns. Only one such move
		 * is allowed per fortification phase. 
		 * 
		 */

		// For each country in Player.getCountryList():
		// If coutry.hasArmies() AND GameBoard.areConnected(country, defeatedCountry):
//			Z = UI.ask(“Move how many armies from X to Y? [0 to country.getArmiesNumber()]”)
//			country.setArmiesNumber(country.getArmiesNumber() - Z)
//			defeatedCountry.setArmiesNumber(Z)	
		// break FOR //only 1 move is allowed

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

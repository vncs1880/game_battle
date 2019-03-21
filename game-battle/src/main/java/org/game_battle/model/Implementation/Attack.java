/*package org.game_battle.model.Implementation;



import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game_battle.view.UI;

public class Attack {
	private static final Logger LOG = LogManager.getLogger(Player.class);

	
	
	public void Attack(int MINIMUM_ARMIES_TO_QUALIFY_FOR_ATTACK , Player player, Board board) {
		
		 * Once all the reinforcement armies have been placed by the player, the attacks
		 * phase begins. In the attack phase, the player may choose one of the countries
		 * he owns that contains two or more armies, and declare an attack on an
		 * adjacent country that is owned by another player.
		 
		// elligibleAttackerCountries = Player.getCountries(Country.getArmiesCount() >
		// 2)

		List<Country> elligibleAttackerCountries = player.getAttackerCountries(MINIMUM_ARMIES_TO_QUALIFY_FOR_ATTACK);
		// LOG.info("elligible Attacker Countries: " + elligibleAttackerCountries);
		if (!elligibleAttackerCountries.isEmpty()) {
			// OffendingCountry = elligibleAttackerCountries[UI.get_user_selection]
			// DeffendingCountry =
			// Board.getElligibleTargets(OffendingCountry)[UI.get_user_selection]
			//// elligible targets are adjacent nodes

			Country OffendingCountry = UI.selectCountry("Attack phase", "Select attacker country",
					elligibleAttackerCountries);
			List<Country> neighbours = new CopyOnWriteArrayList<>(OffendingCountry.getNeighbours());
			//LOG.info("neighbours before filtering: "+neighbours);
			neighbours.remove(OffendingCountry);
			for (Country country : neighbours) {
				if (board.getOwner(OffendingCountry) == board.getOwner(country)) {
					neighbours.remove(country);
				}
			}
			//LOG.info("neighbours after filtering: "+neighbours); //TODO BUILD2 currently showing adjacent, should look for connected 
			LOG.info("connected countries/elligible targets: " + neighbours);
			if (!neighbours.isEmpty()) {
				Country DeffendingCountry = UI.selectCountry("Attack phase", "Select target country",
						neighbours  board.getElligibleTargets(OffendingCountry) );

				// The attacker can choose to continue attacking until either all his armies or
				// all the defending armies have been eliminated.
				//
				// While (OffendingCountry.getTotalArmies() > 0) AND
				// (DeffendingCountry.getTotalArmies() > 0) do {
				// <<Board.Battle()>>
				// }
				LOG.info(OffendingCountry +" vs "+DeffendingCountry+" - Checking if enough armies in both attacker/target countries to allow attack...");
				String attacker = board.getOwner(OffendingCountry).getName();
				String deffender = board.getOwner(DeffendingCountry).getName();
				while (((OffendingCountry.getArmies() > 0) && (DeffendingCountry.getArmies() > 0))) {
					
					if (!UI.isUserOk("Attack phase", attacker + ", do you want to attack " + deffender + " ?")) {
						break;
					}
					LOG.info("Enough armies in both countries(>0). Starting Battle. " + attacker + " attacking " + deffender
							+ ".");
					// Board.Battle(OffendingCountry, DeffendingCountry)
					board.doBattle(OffendingCountry, DeffendingCountry);
					// If all the defender's armies are eliminated the attacker captures the
					// territory.
					//
					// Board.updateTerritories(DeffendingCountry)
					//// just change ownership if DeffendingCountry.getTotalArmies() == 0
					//
				}
				if (DeffendingCountry.getArmies() == 0) {
					board.giveLoserCountryToWinnerPlayer(OffendingCountry, DeffendingCountry);
					LOG.info("All the defender's armies are eliminated." + attacker + " captured " + DeffendingCountry);
					
					 * The attacking player must then place a number of armies in the conquered
					 * country which is greater or equal than the number of dice that was used in
					 * the attack that resulted in conquering the country. A player may do as many
					 * attacks as he wants during his turn.
					 
					if (player.getArmies()>0) {
						int minimumArmies = board.getLastDiceRollResult();
						int armies_to_occupy = UI.askNumber("Attack phase", "How many armies to occupy defeated country?",
								minimumArmies, player.getArmies());
						DeffendingCountry.setArmyQty(armies_to_occupy);
						LOG.info(attacker + " places " + armies_to_occupy + " armies in " + DeffendingCountry);
					} else LOG.info("no armies to occupy defeated country.");
					
				} else {
					LOG.info(attacker + " lost battle.");
				}
			} else {
				LOG.info("No elligible target countries.");
			}
			
		} else {
			LOG.info("No elligible attacker countries.");
		}
	}}


*/
/**
 * 
 */
package org.game_battle.gameplay;

import java.util.List;

/**
 * @author voda
 *
 */
public class Player {

	private int armies = 0;
	private Board board;
	private List<Country> countries;
	private List<Card> cards;

	/**
	 * @param board 
	 * 
	 */
	public Player(Board board) {
		// TODO this is not ok but...
		this.board = board;
	}

	public void setCountries(List<Country> list) {
		countries = list;
	}

	public void Reinforcement() {
		//In the reinforcements phase, the player is given a number of armies that depends on the number of countries he owns (# of countries owned divided by 3, rounded down). 
		//If the player owns all the countries of an entire continent, the player is given an amount of armies
		//corresponding to the continent’s control value. 
		//Player.resetNumberArmies
		this.setArmies(0);
		//For each Continent:
//			if allContinentBelongsTo(Player) 
//				Player.updateNumberArmies(NumberArmies(Continent.control_value))  
		////updateNumberArmies just accumulates the number 
//			else
//				totalCountriesOwnedByPlayer += Continent.CountriesOwnedBy(Player)
		////add accumulated countries after all continents are checked
		//Player.updateNumberArmies(RoundDown(totalCountriesOwnedByPlayer/3))
		//OR (needs elaboration tho)...
		//For each Continent
//			Continent.CalculateNumberArmies(Player)
		List<Continent> continents = board.getContinents();
		int totalCountriesOwnedInAllContinents = 0;
		int totalArmies = 0;
		for (Continent continent : continents) {
			int totalCountriesOwnedInThisContinent = 0;
			List<Country> countriesByContinent = board.getCountriesByContinent(continent);
			for (Country country : countriesByContinent) {
				if (countries.contains(country)) {
					totalCountriesOwnedInAllContinents += 1;
					totalCountriesOwnedInThisContinent += 1;
				}
			}
			if (totalCountriesOwnedInThisContinent == countriesByContinent.size()) {
				totalArmies  = continent.getControlValue();
				totalCountriesOwnedInAllContinents -= totalCountriesOwnedInThisContinent;
			}			
		}
		setArmiesQtyFromCountriesQty(totalCountriesOwnedInAllContinents, totalArmies);
		
		//Finally, if the player owns three cards of different sorts or the same sorts, he can exchange them for armies. 
		//
		//“Do you want to try to get armies from your cards?”
		//If YES:
		//Player.updateNumberArmies(Cards.getEligibleArmies(Player.getCards()))
		////if not eligible,   Cards.getEligibleArmies = 0
		if (UI.isUserOk("Do you wanna try to get armies from your cards?")) {
			setArmies(board.getArmiesFromCards(getCards()));
		}

		//Once the total number of reinforcements is determined for the player’s turn, the player may place the armies on any country he owns, divided as he wants. 
		//
		//For each Player.getCountries():
//			If totalArmiesOwnedByPlayer == 0 then break
//			N = user_input //if >  totalArmiesOwnedByPlayer, N = totalArmiesOwnedByPlayer
//			Country.setArmiesNumber(n)
		if (armies > 0) {
			for (Country country : countries) {
				int qtyArmies = UI.askNumber("How many armies do you want to put in country "+ country.toString() +" ?");
				if (qtyArmies  <= armies) {
					country.setArmyQty(qtyArmies);
				}
			}	
		}
	}

	private List<Card> getCards() {
		return cards;
	}

	private void setArmiesQtyFromCountriesQty(int totalCountriesOwned, int totalArmies) {
		// TODO make sure this is rounded down
		setArmies(totalCountriesOwned/3 + totalArmies);
	}

	private void setArmies(int i) {
		//
		//If totalArmiesOwnedByPlayer < 3:
//			totalArmiesOwnedByPlayer = 3
		//
		if (i < 3) {
			i = 3;
		}
		this.armies  = i;
	}

	public void Attack() {
		/*
		 * Once all the reinforcement armies have been placed by the player, the attacks
		 * phase begins. In the attack phase, the player may choose one of the countries
		 * he owns that contains two or more armies, and declare an attack on an
		 * adjacent country that is owned by another player.
		 */ 
		//elligibleAttackerCountries = Player.getCountries(Country.getArmiesCount() > 2)

		List<Country> elligibleAttackerCountries = getAttackerCountries(2);
		
		//A battle is then simulated by the attacker rolling at most 3 dice (which should not be more than the number of armies contained in the attacking country) and the defender rolling at most 2 dice (which should not be more than the number of armies contained in the attacking country). 
		//
		//OffendingCountry = elligibleAttackerCountries[UI.get_user_selection]
		//DeffendingCountry = Board.getElligibleTargets(OffendingCountry)[UI.get_user_selection] 
		////elligible targets are adjacent nodes
		
		Country OffendingCountry = UI.select("Select attacker country",elligibleAttackerCountries);
		Country DeffendingCountry = UI.select("Select target country", board.getElligibleTargets(OffendingCountry));
		
		//The attacker can choose to continue attacking until either all his armies or all the defending armies have been eliminated. 
		//
		//While (OffendingCountry.getTotalArmies() > 0) AND (DeffendingCountry.getTotalArmies() > 0) do {
		//<<Board.Battle()>>
		//}
		while (((OffendingCountry.getArmies() > 0) && (DeffendingCountry.getArmies() > 0))) {
			if (!UI.isUserOk("Want to attack?")) {
				break;
			}
			//Board.Battle(OffendingCountry, DeffendingCountry) 
			board.doBattle(OffendingCountry, DeffendingCountry);
		}

		//If all the defender's armies are eliminated the attacker captures the territory. 
		//
		//Board.updateTerritories(DeffendingCountry) 
		////just change ownership if DeffendingCountry.getTotalArmies() == 0
		//
		
		if (DeffendingCountry.getArmies() == 0) {
			board.giveLoserCountryToWinnerPlayer(OffendingCountry, DeffendingCountry);
		}
		/*
		 * The attacking player must then place a number of armies in the conquered
		 * country which is greater or equal than the number of dice that was used in
		 * the attack that resulted in conquering the country. A player may do as many
		 * attacks as he wants during his turn.
		 */ 
		//
		//MinimumArmies = Board.Battle.getLastRollDiceResult()
		
		int minimumArmies = board.getLastDiceRollResult();
		DeffendingCountry.setArmyQty(UI.askNumber("How many armies to occupy defeated country? Minimum is "+ minimumArmies+". Maximum is "+ armies));
		
		
	}

	private List<Country> getAttackerCountries(int i) {
		List<Country> attackers = null;
		
		for (Country country : countries) {
			if (country.getArmies() >= 2) {
				attackers.add(country);
			}
		}		
		return attackers;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void Fortification() {
		/*
		 * Once he declares that he will not attack anymore (or cannot attack because
		 * none of his countries that have an adjacent country controlled by another
		 * player is containing more than one army), the fortification phase begins. In
		 * the fortification phase, the player may move any number of armies from one of
		 * his owed countries to the other, provided that there is a path between these
		 * two countries that is composed of countries that he owns. Only one such move
		 * is allowed per fortification phase.
		 */

		//For each country in Player.getCountryList():
		//If coutry.hasArmies() AND GameBoard.areConnected(country, defeatedCountry):
//			Z = UI.ask(“Move how many armies from X to Y? [0 to country.getArmiesNumber()]”)
//			country.setArmiesNumber(country.getArmiesNumber() - Z)
//			defeatedCountry.setArmiesNumber(Z)	
		//break FOR //only 1 move is allowed

		for (Country country : countries) {
			List<Country> neighbours = country.getNeighbours();
			if (country.getArmies() > 0 && neighbours.size() > 0) {
				Country selected = UI.select("Want to move armies from "+ country +" to a neighbour?", neighbours);
				if (selected != null) {
					int n_armies = UI.askNumber("How many armies from "+country+" to "+selected);
					country.setArmyQty(country.getArmies()-n_armies);
					selected.setArmyQty(n_armies);
					break;
				}
			}
		}
		
		/*
		 * Once the move is made or the player forfeits his fortification phase, the
		 * player’s turn ends and it is now the next player’s turn. 
		 */

	}

}

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
		
		//Question : Do player need to play based on strategy or everything should happen in background with randomly generated value
		//
		//
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

}

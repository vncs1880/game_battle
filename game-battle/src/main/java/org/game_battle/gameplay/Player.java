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

	/**
	 * @param board2 
	 * 
	 */
	public Player(Board board) {
		// TODO this is not ok but...
		this.board = board;
	}

	public void assignCountries(List<Country> list) {
		// TODO Auto-generated method stub
		countries = list;
	}

	public void Reinforcement() {
		// TODO Auto-generated method stub
		//
		//In the reinforcements phase, the player is given a number of armies that depends on the number of countries he owns (# of countries owned divided by 3, rounded down). 
		//If the player owns all the countries of an entire continent, the player is given an amount of armies
		//corresponding to the continent’s control value. 
		//
		//Player.resetNumberArmies
		this.setArmies(0);
		//
		//For each Continent:
//			if allContinentBelongsTo(Player) 
//				Player.updateNumberArmies(NumberArmies(Continent.control_value))  
		////updateNumberArmies just accumulates the number 
//			else
//				totalCountriesOwnedByPlayer += Continent.CountriesOwnedBy(Player)
		////add accumulated countries after all continents are checked
		//Player.updateNumberArmies(RoundDown(totalCountriesOwnedByPlayer/3))
		//
		//OR (needs elaboration tho)...
		//
		//For each Continent
//			Continent.CalculateNumberArmies(Player)
		List<Continent> continents = board.getContinents();
		int totalCountriesOwned = 0;
		int totalArmies = 0;
		for (Continent continent : continents) {
			int totalCountriesInContinent = 0;
			List<Country> countriesByContinent = board.getCountriesByContinent(continent);
			for (Country country : countriesByContinent) {
				if (countries.contains(country)) {
					totalCountriesOwned += 1;
				}
			}
			if (totalCountriesInContinent == countriesByContinent.size()) {
				totalArmies  = continent.getControlValue();
			}
			totalCountriesOwned += totalCountriesInContinent;
		}
		setArmiesQtyFromCountriesQty(totalCountriesOwned, totalArmies);
		//
		//
		//Finally, if the player owns three cards of different sorts or the same sorts, he can exchange them for armies. 
		//
		//“Do you want to try to get armies from your cards?”
		//If YES:
		//Player.updateNumberArmies(Cards.getEligibleArmies(Player.getCards()))
		////if not elligible,   Cards.getEligibleArmies = 0
		////Cards.getEligibleArmies manages the counter to 5, 10, 15.. across players turns 
		////Cards is an utility class for now (maybe should be Board.Cards because Board HAS Cards).
		//
		//The number of armies a player will get for cards is first 5, then increases by 5 every time any player does so (i.e. 5, 10, 15, …). In any case, the minimal number of reinforcement armies is 3. 
		//
		//If totalArmiesOwnedByPlayer < 3:
//			totalArmiesOwnedByPlayer = 3
		//
		//Once the total number of reinforcements is determined for the player’s turn, the player may place the armies on any country he owns, divided as he wants. 
		//
		//For each Player.getCountries():
//			If totalArmiesOwnedByPlayer == 0 then break
//			N = user_input //if >  totalArmiesOwnedByPlayer, N = totalArmiesOwnedByPlayer
//			Country.setArmiesNumber(n)
		//
		//
		//Question : Do player need to play based on strategy or everything should happen in background with randomly generated value
		//
		//
	}

	private void setArmiesQtyFromCountriesQty(int totalCountriesOwned, int totalArmies) {
		// TODO make sure this is rounded down
		setArmies(totalCountriesOwned/3 + totalArmies);
	}

	private void setArmies(int i) {
		// TODO Auto-generated method stub
		this.armies  = i;
	}

}

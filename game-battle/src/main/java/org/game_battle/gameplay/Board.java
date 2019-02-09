/**
 * 
 */
package org.game_battle.gameplay;

import java.util.*;
import java.util.function.Predicate; 

/**
 * @author voda
 *
 */
public class Board {
	private List<Continent> continents;
	private List<List<Country>> countriesByContinent;
	/**
	 * @param continent 
	 * @return the countriesByContinent
	 */
	public List<Country> getCountriesByContinent(Continent continent) {
		return countriesByContinent.get(countriesByContinent.indexOf(continent));
	}

	private List<Player> players;
	private int cardsToGive;
	
	/**
	 * 
	 */
	public Board() {
		// TODO Auto-generated constructor stub
	}

	public void startup() {
		//players = Arrays.asList(new Player(this),new Player(this),new Player(this),new Player(this));
		//User-driven creation of map elements, such as country, continent, and connectivity between countries. 4
		continents = Arrays.asList(new Continent(20),new Continent(15),new Continent(10));
		//Saving a map to a text file exactly as edited (using the “conquest” game map format). 3
		//Loading a map from an existing “conquest” map file, then editing the map, or create a new map from scratch. 3
		//Verification of map correctness upon loading and before saving (at least 3 types of incorrect maps). 2
		//Implementation of a game driver implementing the game phases according to the Risk rules. 2
		//Game starts by user selection of a user-saved map file. 1
		//Map is loaded as a connected graph, which is rendered effectively to the user to enable efficient play. 3
		//User chooses the number of players,
		
	}

	public int getPlayersCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	public List<List<Country>> getRandomCountriesLists(int playersCount) {
		// TODO Auto-generated method stub
		
		return null;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public List<Continent> getContinents() {
		return continents;
	}

	public int getArmiesFromCards(List<Card> cards) {
		HashMap<Card.Sort, Integer> cardsCount = new HashMap<Card.Sort, Integer>(){{
		    put(Card.Sort.TYPE_1, 0);
		    put(Card.Sort.TYPE_2, 0);
		    put(Card.Sort.TYPE_3, 0);
		    put(Card.Sort.TYPE_4, 0);
		}}; // TODO EnumMap could be better
		
		for (Card card : cards) {
			Integer new_qty = cardsCount.get(card.getType());
			cardsCount.put( card.getType(), new_qty++ );
		}
		//Finally, if the player owns three cards of different sorts or the same sorts, he can exchange them for armies. 		
		////getEligibleArmies manages the counter to 5, 10, 15.. across players turns 
		////Cards is an utility class for now (maybe should be Board.Cards because Board HAS Cards).
		//The number of armies a player will get for cards is first 5, then increases by 5 every time any player does so (i.e. 5, 10, 15, …). In any case, the minimal number of reinforcement armies is 3. 

		boolean equals = cardsCount.values().contains(3);
		Collection<Integer> diffs = cardsCount.values();
		diffs.removeIf(new Predicate<Integer>() {
			public boolean test(Integer i) {
				return i != 1;
			}
		});
		boolean differents = diffs.size() >= 3;
		
		if (equals||differents) {
			cardsToGive += 5;
			return cardsToGive;	
		} else {
			return 0;
		}
	}

	public void setPlayers(List<Player> asList) {
		players = asList;
	}

}

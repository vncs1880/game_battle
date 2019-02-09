/**
 * 
 */
package org.game_battle.gameplay;

import java.util.*; 

/**
 * @author voda
 *
 */
public class Board {
	private List<Continent> continents;
	private List<List<Country>> countriesByContinent;
	private List<Player> players;
	
	/**
	 * 
	 */
	public Board() {
		// TODO Auto-generated constructor stub
	}

	public void startup() {
		// TODO Auto-generated method stub
		players = Arrays.asList(new Player(this),new Player(this),new Player(this),new Player(this));
		//User-driven creation of map elements, such as country, continent, and connectivity between countries. 4
		continents = Arrays.asList(new Continent(),new Continent(),new Continent());
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
		// TODO Auto-generated method stub
		return players;
	}

	public List<Continent> getContinents() {
		// TODO Auto-generated method stub
		return continents;
	}

}

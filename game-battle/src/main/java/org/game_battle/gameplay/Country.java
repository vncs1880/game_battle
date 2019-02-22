/**
 *
 */
package org.game_battle.gameplay;

import java.util.List;

/**
 * Allows the creation of Country objects.
 * 
 * @author Vinicous
 * @version Alpha
 * @date 5/02/19
 **/
public class Country {
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name + " armies: " + armies;// super.toString();
	}

	private int armies;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param name name of the country
	 *
	 **/
	public Country(String name) {
		this.name = name;
	}

	/**
	 * Used to set the number of armies currently stationed in this country
	 * 
	 * @param n_armies
	 **/
	public void setArmyQty(int n_armies) {
		armies = n_armies;
	}

	/**
	 * Returns the number of armies currently stationed in this country
	 **/
	public int getArmies() {
		return armies;
	}

	/**
	 * Returns a list of the country objects that are neighbor to this country on
	 * the board
	 **/
	public List<Country> getNeighbours() {
		return MapInterface.getNeighbours(this);
	}

}

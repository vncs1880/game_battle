/**
 *
 */
package org.game_battle.gameplay;

import java.util.List;

import org.game_battle.model.Implementation.TerritoryZone;

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
	//private TerritoryZone territoryZone = null;

	/**
	 * 
	 * @return name of the country
	 */
	public String getName() {
		/*if (territoryZone != null) {
			return territoryZone.getTerritoryName();
		}else */return name;
	}

	public void setName(String name) {
		/*if (territoryZone != null) {
			territoryZone.setTerritoryName(name);
		}else */this.name = name;
	}

	/**
	 * @param name name of the country
	 *
	 **/
	public Country(String name) {
		this.name = name;
	}

	/*
	 * public Country(TerritoryZone t) { // TODO Auto-generated constructor stub
	 * this.territoryZone = t; }
	 */

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

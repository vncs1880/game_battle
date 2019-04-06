/**
 *
 */
package org.game_battle.model.Implementation;

import java.util.List;

import org.game_battle.model.Implementation.TerritoryZone;

/**
 * Allows the creation of Country objects.
 * 
 * @author Vinicous
 * @version Alpha
 * 
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

	public int armies;
	private String name;
	// private TerritoryZone territoryZone = null;

	/**
	 * Method to get name of the country
	 * 
	 * @return name of the country
	 */
	public String getName() {
		/*
		 * if (territoryZone != null) { return territoryZone.getTerritoryName(); }else
		 */return name;
	}

	/**
	 * Method to set name of the country
	 * 
	 * @param name name of territory
	 */
	public void setName(String name) {
		/*
		 * if (territoryZone != null) { territoryZone.setTerritoryName(name); }else
		 */this.name = name;
	}

	/**
	 * Country Constructor
	 * 
	 * @param name NAME OF COUNTRY
	 **/ 
	public Country(String name) {
		this.name = name;
	}

	/*
	 * public Country(TerritoryZone t) { this.territoryZone = t; }
	 */



	/**
	 * Used to set the number of armies currently stationed in this country
	 * 
	 * @param n_armies number of armies
	 **/
	public void setArmyQty(int n_armies) {
		armies = (n_armies>0)?n_armies:0;
	}

	/**
	 * Get number of armies stationed in this country
	 * 
	 * @return armies the number of armies currently stationed in this country
	 **/
	public int getArmies() {
		return armies;
	}

	/**
	 * Method to get neigbouring countries
	 * 
	 * @return neighbours a list of the country objects that are neighbor to this
	 *         country on the board
	 **/
	public List<Country> getNeighbours() {
		return MapInterface.getNeighbours(this);
	}

}

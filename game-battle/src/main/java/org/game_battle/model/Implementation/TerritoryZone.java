package org.game_battle.model.Implementation;

import org.game_battle.model.Contract.Territory;

import java.util.ArrayList;

/**
 * TerritoryZone has the info reg
 * territoryName,co-ordinates,continentName,armiesCount
 * 
 * @author basant
 * @version Alpha
 *
 */

public class TerritoryZone implements Territory {

	private String territoryName;
	private String x_coordinates;
	private String y_coordinates;

	private String continentName;

	private Integer armiesCount;

	private ArrayList<String> adjacentTerritories = new ArrayList<String>();

	/**
	 * TerritoryZone Constructor Class
	 * 
	 * @param n_continentName       continent Name
	 * @param n_territoryName       country name
	 * @param x_coordinates         coordinate of country
	 * @param y_coordinates         cordinate of country
	 * @param n_adjacentTerritories neighbouring countries
	 */

	public TerritoryZone(String n_continentName, String n_territoryName, String x_coordinates, String y_coordinates,
			ArrayList<String> n_adjacentTerritories) {
		this.setContinentName(n_continentName);
		this.setTerritoryName(n_territoryName);
		this.setCoordinates(x_coordinates, y_coordinates);
		this.adjacentTerritories = n_adjacentTerritories;
		this.armiesCount = 0;
	}

	public TerritoryZone() {

	}

	/**
	 * hasAdjacencyWith checks if the countries are neighbouring
	 * 
	 * @param t territory t
	 * @return continentName 
	 */
	public boolean hasAdjacencyWith(Territory t) {
		return continentName.contains(t.getTerritoryName());
	}

	/**
	 * getAdjacentNeighbours gets the adjacent neighbours
	 * 
	 * @return null
	 */
	public ArrayList<Territory> getAdjacentNeighbours() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * getAdjacentTerritories gets the adjacent territories return
	 * adjacentTerritories
	 */
	public ArrayList<String> getAdjacentTerritories() {
		return this.adjacentTerritories;

	}

	/**
	 * setAdjacentTerritories sets the adjacent territories
	 * 
	 * @param territorylist list of territories
	 */
	public void setAdjacentTerritories(ArrayList<String> territorylist) {
		this.adjacentTerritories.clear();
		this.adjacentTerritories = territorylist;

	}

	/**
	 * getTerritoryName gets the territory names
	 * 
	 * @return territoryName
	 */

	public String getTerritoryName() {
		return territoryName;
	}

	/**
	 * setTerritoryName sets the territory Name
	 * 
	 * @param territoryName name of territory
	 */
	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}

	/**
	 * setContinentName sets the continent Name
	 * 
	 * @param continentName name of the continent
	 */

	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	/**
	 * getxCoordinates gets the x coordinates
	 * 
	 * @return x_coordinates
	 */
	public String getxCoordinates() {
		return x_coordinates;
	}

	/**
	 * getyCoordinates gets the y coordinates
	 * 
	 * @return y_coordinates
	 */

	public String getyCoordinates() {
		return y_coordinates;
	}

	public void setCoordinates(String x_coordinates, String y_coordinates) {
		this.x_coordinates = x_coordinates;
		this.y_coordinates = y_coordinates;

	}

	public String toString() {
		return "territory: '" + this.territoryName + "', continent: '" + this.continentName + "', Terrirotry: '"
				+ this.adjacentTerritories + "'";
	}

	/**
	 * getContinentName gets the continent Name
	 * 
	 * @return continentName
	 */
	public String getContinentName() {
		return continentName;
	}

}

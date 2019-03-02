package org.game_battle.model.Contract;

import java.util.ArrayList;

/**
 * A javadoc for Territory interface
 * that has the following methods
 * @author
 * @version map1.0
 * 
 */

public interface Territory {
	//getting adjacent territories
	public ArrayList<String> getAdjacentTerritories();
	//getting territory name
	public String getTerritoryName();
	// setter method for territory name
	public void setTerritoryName(String territoryName);
	//setter method for continent name
	public void setContinentName(String continentName) ;
	//getting the coordinates
	public String getxCoordinates();
	public String getyCoordinates();
	//setter method for coordinates
	public void setCoordinates(String x_coordinates, String y_coordinates);


}

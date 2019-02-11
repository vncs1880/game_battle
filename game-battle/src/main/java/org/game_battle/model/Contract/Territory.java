package org.game_battle.model.Contract;

import java.util.ArrayList;

public interface Territory {

	public ArrayList<String> getAdjacentTerritories();
	public String getTerritoryName();
	public void setTerritoryName(String territoryName);
	public void setContinentName(String continentName) ;
	public String getxCoordinates();
	public String getyCoordinates();
	public void setCoordinates(String x_coordinates, String y_coordinates);


}

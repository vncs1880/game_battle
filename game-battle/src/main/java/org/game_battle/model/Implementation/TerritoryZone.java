package org.game_battle.model.Implementation;
import org.game_battle.model.Contract.Territory;

import java.util.ArrayList;


public class TerritoryZone implements Territory {
	
	private String territoryName;
	private String x_coordinates;
	private String y_coordinates;

	private String continentName;

	private Integer armiesCount;

    private ArrayList<String> adjacentTerritories = new ArrayList<String>();    

    public TerritoryZone(String n_continentName, String n_territoryName,String x_coordinates, String y_coordinates, ArrayList<String> n_adjacentTerritories) {
    	this.setContinentName(n_continentName);
		this.setTerritoryName(n_territoryName);
		this.setCoordinates(x_coordinates, y_coordinates);
		this.adjacentTerritories = n_adjacentTerritories;
		this.armiesCount= 0;
	}
    public TerritoryZone() {
    
    }

	public boolean hasAdjacencyWith(Territory t) {
		return continentName.contains(t.getTerritoryName());
	}

	public ArrayList<Territory> getAdjacentNeighbours() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<String> getAdjacentTerritories() {
		return this.adjacentTerritories;
		
	} 
	
	public void setAdjacentTerritories( ArrayList<String> territorylist ) {
		 this.adjacentTerritories.clear();
		 this.adjacentTerritories = territorylist;
		
	} 


	public String getTerritoryName() {
		return territoryName;
	}

	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}

	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	public String getxCoordinates() {
		return x_coordinates;
	}
	public String getyCoordinates() {
		return y_coordinates;
	}
	public void setCoordinates(String x_coordinates, String y_coordinates) {
		this.x_coordinates = x_coordinates;
		this.y_coordinates = y_coordinates;

	}
	
	public String toString() { 
	    return "territory: '" + this.territoryName + "', continent: '" + this.continentName + "', Terrirotry: '" + this.adjacentTerritories + "'";
	}

	public String getContinentName() {
		return continentName;
	}
	

	


}

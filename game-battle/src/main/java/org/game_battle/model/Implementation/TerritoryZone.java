package org.game_battle.model.Implementation;

import java.util.ArrayList;
import java.util.Set;

import org.game_battle.model.Contract.Continent;
import org.game_battle.model.Contract.Player;
import org.game_battle.model.Contract.Territory;

public class TerritoryZone implements Territory {
	
	private String territoryName;
	private String x_coordinates;
	private String y_coordinates;

	private String continentName;


    private ArrayList<String> adjacentTerritories = new ArrayList<String>();    

    public TerritoryZone(String n_continentName, String n_territoryName,String x_coordinates, String y_coordinates, ArrayList<String> n_adjacentTerritories) {
    	this.setContinentName(n_continentName);
		this.setTerritoryName(n_territoryName);
		this.setCoordinates(x_coordinates, y_coordinates);
		this.adjacentTerritories = n_adjacentTerritories;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Void setName(String x) {
		// TODO Auto-generated method stub
		return null;
	}

	public Continent getContinent() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setContinent(Continent c) {
		// TODO Auto-generated method stub
		
	}

	public void setRuler(Player player) {
		// TODO Auto-generated method stub
		
	}

	public Player getRuler() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getArmies() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int setArmies() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public boolean hasAdjacencyWith(Territory t) {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<Territory> getAdjacentNeighbours() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<String> getAdjacentTerritories() {
		return this.adjacentTerritories;
		
	} 


	public String  getContinentName() {
		// TODO Auto-generated method stub
		return continentName;	
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



}

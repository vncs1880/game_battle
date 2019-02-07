package org.game_battle.model.Implementation;

import java.util.ArrayList;
import java.util.Set;

import org.game_battle.model.Contract.Continent;
import org.game_battle.model.Contract.Player;
import org.game_battle.model.Contract.Territory;

public class TerritoryZone implements Territory {
	
	private String territoryName;
	private String coordinates;
	private String continentName;


    private ArrayList<String> adjacentTerritories = new ArrayList<String>();    

    public TerritoryZone(String n_continentName, String n_territoryName,String n_coordinates, ArrayList<String> n_adjacentTerritories) {
    	this.setContinentName(n_continentName);
		this.setTerritoryName(n_territoryName);
		this.setCoordinates(n_coordinates);
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

	public Set<String> getAdjacentTerritories() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getContinentName() {
		// TODO Auto-generated method stub
		return null;
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

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	
	public String toString() { 
	    return "territory: '" + this.territoryName + "', continent: '" + this.continentName + "', Terrirotry: '" + this.adjacentTerritories + "'";
	} 


}

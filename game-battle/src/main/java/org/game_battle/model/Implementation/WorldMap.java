 package org.game_battle.model.Implementation;

import java.util.HashMap;



import java.util.ArrayList;


public class WorldMap {
	public static HashMap<String, HashMap<String,TerritoryZone>> continents = new HashMap<String, HashMap<String,TerritoryZone>>();
	public static HashMap<String, Integer> continentValues = new HashMap<String, Integer>();		
	public static HashMap<String, ArrayList<String>> neighbours = new HashMap<String, ArrayList<String>> ();
	public static int noOfCountries =  neighbours.size() ;
	public static int noOfContinents = continentValues.size();


	public ArrayList<String> getNeighbours(String country)
	{
		return(neighbours.get(country));
	}
	
	
	



}

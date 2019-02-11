 package org.game_battle.model.Implementation;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;


public class WorldMap {
	private  Map<String, Integer> continentValues ;		
	private Map<String, ArrayList<String>> territoryNeighbour ;
	private  HashMap<String, HashMap<String,TerritoryZone>> continentsInfo ;
	public WorldMap()
	{
		continentValues = new HashMap<String, Integer>();	
		territoryNeighbour = new HashMap<String, ArrayList<String>> ();
		continentsInfo = new HashMap<String, HashMap<String,TerritoryZone>>();
	}
	public void setContinentValues(Map<String, Integer> input)
	{
		for (Map.Entry<String, Integer> entry : input.entrySet()) {
		    String key = entry.getKey() ;
		    Integer value = entry.getValue();
		    continentValues.put(key, value);
		}
	}
	public void setContinentValues(String key, Integer value)
	{
		    continentValues.put(key, value);
	}
	public Map<String, Integer> getContinentValues( )
	{
		return continentValues;
	}
	public void setTerritoryNeighbour(Map<String,  ArrayList<String>> input)
	{
		for (Map.Entry<String, ArrayList<String>> entry : input.entrySet()) 
		{
		    String key = entry.getKey() ;
		    ArrayList<String> value = entry.getValue();
		    ArrayList<String> temp = new ArrayList<String>();
		    for(String element : value )
		    {
		    	temp.add(element) ; 
		    }
		    
		    territoryNeighbour.put(key, temp);
		}

	}
	
	public Map<String,  ArrayList<String>> getTerritoryNeighbour( )
	{
		return  territoryNeighbour; 
	}

	
	public void setContinentsInfo(Map<String,  HashMap<String,TerritoryZone>> input)
	{
		for (Map.Entry<String, HashMap<String,TerritoryZone>> entry : input.entrySet()) 
		{
		    String key = entry.getKey() ;
		    Map<String,TerritoryZone> value = entry.getValue();
		    Map<String, TerritoryZone> value_copy = new HashMap<String, TerritoryZone>();	
			for (Map.Entry<String, TerritoryZone> entryforTerritory : value.entrySet()) 
			{
				String keyforTerritory = entryforTerritory.getKey() ;
				TerritoryZone valueforTerritory = entryforTerritory.getValue();
				value_copy.put(keyforTerritory, valueforTerritory);
			}
			continentsInfo.put(key, (HashMap<String, TerritoryZone>) value_copy);
		}

	}
	
	public Map<String,  HashMap<String,TerritoryZone>> getContinentsInfo()
	{
		return continentsInfo; 
	}
	
	public Map<String,TerritoryZone> getContinentInfo(String continent)
	{
		return continentsInfo.get(continent); 
	}
	public int getNumberOfCountries()
	{
		return territoryNeighbour.size();
	}
	
	public int getNumberOfContinents()
	{
		return continentValues.size();
	}
	
}

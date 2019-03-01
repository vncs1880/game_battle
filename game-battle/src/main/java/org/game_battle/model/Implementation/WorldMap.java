 package org.game_battle.model.Implementation;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.text.html.HTMLDocument.Iterator;

import java.util.ArrayList;
import java.util.Collection;


public class WorldMap {
	private TerritoryZone TerritoryZone;
	private  Map<String, Integer> continentValues ;		
	private Map<String, ArrayList<String>> territoryNeighbour ;
	private  HashMap<String, HashMap<String,TerritoryZone>> continentsInfo ;
	public WorldMap()
	{
		continentValues = new HashMap<String, Integer>();	
		territoryNeighbour = new HashMap<String, ArrayList<String>> ();
		continentsInfo = new HashMap<String, HashMap<String,TerritoryZone>>();
		TerritoryZone tz = new TerritoryZone(); 
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
	/**
	 * getContinentValues gets the continent values
	 * @return continentValues
	 */
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
	/**
	 * getTerritoryNeighbour gets the neighbours of the territories.
	 * @return territoryNeighbour
	 */
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
	
	
	public void updateNeighboursInCountryInfo(Map<String,  HashMap<String,TerritoryZone>> input)
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
				valueforTerritory.setAdjacentTerritories(this.territoryNeighbour.get(key) ); 
				value_copy.put(keyforTerritory, valueforTerritory);
			}
			continentsInfo.put(key, (HashMap<String, TerritoryZone>) value_copy);
		}

	}
	
	public void setUpdatedNeighbours( String key)
	{

		 HashMap<String,TerritoryZone> temp =  continentsInfo.get(key) ;
		 TerritoryZone tz = temp.get(key);
		 tz.setAdjacentTerritories(territoryNeighbour.get(key) ); 
		 temp.put(key, tz);
		 continentsInfo.put(key, temp);

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
	public void updateContinent(String continentVlaues) {
		if(continentVlaues.length()>0)
		{
			String [] value = continentVlaues.split(":");
			String continent = value[0];
			Integer control_value = Integer.parseInt( value[1]);
		    this.continentValues.put(continent ,  control_value);   
		}
		
	}
	public void updateNeighbours(String neighbourValues) {
		if(neighbourValues.length()>0)
		{
			String [] value = neighbourValues.split(":");
			String key = value[0];
			String []neighbour = value[1].split(",");
			 ArrayList<String> temp = new ArrayList<String>();
			 temp = this.territoryNeighbour.get(key);

			 for(String element : neighbour )
			    {
	
				 temp.add(element.trim()) ; 
			    }


		    this.territoryNeighbour.put(key, temp);
		    this.updateNeighboursInCountryInfo( this.continentsInfo);
		}	
		
	}
	public void removeNeighbours(String removeNeighbours) {
		if(removeNeighbours.length()>0)
		{
			String [] value = removeNeighbours.split(":");
			String key = value[0];
			String []neighbour = value[1].split(",");
			if(this.territoryNeighbour.containsKey(key))
			{
				ArrayList<String> temp = new ArrayList<String>();
				temp = this.territoryNeighbour.get(key);
				for(String element : neighbour )
				 	{	
					temp.remove(element.trim());
				    }
			    this.territoryNeighbour.put(key, temp);
			    this.updateNeighboursInCountryInfo( this.continentsInfo);

				}   
			}
		}
		
		
	}
	


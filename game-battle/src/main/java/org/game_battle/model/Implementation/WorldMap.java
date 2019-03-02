 package org.game_battle.model.Implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * WorldMap contains the metadata of the MapLoad
 * @author 91950
 * 
 *
 */

public class WorldMap {
	private ContinentZone continentZone;
	private TerritoryZone territoryZone;
	private Map<String, Integer> continentValues ;		
	private Map<String, ArrayList<String>> territoryNeighbour ;
	private HashMap<String, HashMap<String,TerritoryZone>> continentsInfo ;
	private Map<String, ArrayList<TerritoryZone>> territoryContinents;
	private ArrayList<ContinentZone> continents;

	public WorldMap()
	{
		continentValues = new HashMap<String, Integer>();	
		territoryNeighbour = new HashMap<String, ArrayList<String>> ();
		continentsInfo = new HashMap<String, HashMap<String,TerritoryZone>>();
		territoryContinents = new HashMap<String, ArrayList<TerritoryZone>>();
		continents = new ArrayList<ContinentZone>();
	}
	/** the mehods set creates the continentInfo data structure
	 * @param input
	 */
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

	/**
	 * setContinentValues sets the continentvalues in Hashmap pair
	 * @param input
	 */

	
	// the method creates list of continent objects
	public void setContinentZone()
	{
		for(Map.Entry<String, ArrayList<TerritoryZone>> entry : territoryContinents.entrySet())
		{
			String name = entry.getKey();
			ArrayList<TerritoryZone> str = new ArrayList<TerritoryZone>();
			str = entry.getValue();
			continentZone = new ContinentZone(name , str);
			continents.add(continentZone);
		}
	}
	
	// this method creates the list of continent and its values

	public void setContinentValues(Map<String, Integer> input)
	{
		for (Map.Entry<String, Integer> entry : input.entrySet()) {
		    String key = entry.getKey() ;
		    Integer value = entry.getValue();
		    continentValues.put(key, value);
		}
	}
	
	// the setter method to set continentVlaues
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

	/**
	 * setTerritoryNeighbour sets the territories and its neighbours
	 * @param input
	 */

	
	// creates the map of territory and its neighbours

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
	public  ArrayList<String> getTerritoryNeighbour(String territory )
	{
		return  territoryNeighbour.get(territory); 
	}


	/**
	 * setContinentsInfo sets the continents its countries and its neighbouring territories
	 * @param input
	 */



	
	
	public Map<String,  HashMap<String,TerritoryZone>> getContinentsInfo()
	{
		return continentsInfo; 
	}
	
	/**
	 * updateNeighboursInCountryInfo updates the newly addded neighbours
	 * @param input
	 */
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
	/**
	 * setUpdatedNeighbours sets the newly updated neighbour to continentsInfo
	 * @param key
	 */
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
	/**
	 * updateContinent updates the new country and its control value
	 * @param continentVlaues
	 */
	public void updateContinent(String continentVlaues) {
		if(continentVlaues.length()>0)
		{
			String [] value = continentVlaues.split(":");
			String continent = value[0];
			Integer control_value = Integer.parseInt( value[1]);
		    this.continentValues.put(continent ,  control_value);   
		}
		
	}
	/**
	 * updateNeighbours updates the new neighbours with its country
	 * @param neighbourValues
	 */
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
	/**
	 * removeNeighbours removes the neighbours listed for that particular 
	 * country
	 * @param removeNeighbours
	 */
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
	
	/**
	 * getTerritories gets all the territories
	 * @return territories
	 */
	public List<TerritoryZone> getTerritories()
	{
		List<TerritoryZone> territories = new ArrayList<TerritoryZone>();
		
		for (Map.Entry<String, HashMap<String,TerritoryZone>> entry : this.continentsInfo.entrySet()) 
		{
		    Map<String,TerritoryZone> value = entry.getValue();
			for (Map.Entry<String, TerritoryZone> entryforTerritory : value.entrySet()) 
			{
				TerritoryZone TerritoryZone = new TerritoryZone(); 
				TerritoryZone = entryforTerritory.getValue();
				territories.add(TerritoryZone);
			}
		}
 		
		return territories;	
		
	}
	/**
	 * getContinents gets all the continents
	 * @return continentList
	 */
	public List<ContinentZone> getContinents()
	{
	
		return continents;	
	}
		
    public  List<TerritoryZone> getCountriesByContinent(String continent ) {
    	
    	return territoryContinents.get(continent) ;
    	
    }
	public void addContinentAndTerriroty(String continentName, TerritoryZone territoryName) {
		ArrayList<TerritoryZone> country = new ArrayList<TerritoryZone>();

		if (!territoryContinents.containsKey(continentName))
		{
			country.add(territoryName);
			territoryContinents.put(continentName, country);
		}
		else
		
		{
			country = territoryContinents.get(continentName);
			country.add(territoryName);
			territoryContinents.put(continentName, country);
		}
	}

	public Map<String, ArrayList<String>> getTerrirotyNeighbourList() {
		return territoryNeighbour;
	}

	
  }
			
	
	


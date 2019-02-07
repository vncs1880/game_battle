package org.game_battle.utility;

import java.util.ArrayList;
import java.util.HashMap;
import org.game_battle.model.Implementation.*;

public class MapLoader {
	

	 public static boolean isContinent = false;
	 public static boolean isCountry = false;
	
	public static void extractData(String line)
	{

		line = line.trim();
		if(line.equals("[Continents]"))
		{

			isContinent = true;
			isCountry = false;
			return; 
		}

		if(isContinent && !line.isEmpty() )
		{		
			getContinent(line);
		}
		if(line.equals("[Territories]"))
		{
			isContinent = false;
			isCountry = true;
			return; 

		}
		if(isCountry && !line.isEmpty())
		{
			getTerritorisInfo(line);

		}
		
	}
	
	 public static void getContinent(String line)
	 {
		 String[] value = line.split("=");
			if(value.length == 2)
			{
				Integer c_value = Integer.parseInt(value[1]) ;
				WorldMap.continentValues.put(value[0], c_value);
			}
		 
	 }
	private static void getTerritorisInfo(String line) {
		String territoryName = null; 
		String cordiantes = null;
		String continentName = null ;
		ArrayList<String> CountryList = new ArrayList<String>();
		String[] value = line.split(",");
		if(value.length != 0)
		{
			 territoryName= value[0]; 
			 cordiantes = value[1] + "-" +  value[2];
			 continentName = value[3];
			for ( int i = 4 ; i < value.length; i++ )
			{
				CountryList.add(value[i]);
			}
		}
		TerritoryZone tz = new TerritoryZone(continentName, territoryName, cordiantes, CountryList); 
		HashMap<String,TerritoryZone>  tz_hash= new HashMap<String,TerritoryZone>() ;
		tz_hash.put(continentName, tz);
		WorldMap.continents.put(territoryName  , tz_hash);
	}

}

package org.game_battle.utility;


import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.LinkedList; 


public class ReadJSONFromFile {
	


	 HashMap<String, LinkedList<String>> NeighbourList;  
	 HashMap<String, Integer> Continent;    
	 LinkedList<String> CountryList ;


	
	public void readJSONFromFile()
	{
		JSONParser parser = new JSONParser();
		NeighbourList = new HashMap<String, LinkedList<String>>();  
        Set<String> country_Set = new HashSet<String>(); 
        

		 try {
			 
	            Object obj = parser.parse(new FileReader("/Users/basantsingh/git/game_battle/game-battle/resource/SOUTH_AMERICA.txt"));
	            JSONObject jsonObject = (JSONObject) obj;
	            JSONArray countryList = (JSONArray) jsonObject.get("Country");
	            Iterator<String> it1 = countryList.iterator();
	            while (it1.hasNext()) 
	            {
	                String country= it1.next();
		            JSONArray neighbourList = (JSONArray) jsonObject.get(country);
		            Iterator<?> it2 = neighbourList.iterator();
		    	    CountryList = new LinkedList<String>();

	                while (it2.hasNext()) 
		            {
	                	String x = (String) it2.next();
	                	CountryList.add(x);
	                	country_Set.add(x);
		            }
                	country_Set.add(country);
	                NeighbourList.put(country,  CountryList);

	            }
                System.out.println(NeighbourList);
                System.out.println(country_Set);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		 
	}

	
	
	
}

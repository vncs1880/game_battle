package org.game_battle.utility;


import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class ReadJSONFromFile {

	public ReadJSONFromFile() {
		JSONParser parser = new JSONParser();
		 try {
			 
	            Object obj = parser.parse(new FileReader("/Users/basantsingh/git/game_battle/game-battle/resource/SOUTH_AMERICA.txt"));
	            JSONObject jsonObject = (JSONObject) obj;
	            JSONArray countryList = (JSONArray) jsonObject.get("Country");

	            Iterator<String> it1 = countryList.iterator();

	            while (it1.hasNext()) 
	            {
	                String country= it1.next();
	                System.out.println(country);
		            JSONArray neighbourList = (JSONArray) jsonObject.get(country);
		            Iterator<String> it2 = neighbourList.iterator();

	                while (it2.hasNext()) 
		            {
		                System.out.print(it2.next() + " ");
		                
		            }
	                System.out.print("\n");

	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

	
	
}

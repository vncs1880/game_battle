package org.game_battle.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class ReadMapFile {
	
	 HashMap<String, LinkedList<String>> NeighbourList;  
	 HashMap<String, Integer> Continent;    
	 LinkedList<String> CountryList ;
	 boolean isContinent = false;
	 boolean isCountry = false;

	public ReadMapFile() {
		// TODO Auto-generated constructor stub
	}
	
	public	void readFiles(String PATH)
	{
	 
		BufferedReader reader;
		Continent = new HashMap<String, Integer>();
		NeighbourList = new HashMap<String, LinkedList<String>> ();
		try {
			reader = new BufferedReader(new FileReader("/Users/basantsingh/git/game_battle/game-battle/resource/file.map"));
			String line = reader.readLine();
			while (line != null) {
				// System.out.println(line);
				// read next line
				extractData( line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(NeighbourList);
		
		System.out.println(Continent);

	}
	
	 void extractData(String line)
	{


		 String country = "" ;
		line = line.trim();
//		System.out.println(line);

		if(line.equals("[Continents]"))
		{

			isContinent = true;
			isCountry = false;
			return; 
		}

		if(isContinent )
		{		
			String[] value = line.split("=");
			if(value.length == 2)
			{
				country = value[0];
				Integer c_value = Integer.parseInt(value[1]) ;
				Continent.put(country, c_value);
			}
		}
		if(line.equals("[Territories]"))
		{
			isContinent = false;
			isCountry = true;
			return; 

		}
		if(isCountry && !line.isEmpty())
		{
			String[] value = line.split(",");
			if(value.length != 0)
			{
				country = value[0] ;
	    	    CountryList = new LinkedList<String>();
				for ( int i = 3 ; i < value.length; i++ )
				{
                	CountryList.add(value[i]);
				}
			}
			
            NeighbourList.put(country,  CountryList);

		}
		


	}
 
 

}

package org.game_battle.booatstrap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import org.game_battle.model.Implementation.TerritoryZone;
import org.game_battle.model.Implementation.WorldMap;
import org.game_battle.utility.MapLoader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class MapFile {

	File outputPath;

	public	void readFiles(String PATH)
	{
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("/Users/basantsingh/git/game_battle/game-battle/resource/file2.map"));
			String line = reader.readLine();
			while (line != null) {
				MapLoader.extractData( line);
				line = reader.readLine();
			}
			System.out.println(WorldMap.continents);
			System.out.println(WorldMap.continentValues);

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void writeFiles(){		
		try {
			FileWriter fw = new FileWriter(outputPath);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("[Continents]");
			bw.newLine();
			for(String key : WorldMap.continentValues.keySet()){
				bw.write(key+"="+WorldMap.continentValues.get(key));
				bw.newLine();
			}
			bw.write("[Territories]");
			bw.newLine();
			for(String key : WorldMap.continents.keySet()){
                HashMap<String,TerritoryZone> territories = WorldMap.continents.get(key);
                for(TerritoryZone t:territories.values()){
                	String tmpStorage = "";
                	for(String s:t.getAdjacentTerritories()){
                		tmpStorage +=","+s; 
                	}
                	bw.write(t.getTerritoryName()+","+t.getCoordinates()+","+t.getContinentName()+tmpStorage);
    			    bw.newLine();
    			}
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

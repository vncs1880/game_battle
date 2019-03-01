package org.game_battle.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.game_battle.model.Implementation.TerritoryZone;
import org.game_battle.model.Implementation.WorldMap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class FileReaderWriter {

	File outputPath;
	/**
	 * readFile is to read the existing map file from the stored
	 * location.
	 * @param PATH
	 * @return filedData
	 */
	public static ArrayList<String> readFile(String PATH)
	{
		BufferedReader reader;
		ArrayList<String> filedData = new ArrayList<String>();
		try {
			reader = new BufferedReader(new FileReader(PATH));
			String line = reader.readLine();
			while (line != null) {
				filedData.add( line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filedData;

	}
	
	public static void writeFile(String PATH , WorldMap wm){		
		try {
			
		    System.out.println("#####");

		    System.out.println( wm.getTerritoryNeighbour());

			FileWriter fw = new FileWriter(PATH);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("[Continents]");
			bw.newLine();
			for(String key : wm.getContinentValues().keySet()){
				bw.write(key+"="+wm.getContinentValues().get(key));
				bw.newLine();
			}
			bw.write("[Territories]");
			bw.newLine();
			for(String key : wm.getContinentsInfo().keySet()){
                Map<String,TerritoryZone> territories = wm.getContinentInfo(key);
                for(TerritoryZone t:territories.values()){
                	String tmpStorage = "";
                	for(String s:t.getAdjacentTerritories()){
                		tmpStorage +=","+s; 
                	}
                	bw.write(t.getTerritoryName()+","+t.getxCoordinates()+ ","+ t.getyCoordinates()+ ","+ t.getContinentName()+tmpStorage);
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

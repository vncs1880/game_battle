package org.game_battle.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.game_battle.model.Implementation.*;

/**
 * MapDataExtractor class extracts the loaded map data and sets the continents and the countries
 * 
 * @author basant
 * @version Alpha
 *
 */
public class MapDataExtractor {

	private static Scanner sc;

	/**
	 * extractData is to extract the data from the map file and store the continents
	 * and countries in worldMap.
	 * 
	 * @param mapList Array of Map values
	 * @param wm      worldmap
	 * 
	 */
	public static void extractData(ArrayList<String> mapList, WorldMap wm) {
		boolean isContinent = false;
		boolean isCountry = false;

		for (String line : mapList) {
			line = line.trim();
			if (line.equals("[Continents]")) {
				isContinent = true;
				isCountry = false;
				continue;
			}

			if (isContinent && !line.isEmpty()) {
				setContinent(line, wm);
			}
			if (line.equals("[Territories]")) {
				isContinent = false;
				isCountry = true;
				continue;
			}
			if (isCountry && !line.isEmpty()) {
				setTerritorisInfo(line, wm);

			}
		}
	}

	/**
	 * setContinent method sets the continent and its control value in World Map.
	 * 
	 * @param line read the loaded map
	 * @param wm   worldmap
	 */
	private static void setContinent(String line, WorldMap wm) {
		Map<String, Integer> continentValues = new HashMap<String, Integer>();
		String[] value = line.split("=");
		if (value.length == 2) {
			Integer c_value = Integer.parseInt(value[1]);
			continentValues.put(value[0], c_value);
		}
		wm.setContinentValues(continentValues);

	}

	/**
	 * setTerritorisInfo sets the territory name,continnet and its neighbouring
	 * countries
	 * 
	 * @param line read the loaded map
	 * @param wm   worldmap
	 */
	private static void setTerritorisInfo(String line, WorldMap wm) {
		Map<String, ArrayList<String>> territoryNeighbour = new HashMap<String, ArrayList<String>>();
		Map<String, HashMap<String, TerritoryZone>> continents = new HashMap<String, HashMap<String, TerritoryZone>>();
		String territoryName = null;
		String xcoordinates = null;
		String ycoordinates = null;

		String continentName = null;
		ArrayList<String> CountryList = new ArrayList<String>();
		String[] value = line.split(",");
		if (value.length != 0) {
			territoryName = value[0];
			xcoordinates = value[1];
			ycoordinates = value[2];
			continentName = value[3];
			for (int i = 4; i < value.length; i++) {
				CountryList.add(value[i]);
			}
		}
		if (!wm.getContinentValues().containsKey(value[3])) {
			System.out.println("Enter the control value of continent: " + continentName);
			sc = new Scanner(System.in);
			Integer temp = Integer.parseInt(sc.nextLine());
			wm.setContinentValues(value[3], temp);
		}

		territoryNeighbour.put(territoryName, CountryList);
		wm.setTerritoryNeighbour(territoryNeighbour);
		TerritoryZone tz = new TerritoryZone(continentName, territoryName, xcoordinates, ycoordinates, CountryList);
		wm.addContinentAndTerriroty(continentName, tz);
		HashMap<String, TerritoryZone> tz_hash = new HashMap<String, TerritoryZone>();
		tz_hash.put(continentName, tz);
		continents.put(territoryName, tz_hash);
		wm.setContinentsInfo(continents);
	}

}

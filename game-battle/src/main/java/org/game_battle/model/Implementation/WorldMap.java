 package org.game_battle.model.Implementation;

import java.util.HashMap;
import java.util.LinkedList;

public class WorldMap {
	public static HashMap<String, HashMap<String,TerritoryZone>> continents = new HashMap<String, HashMap<String,TerritoryZone>>();
	public static HashMap<String, Integer> continentValues = new HashMap<String, Integer>();		
	
	public static void clear() {
		continents.clear();
		continentValues.clear();
	}


}

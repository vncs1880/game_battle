package org.game_battle.model.Contract;

import java.util.ArrayList;
import java.util.Map;

/**
 *  A javadoc for world interface
 * that has the following methods
 * @author basant
 * @version alpha
 * 
 */


public interface World {
	//getting continent values
	public Map<String, Integer> getContinentValues( );
	//getting territoryneighbour
	public Map<String,  ArrayList<String>> getTerritoryNeighbour( );
	//getting continents info
	public Map<String,  Map<String, ?>> getContinentsInfo();
	//getting contiennt info
	public Map<String,?> getContinentInfo(String continent);
	//getting country count
	public int getNumberOfCountries();
	//getting continents count
	public int getNumberOfContinents();
	 
}


package org.game_battle.model.Contract;

import java.util.ArrayList;
import java.util.Map;

public interface World {

	public Map<String, Integer> getContinentValues( );
	public Map<String,  ArrayList<String>> getTerritoryNeighbour( );
	public Map<String,  Map<String, ?>> getContinentsInfo();
	public Map<String,?> getContinentInfo(String continent);
	public int getNumberOfCountries();
	public int getNumberOfContinents();
	 
}

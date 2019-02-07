package org.game_battle.utility;

import org.game_battle.model.Implementation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * This class has methods to check the valid of a map loaded into the memory
 * @author SA
 */
public class MapValidity {

	public static boolean isValidAdjacency(){
		
		Set<String> continentNames = WorldMap.continents.keySet();
		boolean isnotConnetWithSameContinent = true;
		
		for(HashMap<String,TerritoryZone> territories : WorldMap.continents.values()){
			//looping through every territory in a continent
			for(TerritoryZone territory : territories.values()){
				isnotConnetWithSameContinent = true;
				//if there is no adjacent s say wrong map
				if(territory.getAdjacentTerritories().size() == 0){
					return false;
				}
				//loop through adjacent s and check anyone of them belongs to same continent
			     for(String s : territory.getAdjacentTerritories()){
			    	 boolean foundTerritory = false;
			    	 for(String continent: continentNames){
			    	     if(WorldMap.continents.get(continent).containsKey(s)){
			    	    	 //check anyone of them belongs to same continent
			    	    	if(continent.equals(territory.getContinentName()) ){
			    	    	    isnotConnetWithSameContinent = false;
			    	    	}
			    	    	foundTerritory = true;
			    	    }
			    	 }
			    	 //if adjacent territories not found in the map return false
			    	 if(!foundTerritory) return false;
			     }			    	 
			  
			     if(isnotConnetWithSameContinent  && WorldMap.continents.get(territory.getContinentName()).size() > 1){
			    	     return false;
			    	   }
			     }
			}
		
		return true;
	}

	




	/**
	 * @return false is there is any dis-connectivity link than continent disconnectivity 
	 */
	public static boolean isAnyDiconnectivity(){		
        
		HashSet<String> allAdjacencies = new HashSet<String>();
		HashMap<String,String> waitingForConnection = new HashMap<String, String>();
		for(String continent : WorldMap.continents.keySet()){
			//get all territories in the map 
			Set<String> countries = WorldMap.continents.get(continent).keySet();
			//loop through all territories in  a continent
			for(String territory: countries){
				ArrayList<String> tmp = new ArrayList<String>();
				tmp.clear();
				tmp.addAll(WorldMap.continents.get(continent).get(territory).getAdjacentTerritories());
				
				if(!allAdjacencies.contains(territory)){
					waitingForConnection.put(territory,continent);
				}else{
					waitingForConnection.remove(territory);
				}
				//looping through adjacent's territories
				for(String s: tmp){
					//check if anyone is waiting for the connectivity 
					if(waitingForConnection.containsKey(s)){
						String continentTmp = waitingForConnection.get(s);
						if(WorldMap.continents.get(continentTmp).get(s).getAdjacentTerritories().size() == 1){
							if(!((HashMap<String, HashMap<String, TerritoryZone>>) WorldMap.continents.get(continentTmp).get(s).getAdjacentTerritories()).get(0).equals(territory)){
								waitingForConnection.remove(s);	
							}
							else if(tmp.size()>1){
								waitingForConnection.remove(s);
							}
							else{

							}
						}else{
							waitingForConnection.remove(s);	
						}
					}
					
				}
				allAdjacencies.addAll(tmp); 
		
			}
			
		}	
		
		if(waitingForConnection.size()  == 1 && allAdjacencies.size() == 2){
			return true;
		}
		
		if(waitingForConnection.size()  == 0){
			return true;
		}
		
		return false;		
	}
	
	
}
package org.game_battle.model.Contract;

import java.util.ArrayList;

public interface World {
	
	 void addEdge(int src, int dest);
	 boolean removeEdge(int src, int dest);
	 ArrayList<Continent> getContinent();


}

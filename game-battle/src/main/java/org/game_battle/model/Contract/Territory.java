package org.game_battle.model.Contract;

import java.util.ArrayList;

public interface Territory {
	
    String getName();
    Void setName(String x);
	Continent getContinent();
	void setContinent(Continent c);
	void setRuler(Player player);
	Player getRuler();
	int getArmies();
	int setArmies();

	boolean hasAdjacencyWith(Territory t);
	ArrayList<Territory> getAdjacentNeighbours();

}

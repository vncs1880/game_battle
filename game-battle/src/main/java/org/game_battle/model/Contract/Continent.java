package org.game_battle.model.Contract;

import java.util.ArrayList;

public interface Continent 
{
	
	ArrayList<Territory> getTerritories();
	void addTerritory(Territory t);
	void removeTerritory(Territory t);
	String getContinentName();
	void setContinentName(String name);
	String getContinentID();
	void setContinentID(int id);
	int getControlValue();
	void setControlValue(int value);
	
}

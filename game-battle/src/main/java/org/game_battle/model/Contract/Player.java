package org.game_battle.model.Contract;

import java.util.ArrayList;

public interface Player {
	
	String getName();
    void setName(String newName);
    ArrayList<Territory> getTerritories();
    double getCoverage();
    void setCoverage(double value);
    void setUnusedArmies(int armies);
    int getUnusedArmies();
    void setUsedArmies(int armies);
    int getUsedArmies();
    Territory getTerritoryByName(String territoryName);
    void reinforcement();
    void attack(int attempts);
    void attack();
    void fortification();
    void addCard(Card crd);
    ArrayList<Card> getCardSet();
	


}

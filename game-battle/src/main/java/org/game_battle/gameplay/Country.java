package org.game_battle.gameplay;

import java.util.ArrayList;

/**
 * @author voda
 *
 */
public class Country {

    private int armies;
    private String name;
    private ArrayList<Country> neighbours;
    private Player ruler;
    private boolean hasRuler;

    public Country(String name) {
        // TODO Auto-generated constructor stub
        this.name = name;
        hasRuler = false;
        armies = 0;
        System.out.println("Country Created " + name);
    }

    public void setArmyQty(int num) {
        armies = num;
    }

    public int getArmies() {
        // TODO Auto-generated method stub
        return armies;
    }

    public ArrayList<Country> getAdjacentNeighbours() {
        // TODO Auto-generated method stub
        return neighbours;
    }

    /**
     * When a player occupies a country the player object is set as the ruler of
     * the country
     */
    public void setRuler(Player ruler) {
        hasRuler = true;
        this.ruler = ruler;

    }

    /**
     * Returns the player object who currently rules the country
     *
     */
    public Player getRuler() {
        return ruler;
    }

    public boolean hasRuler() {
        return hasRuler;
    }

}

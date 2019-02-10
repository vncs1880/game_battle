package org.game_battle.gameplay;

import java.util.ArrayList;

public class Continent {

    private int controlValue;
    private String name;
    private ArrayList<Country> countries;

    public Continent(String name, int ctrlValue, ArrayList<Country> memberCountries) {
        // TODO Auto-generated constructor stub
        controlValue = ctrlValue;
        this.name = name;
        countries = memberCountries;
        System.out.println("Continent Created: " + name + "Extra Armies: " + controlValue);

    }

    /**
     * Returns the control value associated with the continent
     *
     */
    public int getControlValue() {
        // TODO Auto-generated method stub
        return controlValue;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns a list of the country objects that are on this continent
     *
     */
    public ArrayList<Country> getMemberCountries() {
        return countries;
    }

}

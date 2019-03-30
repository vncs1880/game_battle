package org.game_battle.model.Implementation;

import java.util.ArrayList;
import java.util.List;

import org.game_battle.model.Contract.Continent;

/**
 * ContinentZone has the continent info
 * 
 * @author basant
 * @version Alpha
 *
 */

public class ContinentZone implements Continent {
	private String continentName;
	private ArrayList<TerritoryZone> countrylist;

	ContinentZone(String continentName, ArrayList<TerritoryZone> countrylist) {
		this.continentName = continentName;
		this.countrylist = new ArrayList<TerritoryZone>();
		this.countrylist = countrylist;

	}

	/**
	 * getContinentName gets the continent Name
	 * 
	 * @return continentName name of the Continent
	 */
	public String getContinentName() {
		return continentName;
	}

	/**
	 * setContinentName set the continent Name
	 * @param continentName name of the continent
	 * 
	 */

	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	/**
	 * getContryList gets the country List
	 * 
	 * @return countrylist list of the countries
	 */

	public List<TerritoryZone> getContryList() {
		return countrylist;
	}

	/**
	 * setContinentList sets the continent list
	 * @param countrylist list of the countries
	 * 
	 */

	public void setContinentList(ArrayList<TerritoryZone> countrylist) {
		this.countrylist = countrylist;
	}

	/**
	 * setContinentList sets the continent List
	 * 
	 * @return countrylist list of the countries
	 */

	public ArrayList<TerritoryZone> setContinentList() {
		return (countrylist);
	}
}

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
	 * @return continentName
	 */
	public String getContinentName() {
		return continentName;
	}

	/**
	 * setContinentName set the continent Name
	 * 
	 */

	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	/**
	 * getContryList gets the country List
	 * 
	 * @return countrylist
	 */

	public List<TerritoryZone> getContryList() {
		return countrylist;
	}

	/**
	 * setContinentList sets the continent list
	 * 
	 */

	public void setContinentList(ArrayList<TerritoryZone> countrylist) {
		this.countrylist = countrylist;
	}

	/**
	 * setContinentList sets the continent List
	 * 
	 * @return countrylist
	 */

	public ArrayList<TerritoryZone> setContinentList() {
		return (countrylist);
	}
}

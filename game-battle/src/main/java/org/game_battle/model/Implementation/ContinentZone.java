package org.game_battle.model.Implementation;

import java.util.ArrayList;
import java.util.List;

import org.game_battle.model.Contract.Continent;
/**
 * ContinentZone has the continent info
 * @author 91950
 *
 *
 */

public class ContinentZone implements Continent {
	private String continentName;
	private ArrayList<TerritoryZone> countrylist;

	ContinentZone(String continentName , ArrayList<TerritoryZone> countrylist)
	{
		this.continentName = continentName;
		this.countrylist = new ArrayList<TerritoryZone>();
		this.countrylist = countrylist;

	}
	
	public String getContinentName()
	{
		return continentName;
	}
	
	public void setContinentName(String continentName)
	{
		this.continentName = continentName;
	}
	public List<TerritoryZone> getContryList()
	{
		return countrylist;
	}
	
	public void setContinentList(ArrayList<TerritoryZone> countrylist)
	{
		this.countrylist = countrylist;
	}
	
	public ArrayList<TerritoryZone> setContinentList()
	{
		return(countrylist);
	}
}

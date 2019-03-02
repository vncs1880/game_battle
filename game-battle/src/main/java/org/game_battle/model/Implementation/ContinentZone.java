package org.game_battle.model.Implementation;

import org.game_battle.model.Contract.Continent;
/**
 * ContinentZone has the continent info
 * @author 91950
 *
 */

public class ContinentZone implements Continent {
	private String Continent;
	
	public String getContinentName()
	{
		return Continent;
	}
	
	public void setContinentName(String Continent)
	{
		this.Continent = Continent;
	}

}

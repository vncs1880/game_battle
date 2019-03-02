package org.game_battle.model.Implementation;

import org.game_battle.model.Contract.Continent;

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

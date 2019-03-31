package org.game_battle.model.Implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.game_battle.model.Contract.Strategy;

public class CheaterStrategyImpl implements Strategy{

	@Override
	public String askText(String msg, String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int askNumber(String title, String prompt, int min, int max, int numOfCountries , boolean flag) {
		// TODO Auto-generated method stub
		if(!flag)
		return max/ numOfCountries;
		return max;
	}

	@Override
	public Collection getObjs(String prompt, Object[] objs) {
		// TODO Auto-generated method stub
		List list = Arrays.asList(objs);
		Collection collection = new ArrayList(list);
		return  collection;
	}

	@Override
	public boolean isUserOk(String title, String prompt) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Country selectCountry(String title, String prompt, List<Country> countries) {
		// TODO Auto-generated method stub
		Country maxCountryArmy = null;
		for(Country str:countries)
		{
			Country country=(Country) str.getNeighbours();
			int noOfArmies=country.getArmies();
			
			int armyCount=0;
			if (armyCount < country.getArmies()) {
				armyCount = country.getArmies();
				maxCountryArmy = country;
			}
			int maxArmies=maxCountryArmy.getArmies();
			str.setArmyQty(maxArmies);
		}
		return maxCountryArmy;
		

	}

	
	@Override
	public int setArmies(int NumOfArmies) {
	//	NumOfArmies=NumOfArmies*2;
	//	NumOfArmies=NumOfArmies/numOfCountries;		
		return NumOfArmies*2;
	}

	
	@Override
	public Country[] doAttack(Country offendingCountry, Country deffendingCountry, int attackerDiceRoll,
			int defenderDiceRoll) {
		// TODO Auto-generated method stub
		
		
		Country[] cn = new Country[2];
		cn[0] = offendingCountry;
		cn[1] = deffendingCountry;
		return cn;
	}

	

}

package org.game_battle.model.Implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.game_battle.GamePlay;
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
		if(title.equals("Rolling dices") ||title.equals("Attack phase")) {
			return max;
			
		}
		if(flag==false) {
			return max/ numOfCountries;

		}else {
		
		int armiesOfSelected=Player.countrySelected.getArmies();
		return 2*armiesOfSelected;
		}
		
		
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
		ArrayList<Country> doubleArmiesArrayList = new ArrayList<Country>();
		Country[] countries_array = countries.toArray(new Country[0]);
		Country maxCountryArmy = null;
		Country minCountryArmy = null;
		Country minCountryArmyFortification = null;
		if(title.equals("Fortification phase")) {
			for(Country country:countries)
			{
				System.out.println("My country"+country);
				for(Country nc :  country.getNeighbours())
				{
					System.out.println("My country neighbours"+nc);
					for (Player pl : GamePlay.playersList)
					{
						System.out.println("Players"+pl);
						if(!GamePlay.playerStatic.getName().equals(pl.getName()))
						{
							System.out.println("Players static"+GamePlay.playerStatic.getName());
							if((pl.getCountries().contains(nc))&& !doubleArmiesArrayList.contains(country))
							{
								doubleArmiesArrayList.add(country);
							}

						}
					}
				}
			}


			for(Country c:doubleArmiesArrayList) {
				int minArmy=9999;

				int noOfArmies=c.getArmies();
				if(minArmy> noOfArmies) {
					minArmy=c.getArmies();
					minCountryArmyFortification=c;
				}
			}
			System.out.println(doubleArmiesArrayList);


			return minCountryArmyFortification;
		}
		if(title.equals("Attack phase") && prompt.equals("Select attacker country")) {
			int armyCount = 0;
			for (Country tempStr : countries_array) {
				if (armyCount < tempStr.getArmies()) {
					armyCount = tempStr.getArmies();
					maxCountryArmy = tempStr;
				}
			}
			return maxCountryArmy;

		}
		if(title.equals("Attack phase") && prompt.equals("Select target country")) {
			int armyCount = 9999;
			for (Country tempStr : countries_array) {
				if (armyCount > tempStr.getArmies()) {
					armyCount = tempStr.getArmies();
					minCountryArmy = tempStr;
				}
			}
			return minCountryArmy;
		}
		return countries_array[0];

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

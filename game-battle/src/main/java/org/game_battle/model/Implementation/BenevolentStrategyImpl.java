package org.game_battle.model.Implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.game_battle.model.Contract.Strategy;

public class BenevolentStrategyImpl implements Strategy{

	@Override
	public String askText(String msg, String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int askNumber(String title, String prompt, int min, int max, int numOfCountries , boolean flag) {
		// TODO Auto-generated method stub
		//for(Country countries:)
		return 0;
	}

	@Override
	public Collection getObjs(String prompt, Object[] objs) {
		// TODO Auto-generated method stub
		List list = new ArrayList(Arrays.asList(objs));
		List responseList=getRandomElement(list, 3);
		Collection collection = new ArrayList(responseList);
		System.out.println("COLLECTIONNNNNNNNNNNNN"+collection);
		return collection;
		
	}

	@Override
	public boolean isUserOk(String title, String prompt) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Country selectCountry(String title, String prompt, List<Country> countries) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public int setArmies(int NumOfArmies) {
		// TODO Auto-generated method stub
		return NumOfArmies;
	}

	

	@Override
	public Country[] doAttack(Country offendingCountry, Country deffendingCountry, int attackerDiceRoll,
			int defenderDiceRoll) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Card> getRandomElement(List<Card> list, int totalItems) {
		System.out.println("LISTTTTTTTTTTTTTTTTTTTTTTTT##############"+list);

		Random rand = new Random();

		List<Card> newList = new ArrayList<>();
		for (int i = 0; i < totalItems; i++) {

			int randomIndex = rand.nextInt(list.size());
			System.out.println("LISTTTTTTTTTTTTTTTTTTTTTTTTSIZZZZZZZZZZZE##############"+randomIndex);


			newList.add(list.get(randomIndex));

			list.remove(randomIndex);
		}
		return newList;
	}
	

}

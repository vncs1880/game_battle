package org.game_battle.model.Implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.game_battle.model.Contract.Strategy;

public class RandomStrategyImpl implements Strategy {

	@Override
	public String askText(String msg, String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int askNumber(String title, String prompt, int min, int max, int countryNumber, boolean flag) {
		// TODO Auto-generated method stub
		return getRandomNumberInRange(min, max);

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
		Random random = new Random();

		return random.nextBoolean();
	}

	@Override
	public Country selectCountry(String title, String prompt, List<Country> countries) {
		// TODO Auto-generated method stub
		Country[] countries_array = countries.toArray(new Country[0]);

		Random generator = new Random();
		int randomIndex = generator.nextInt(countries_array.length);
		System.out.println("RANDOM COUNTRY" + countries_array[randomIndex]);
		return countries_array[randomIndex];
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
		Country winner = (attackerDiceRoll > defenderDiceRoll) ? offendingCountry : deffendingCountry;
		Country loser = (attackerDiceRoll < defenderDiceRoll) ? offendingCountry : deffendingCountry;
		Country[] cn = new Country[2];
		cn[0] = winner;
		cn[1] = loser;
		return cn;

	}

	private int getRandomNumberInRange(int min, int max) {

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
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

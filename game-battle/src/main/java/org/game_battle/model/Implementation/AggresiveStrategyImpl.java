package org.game_battle.model.Implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.swing.JList;
import javax.swing.JOptionPane;

import org.game_battle.model.Contract.Strategy;

public class AggresiveStrategyImpl implements Strategy {

	@Override
	public int askNumber(String title, String prompt, int min, int max, int numOfCountries, boolean flag) {
		// TODO Auto-generated method stub
		if (title.equals("Reinforcement phase") || title.equals("Attack phase")) {
			return max;
		}

		else {
			return 0;
		}
	}

	@Override
	/*
	 * public Collection getObjs(String prompt, Object[] objs) { // TODO
	 * Auto-generated method stub List list = Arrays.asList(objs); Collection
	 * collection = new ArrayList(list); return collection;
	 * 
	 * }
	 */
	public Collection getObjs(String prompt, Object[] objs) {
		// TODO Auto-generated method stub
		List list = new ArrayList(Arrays.asList(objs));
		List responseList = getRandomElement(list, 3);
		Collection collection = new ArrayList(responseList);
		System.out.println("COLLECTIONNNNNNNNNNNNN" + collection);
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
		Country[] countries_array = countries.toArray(new Country[0]);
		Country maxCountryArmy = null;
		Country minCountryArmy = null;

		if (prompt.equals("Select attacker country")) {

			int armyCount = 0;
			for (Country tempStr : countries_array) {
				if (armyCount < tempStr.getArmies()) {
					armyCount = tempStr.getArmies();
					maxCountryArmy = tempStr;
				}
			}
			return maxCountryArmy;

		} else if (prompt.equals("Select defender country")) {

			int armyCount = 9999;
			for (Country tempStr : countries_array) {
				if (armyCount > tempStr.getArmies()) {
					armyCount = tempStr.getArmies();
					minCountryArmy = tempStr;
				}
			}
			return minCountryArmy;
		} else if (title.equals("Fortification phase")) {
			return countries_array[0];
		}
		return countries_array[0];

	}

	@Override
	public String askText(String msg, String title) {
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
		Country winner = (attackerDiceRoll > defenderDiceRoll) ? offendingCountry : deffendingCountry;
		Country loser = (attackerDiceRoll < defenderDiceRoll) ? offendingCountry : deffendingCountry;
		Country[] cn = new Country[2];
		cn[0] = winner;
		cn[1] = loser;
		return cn;
	}

	public List<Card> getRandomElement(List<Card> list, int totalItems) {
		System.out.println("LISTTTTTTTTTTTTTTTTTTTTTTTT##############" + list);

		Random rand = new Random();

		List<Card> newList = new ArrayList<>();
		for (int i = 0; i < totalItems; i++) {

			int randomIndex = rand.nextInt(list.size());
			System.out.println("LISTTTTTTTTTTTTTTTTTTTTTTTTSIZZZZZZZZZZZE##############" + randomIndex);

			newList.add(list.get(randomIndex));

			list.remove(randomIndex);
		}
		return newList;
	}

}

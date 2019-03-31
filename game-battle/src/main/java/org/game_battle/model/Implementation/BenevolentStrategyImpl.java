package org.game_battle.model.Implementation;

import java.util.Collection;
import java.util.List;

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
		return 0;
	}

	@Override
	public Collection getObjs(String prompt, Object[] objs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUserOk(String title, String prompt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Country selectCountry(String title, String prompt, List<Country> countries) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public int setArmies(int NumOfArmies) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	@Override
	public Country[] doAttack(Country offendingCountry, Country deffendingCountry, int attackerDiceRoll,
			int defenderDiceRoll) {
		// TODO Auto-generated method stub
		return null;
	}
	

}

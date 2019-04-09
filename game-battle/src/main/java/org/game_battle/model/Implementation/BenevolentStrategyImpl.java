package org.game_battle.model.Implementation;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.game_battle.model.Contract.Strategy;
import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;
import java.util.Map.*;
import org.game_battle.*;
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
	if(title.equals("Reinforcement phase")) {
		int count = 9999;
		String minArmyCountryString = "";
		for(Country s : GamePlay.playerStatic.getCountries())
		{
			if(s.getArmies() <= count)
			{
				//minArmyCountryString = s.getName();
				count = s.getArmies();
				minArmyCountryString = s.getName();
			}
		}
		
		if(minArmyCountryString.equals(Player.sCountry.getName())) {
			return max;
		}
		
		else {
			return 0;
		}
		
	}
	else {
		int count = -9999;
		Country maxArmyCountry =null;
		for(Country s : Player.sCountry.getNeighbours())
		{
			String con = s.getName();
			if(s.getArmies() > count)
			{
				count = s.getArmies();
				maxArmyCountry = s;
			}
		
	}
		
		int countOfArmiesForSelectedCountry = Player.countrySelected.getArmies();
		int armyValue = 0;
		if(countOfArmiesForSelectedCountry != count)
		{
		armyValue=count/2;
		System.out.println(armyValue);
		System.out.println(count);
		System.out.println( count-armyValue);

		int remainingArmies =  count-armyValue ;
		System.out.println(" 000000000000000000000000 "+GamePlay.playerStatic + armyValue+ "-"+ remainingArmies + maxArmyCountry);

		System.out.println("Player " + GamePlay.playerStatic.getName() + " moved " + armyValue + " army from " + maxArmyCountry.getName() + " to "
				+Player.countrySelected.getName() + " previous army qty was " + Player.countrySelected.getArmies());
		
		GamePlay.playerStatic.setArmiesForCountry(maxArmyCountry, remainingArmies);
		GamePlay.playerStatic.setArmiesForCountry(Player.countrySelected, Player.countrySelected.getArmies() +armyValue );

		System.out.println("     0000000000000" +GamePlay.playerStatic);
		
		}
		return armyValue;
	}
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
	
	public Collection getCards(Collection collection) {
		
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
		
		int count = 9999;
		Country minArmyCountry = null;
		
		for(Country s : GamePlay.playerStatic.getCountries())
		{
			if(s.getArmies() < count)
			{
				count = s.getArmies();
				minArmyCountry = s;
			}
		}
		
		
		
		return minArmyCountry;
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
		HashMap<Card, Integer> cardMap=new HashMap<Card, Integer>();
		for(Card c: list)
		{
		if(cardMap.containsKey(c))
		{
			Integer value = cardMap.get(c);
			value+=1;
			cardMap.put(c,value );
		}
		else
			cardMap.put(c,1 );

		}
		ArrayList<Card> pointCards = new ArrayList<Card>();
		Card pointCard  = null;
		Integer cardValue = 0;
		boolean threeCardflag = false;
		int counter =0 ;
		for (java.util.Map.Entry<Card, Integer> entry : cardMap.entrySet()) {
			cardValue =entry.getValue();
        	pointCard = entry.getKey();
            if (cardValue>=3) {
            	pointCards.add(pointCard);
            	pointCards.add(pointCard);
            	pointCards.add(pointCard);
            	threeCardflag = true;
            	break;
            }
            else {
            	counter+=1;
            	pointCards.add(pointCard);
        		cardMap.put(pointCard,cardValue-1);
            }

        }
	
		if(threeCardflag)
		{
			cardMap.put(pointCard,cardValue-3 );
		}
		else  if(counter < 3)
			pointCards = new ArrayList<Card>();

		return pointCards;
	}
	

}

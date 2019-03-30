package org.game_battle.model;

import org.game_battle.model.Implementation.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerTests {

	/**
	 * A series of test for testing the Player class, although executing these tests
	 * is difficult as the Player is currently closely coupled with the UI and Board
	 * class which is maybe shouldn't be.
	 */

	private Board testBoard;
	private Player p1, p2;
	private Country c1, c2, c3, c4, c5, c6, c7;
	private List<Country> p1_conts, p2_conts;
	private MapInterface mapInterface;

	@Before
	public void setup() {
		testBoard = new Board();
		// WorldMap model = new WorldMap();
		
		Map<String, ArrayList<String>> tn = new HashMap<String, ArrayList<String>>();
		ArrayList<String> neighbourListc1 = new ArrayList<String>();
		ArrayList<String> neighbourListc2 = new ArrayList<String>();
		ArrayList<String> neighbourListc3 = new ArrayList<String>();
		ArrayList<String> neighbourListc4 = new ArrayList<String>();
		ArrayList<String> neighbourListc5 = new ArrayList<String>();
		ArrayList<String> neighbourListc6 = new ArrayList<String>();

		neighbourListc1.add("c2");
		neighbourListc1.add("c3");
		neighbourListc1.add("c4");
		neighbourListc1.add("c5");
		neighbourListc1.add("c6");
		
		neighbourListc2.add("c3");
		neighbourListc2.add("c4");
		neighbourListc2.add("c5");
		neighbourListc2.add("c6");
		neighbourListc2.add("c1");
		
		neighbourListc3.add("c4");
		neighbourListc3.add("c5");
		neighbourListc3.add("c6");
		neighbourListc3.add("c1");
		neighbourListc3.add("c2");
		
		neighbourListc4.add("c5");
		neighbourListc4.add("c6");
		neighbourListc4.add("c1");
		neighbourListc4.add("c2");
		neighbourListc4.add("c3");
		
		neighbourListc5.add("c6");
		neighbourListc5.add("c4");
		neighbourListc5.add("c3");
		neighbourListc5.add("c1");
		neighbourListc5.add("c2");
		
		neighbourListc5.add("c5");
		neighbourListc5.add("c4");
		neighbourListc5.add("c1");
		neighbourListc5.add("c3");
		neighbourListc5.add("c2");
		
		tn.put("c1", neighbourListc1);
		tn.put("c2", neighbourListc2);
		tn.put("c3", neighbourListc3);
		tn.put("c4", neighbourListc4);
		tn.put("c5", neighbourListc5);
		tn.put("c6", neighbourListc6);

		testBoard.getMapinterface().getModel().setTerritoryNeighbour(tn);
		c1 = new Country("c1");
		c2 = new Country("c2");
		c3 = new Country("c3");
		c4 = new Country("c4");
		c5 = new Country("c5");
		c6 = new Country("c6");
		c7 = new Country("c7");

		p1 = new Player(testBoard, "p1");
		p1_conts = Arrays.asList(new Country[] { c1, c2, c3, c4, c5, c6 });
		p1.setCountries(p1_conts);

		p2 = new Player(testBoard, "p2");
		p2_conts = Arrays.asList(new Country[] { c7 });
		p2.setCountries(p2_conts);

	}

	/**
	 * A test to ensure that the correct number of armies is assigned to player
	 * after the Reinforcement method is executed.
	 */

	@Test
	public void reinforcementTest() {

		int countryCount = 0;
		int expectedReinforcementCount = 0;

		for (Country c : testBoard.getCountries()) {
			if (testBoard.getOwner(c).equals(p1)) {
				countryCount++;
			}
		}

		expectedReinforcementCount = Math.floorDiv(countryCount, 3);
		p1.Reinforcement();
		Assert.assertEquals(p1.getArmies(), expectedReinforcementCount);
	}

	

	@Test
	public void fortificationTest() {
		
		p1.Fortification();		
		System.out.println("##########" + p1.getArmies() + "##########");

	}

	/**
	 * Check that the correct players are assigned owners in the way they were
	 * assigned during prior to test execution.
	 */

	@Test
	public void testCountriesOwnedByPlayers() {

		p1_conts = p1.getCountries();
		p2_conts = p2.getCountries();

		Assert.assertTrue(p1_conts.size() == 6);
		Assert.assertTrue(p2_conts.size() == 1);
		Assert.assertEquals(p1_conts.get(0), c1);
		Assert.assertEquals(p1_conts.get(1), c2);
		Assert.assertEquals(p2_conts.get(0), c7);
	}

	/**
	 * Test to be completed, requires UI to be decoupled with player attack method
	 * to be executed without triggering a game loop.
	 * 
	 */

	@Test
	public void playerAttackTest_PlayerHasLessArmiesAfterBattle() {
		int previousArmyCount = p1.getArmies();
		p1.Attack();
		int nextArmyCount = p1.getArmies();
		Assert.assertTrue(nextArmyCount >= previousArmyCount);
	}

	/**
	 * playerAttackTest checks if the player has correct number of previous armies
	 */
	@Test
	public void playerAttackTest_PlayerHasCorrectNumberOfPreviousArmies() {
		int armyCountBeforeBattle = p1.getArmies();
		p1.Attack();
		int lostArmies = p1.getArmies();
		Assert.assertEquals(armyCountBeforeBattle - lostArmies, p1.getArmies());
	}

	/**
	 * playerConquerCountryAfterWinningBattles checks if the player conquers the
	 * country after winning battle
	 */
	@Test
	public void playerAttackTest_PlayerConquersCountryAfterWinningBattle() {
		Country contestedCountry = new Country(p1.toString());
		p1.Attack();
		Assert.assertTrue(p1.getCountries().contains(contestedCountry));
	}

}
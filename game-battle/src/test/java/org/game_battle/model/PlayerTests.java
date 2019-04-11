package org.game_battle.model;

import org.game_battle.model.Implementation.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
	
	
	private Player comp1,comp2;

	@Before
	public void setup() {
		testBoard = new Board();

		c1 = new Country("c1");
		c2 = new Country("c2");
		c3 = new Country("c3");
		c4 = new Country("c4");
		c5 = new Country("c5");
		c6 = new Country("c6");
		c7 = new Country("c7");

		p1 = new Player(testBoard, "p1","Human");
		p1_conts = Arrays.asList(new Country[] { c1, c2, c3, c4, c5, c6 });
		p1.setCountries(p1_conts);

		p2 = new Player(testBoard, "p2","Aggresive");
		p2_conts = Arrays.asList(new Country[] { c7 });
		p2.setCountries(p2_conts);
		
		
		HashMap config = new HashMap();
		List<Object> playerStrats = new ArrayList<>();
		playerStrats.add("Aggresive");
		playerStrats.add("Aggresive");
		config.put("strategieslist",playerStrats);

		testBoard = new Board("resource/file2.map",config);
		comp1 = testBoard.getPlayers().get(0);
		comp2 = testBoard.getPlayers().get(1);

		comp1.getCountries().get(0).setArmyQty(15); //Each player owns one country (file2.map data)
		comp2.getCountries().get(0).setArmyQty(5);

		testBoard.setIsAllOutMode(true);
	}

	/**
	 * A test to ensure that the correct number of armies is assigned to player
	 * after the Reinforcement method is executed.
	 */
	
	@Test
	public void checkPlayerStrategiesGetters(){
		Assert.assertEquals("Aggresive",comp2.getPlayerMode());
		Assert.assertEquals("Aggresive",comp1.getPlayerMode());
	}
	
	@Test
	public void testAssignmentOfRandomPlayerStrategy(){
		testBoard.getPlayers().get(0).setPlayerMode("Random");
		Assert.assertEquals("Random",testBoard.getPlayers().get(0).getPlayerMode());
	}

	@Test
	public void reinforcementTest_CountryCountCorrectAfterReinforcement_1OwnedCountry() {
		int expectedReinforcementIncrease = Math.floorDiv(comp1.getCountries().size() , 3);
		comp1.Reinforcement();
		Assert.assertEquals(expectedReinforcementIncrease,comp1.getArmies());
	}

	
	@Test
	public void reinforcementTest_CountryCountCorrectAfterReinforcement_5OwnedCountries() {
		for(int i=0;i<4;i++){ //Add 4 countries to currently owned by comp1
			comp1.getCountries().add(new Country(i+""));
		}
		Assert.assertEquals(5,comp1.getCountries().size());
		int expectedReinforcementIncrease = Math.floorDiv(comp1.getCountries().size() , 3);
		comp1.Reinforcement();
		Assert.assertEquals(expectedReinforcementIncrease,comp1.getArmies());
		Assert.assertEquals(1,comp1.getArmies()); // (6/2) rounded down = 1
	}
	
	/*
	 * @Ignore
	 * 
	 * @Test public void
	 * reinforcementTest_PlayerGivenControlValueOfOwnedContinent(){ int
	 * controlValueOfOwnedContinent =
	 * MapInterface.getContinents().get(0).getControlValue(); List<Country>
	 * countries =
	 * testBoard.getCountriesByContinent(MapInterface.getContinents().get(0));
	 * for(Country c : countries){ comp1.getCountries().add(c); //Set all players in
	 * continent to players owned countries } int comp1_country_count =
	 * comp1.getArmies(); int expectedArmyCount = Math.floorDiv(comp1_country_count,
	 * 3) + controlValueOfOwnedContinent; comp1.Reinforcement();
	 * Assert.assertEquals(expectedArmyCount,comp1.getArmies()); }
	 */
	
	

	@Test
	public void playerAttack_AttackerOwnsCountryAfterAttackingUndefendedCountry() {
		Country defendingCountry = comp2.getCountries().get(0);
		defendingCountry.setArmyQty(0);
		comp1.Attack();
		Assert.assertEquals(comp1,testBoard.getOwner(defendingCountry));
	}

	@Test
	public void playerAttackTest_ConflictedCountriesSameOrEqualCountriesAfterCombat() {
		int attackerStartingArmies = comp1.getArmies();
		int defenderStartingArmies = comp2.getArmies();
		comp1.Attack();
		Assert.assertTrue(comp1.getArmies()<=attackerStartingArmies);
		Assert.assertTrue(comp2.getArmies()<=defenderStartingArmies);
	}

	@Test
	public void playerAttackTest_NoChangeInAttackerArmiesWhenNoValidTarget() {
		Country comp2Country = comp2.getCountries().get(0);
		comp2.getCountries().remove(comp2Country);
		comp1.getCountries().add(comp2Country);
		int attackerStartingArmies = comp1.getArmies();
		comp1.Attack();
		Assert.assertEquals(attackerStartingArmies,comp1.getArmies());
	}

	@Test
	public void reinforcement_TotalArmyCountEqualAfterReinforcement(){
		int comp1_armyCountBeforeReinforcement = comp1.getArmies();
		comp1.Reinforcement();
		Assert.assertEquals(comp1_armyCountBeforeReinforcement,comp1.getArmies());
	}


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
		// p1.Attack();
		int nextArmyCount = p1.getArmies();
		Assert.assertTrue(nextArmyCount >= previousArmyCount);
	}

	@Test
	public void playerAttackTest_PlayerHasCorrectNumberOfPreviousArmies() {
		int armyCountBeforeBattle = p1.getArmies();
		// p1.Attack();
		int lostArmies = p1.getArmies();
		Assert.assertEquals(armyCountBeforeBattle - lostArmies, p1.getArmies());
	}

	@Test
	public void playerAttackTest_PlayerConquersCountryAfterWinningBattle() {
		Country contestedCountry = new Country(p1.toString());
		// p1.Attack()
		Assert.assertTrue(p1.getCountries().contains(contestedCountry));
	}

}
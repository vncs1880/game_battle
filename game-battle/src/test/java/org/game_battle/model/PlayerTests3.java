package org.game_battle.model;

import org.game_battle.model.Contract.Continent;
import org.game_battle.model.Implementation.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

public class PlayerTests3 {

	/**
	 * A series of test for testing the Player class, although executing these tests
	 * is difficult as the Player is currently closely coupled with the UI and Board
	 * class which is maybe shouldn't be.
	 */

	private Board testBoard;
	private Player comp1,comp2;

	@Before
	public void setup() {

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

	/**
	 * A test to ensure that the correct number of armies is assigned to player
	 * after the Reinforcement method is executed.
	 */

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

	@Test
	public void reinforcementTest_PlayerGivenControlValueOfOwnedContinent(){
		int controlValueOfOwnedContinent =  MapInterface.getContinents().get(0).getControlValue();
		List<Country> countries = testBoard.getCountriesByContinent(MapInterface.getContinents().get(0));
		for(Country c : countries){
			comp1.getCountries().add(c); //Set all players in continent to players owned countries
		}
		int comp1_country_count = comp1.getArmies();
		int expectedArmyCount = Math.floorDiv(comp1_country_count, 3) + controlValueOfOwnedContinent;
		comp1.Reinforcement();                                                                                                              
		Assert.assertEquals(expectedArmyCount,comp1.getArmies());
	}

	/**
	 * Check that the correct players are assigned owners in the way they were
	 * assigned during prior to test execution.
	 */

	@Test
	public void testCountriesOwnedByPlayers() {

		List comp1_countries = new ArrayList();
		List comp2_countries = new ArrayList();

		for(Country c : MapInterface.getCountries()){
			if(testBoard.getOwner(c).equals(comp1)){
				comp1_countries.add(c);
			}

			if(testBoard.getOwner(c).equals(comp2)){
				comp2_countries.add(c);
			}
		}

		Assert.assertEquals(null,comp1.getOwnedContinent());
		Assert.assertEquals(null,comp2.getOwnedContinent());
		Assert.assertEquals(comp1_countries,comp1.getCountries());
		Assert.assertEquals(comp2_countries,comp2.getCountries());
	}


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


}
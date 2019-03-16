package org.game_battle.modelutility;

import org.game_battle.model.Implementation.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple tests executed to verify functionality of various methods in the class
 * and continent classes.
 */

public class CountryContinentTests {

	Continent cont1, cont2;
	Country c1, c2, c3;

	/**
	 * Executed before executing unit tests.
	 */

	@Before
	public void setupCountriesandContinents() {

		cont1 = new Continent(10, "Cont1");
		cont2 = new Continent(20, "Cont2");

		c1 = new Country("c1");
		c2 = new Country("c2");
		c3 = new Country("c3");

		c1.setArmyQty(3);
		c2.setArmyQty(2);
		c3.setArmyQty(1);
	}

	/**
	 * Ensure all continents and countries are intialised (not null).
	 */

	@Test
	public void containersNotNull() {
		Assert.assertNotNull(cont1);
		Assert.assertNotNull(cont2);
		Assert.assertNotNull(c1);
		Assert.assertNotNull(c2);
		Assert.assertNotNull(c3);
	}

	/**
	 * Validate that getters provide correct values for army quantities after
	 * invocation of setArmyQty.
	 */

	@Test
	public void checkArmyQuantities() {
		Assert.assertEquals(c1.getArmies(), 3);
		Assert.assertEquals(c2.getArmies(), 2);
		Assert.assertEquals(c3.getArmies(), 1);
		c3.setArmyQty(3);
		Assert.assertEquals(c3.getArmies(), 3);

	}

	/**
	 * Validate the control values of continents are correctly assigned on
	 * construction of continents.
	 */

	@Test
	public void verifyContorlValue() {
		Assert.assertEquals(10, cont1.getControlValue());
		Assert.assertEquals(20, cont2.getControlValue());
	}

}

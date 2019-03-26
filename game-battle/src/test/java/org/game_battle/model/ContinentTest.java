package org.game_battle.model;

import org.game_battle.model.Contract.Territory;
import org.game_battle.model.Implementation.Continent;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class ContinentTest {

	Continent continent_Asia, continent_Africa;
	String continent_Asia_Name, continent_Africa_Name;
	int continent_Asia_ControlValue, continent_Africa_ControlValue;
	
	@Before
	public void setup() {
		
		continent_Asia_ControlValue = 20;
		continent_Africa_ControlValue = 15;
		
		continent_Asia_Name = "Asia";
		continent_Africa_Name = "Africa";

		continent_Asia = new Continent(continent_Asia_ControlValue,continent_Asia_Name);
		continent_Africa = new Continent(continent_Africa_ControlValue,continent_Africa_Name);
	}
	
	
	@Test
	public void continentsNotNull() {
		Assert.assertNotNull(continent_Africa);
		Assert.assertNotNull(continent_Asia);
	}
	
	@Test
	public void continentsControlValueToValuesPassedInConstructor() {
		Assert.assertEquals(continent_Asia_ControlValue,continent_Asia.getControlValue());
		Assert.assertEquals(continent_Africa_ControlValue, continent_Africa.getControlValue());
		
	}
	
	@Test
	public void differentContinentsAreNotEqual() {
		Assert.assertFalse(continent_Africa.equals(continent_Asia));
	}

	@Test
	public void toString_ContainsContinentName() {
		Assert.assertTrue(continent_Africa.toString().contains(continent_Africa_Name));
		Assert.assertTrue(continent_Asia.toString().contains(continent_Asia_Name));
	}

	
	
}

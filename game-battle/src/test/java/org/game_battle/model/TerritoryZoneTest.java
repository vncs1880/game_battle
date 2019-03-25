package org.game_battle.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.game_battle.model.Contract.Territory;
import org.game_battle.model.Implementation.TerritoryZone;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class TerritoryZoneTest {

	TerritoryZone zone;
	ArrayList<String> adjacentTerritories;
	String territoryName, xCoordinate, yCoordinate, continentName;
	
	List<TerritoryZone> testTerritories;
	Territory t1,t2,t3;
	
	@Before
	public void setup() {
	
		adjacentTerritories = new ArrayList<String>(); 
		adjacentTerritories.addAll(Arrays.asList("Territory1", "Territory2"));
		
		territoryName = "TestTerritory";
		continentName = "TestContinent";
		
		xCoordinate = "20";
		yCoordinate = "15";
		
		zone = new TerritoryZone(continentName,territoryName,xCoordinate, yCoordinate, adjacentTerritories);
	}
	
	@Test
	public void zoneObjectsNotNull() {
		Assert.assertNotNull(zone);
		Assert.assertNotNull(zone.getTerritoryName());
		Assert.assertNotNull(zone.getAdjacentTerritories());
		Assert.assertNotNull(zone.getxCoordinates());
		Assert.assertNotNull(zone.getyCoordinates());
		Assert.assertNotNull(zone.getContinentName());
		Assert.assertNotNull(zone.toString());
	}
	
	@Test
	public void getterReturnedVauesEqualValuesPassedOnConstruction() {
		Assert.assertEquals(territoryName,zone.getTerritoryName());
		Assert.assertEquals(xCoordinate, zone.getxCoordinates());
		Assert.assertEquals(yCoordinate,zone.getyCoordinates());
		Assert.assertEquals(adjacentTerritories,zone.getAdjacentTerritories());
	}
	
	@Test
	public void toString_ContainsTerritoryName() {
		String toStringOutput = zone.toString();
		Assert.assertTrue(toStringOutput.contains(territoryName));
	}
	
	@Test
	public void toString_ContainsContinentName() {
		String toStringOutput = zone.toString();
		Assert.assertTrue(toStringOutput.contains(continentName));
	}
	
	@Test
	public void toString_ContainsTerritoryAdjacentCountries() {
		String toStringOutput = zone.toString();
		for(String adjacent: adjacentTerritories) {
			Assert.assertTrue(toStringOutput.contains(adjacent));
		}
	}
	
	
	@Test
	public void returnedCoordinatesCanBeParsedToInt() {
		int CoordinateX = Integer.parseInt(xCoordinate);
		int CoordinateY = Integer.parseInt(yCoordinate);
		
		Assert.assertEquals(20, CoordinateX);
		Assert.assertEquals(15, CoordinateY);
	}
	
	
	@Test
	public void setAdjacentTerritories_NotNull() {
		Assert.assertNotNull(zone.getAdjacentNeighbours());
	}
	
	@Test
	public void hasAdjacency_ReturnsFalseWithNonAdjacentTerritory() {
		Assert.assertFalse(zone.hasAdjacencyWith(new TerritoryZone("Territory63","2","2","",new ArrayList())));
	}
	
	@Test
	public void hasAdjacency_ReturnsTrueAdjacentTerritory() {
		Assert.assertTrue(zone.hasAdjacencyWith(new TerritoryZone("Territory1","2","2","",new ArrayList())));
	}
	
	@Test
	public void isInstanceOf_Territory() {
		Assert.assertTrue(zone instanceof Territory);
	}
	
	
}

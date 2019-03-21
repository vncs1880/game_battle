package org.game_battle.model;

import org.game_battle.model.Implementation.Card;
import org.game_battle.model.Implementation.Card.Sort;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class CardTest {

	Card ArtilleryCard, InfantryCard, CavalryCard;
	
	@Before
	public void setup() {
		ArtilleryCard = new Card(Card.Sort.ARTILLERY);
		InfantryCard = new Card(Card.Sort.INFANTRY);
		CavalryCard = new Card(Card.Sort.CAVALRY);
		
	}
	
	@Test
	public void cardsNotNull() {
		Assert.assertNotNull(ArtilleryCard);
		Assert.assertNotNull(CavalryCard);
		Assert.assertNotNull(InfantryCard);
	}
	
	@Test
	public void getType_CorrectCardValues() {
		Assert.assertEquals(Card.Sort.ARTILLERY,ArtilleryCard.getType());
		Assert.assertEquals(Card.Sort.CAVALRY,CavalryCard.getType());
		Assert.assertEquals(Card.Sort.INFANTRY,InfantryCard.getType());
	}
	
	@Test
	public void toString_CorrectFormat() {
		Assert.assertEquals("ARTILLERY",ArtilleryCard.toString());
		Assert.assertEquals("CAVALRY",CavalryCard.toString());
		Assert.assertEquals("INFANTRY",InfantryCard.toString());
	}
	
	

	
	
}

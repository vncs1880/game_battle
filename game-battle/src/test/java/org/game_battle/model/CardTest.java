package org.game_battle.model;

import org.game_battle.model.Implementation.Card;


import org.game_battle.model.Implementation.Card.Sort;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

/**
 * CardTest test the card types
 * @author 91950
 *
 */

public class CardTest {

	Card ArtilleryCard, InfantryCard, CavalryCard;
	
	@Before
	public void setup() {
		ArtilleryCard = new Card(Card.Sort.ARTILLERY);
		InfantryCard = new Card(Card.Sort.INFANTRY);
		CavalryCard = new Card(Card.Sort.CAVALRY);
		
	}
	/**
	 * cardsNotNull checks if the cards are not null
	 */
	
	@Test
	public void cardsNotNull() {
		Assert.assertNotNull(ArtilleryCard);
		Assert.assertNotNull(CavalryCard);
		Assert.assertNotNull(InfantryCard);
	}
	/**
	 * Type_CorrectCardValues gets the correct type cards
	 */
	
	@Test
	public void getType_CorrectCardValues() {
		Assert.assertEquals(Card.Sort.ARTILLERY,ArtilleryCard.getType());
		Assert.assertEquals(Card.Sort.CAVALRY,CavalryCard.getType());
		Assert.assertEquals(Card.Sort.INFANTRY,InfantryCard.getType());
	}
	
	/**
	 * toString_CorrectFormat gets the correct format of cards
	 */
	
	@Test
	public void toString_CorrectFormat() {
		Assert.assertEquals("ARTILLERY",ArtilleryCard.toString());
		Assert.assertEquals("CAVALRY",CavalryCard.toString());
		Assert.assertEquals("INFANTRY",InfantryCard.toString());
	}
	
	/**
	 * Sort_Enum_CorrectSize gets the correct size of cards
	 */
	
	@Test
	public void Sort_Enum_CorrectSize() {
		Assert.assertEquals(3, Card.Sort.values().length);
	}
	
	/**
	 * Sort_Enum_NonEqualValues checks the values of cards
	 */
	
	@Test
	public void Sort_Enum_NonEqualValues() {
		Assert.assertFalse(Card.Sort.ARTILLERY.equals(Card.Sort.CAVALRY));
		Assert.assertFalse(Card.Sort.CAVALRY.equals(Card.Sort.ARTILLERY));
		Assert.assertFalse(Card.Sort.INFANTRY.equals(Card.Sort.ARTILLERY));
	}
	
	

	
	
}

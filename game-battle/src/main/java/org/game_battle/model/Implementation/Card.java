
package org.game_battle.model.Implementation;

import java.util.List;

/**
 * Allows the creation of Card objects.
 * 
 * @author Vini
 * @version Alpha
 * 
 **/
public class Card {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getType().toString();// super.toString();
	}

	/**
	 * Card Constructor
	 * 
	 * @param type card type
	 */
	public Card(Sort type) {
		super();
		this.type = type;
	}

	public enum Sort {// infantry, cavalry, or artillery
		INFANTRY, CAVALRY, ARTILLERY
	}

	private Sort type;

	/**
	 * Method to get the type of the card
	 * 
	 * @return type of the card
	 *
	 */
	public Sort getType() {
		return type;
	}

}

/**
 * 
 */
package org.game_battle.gameplay;

import java.util.List;

/**
 * @author vncs
 *
 */
public class Card {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getType().toString();//super.toString();
	}

	/**
	 * @param type
	 */
	public Card(Sort type) {
		super();
		this.type = type;
	}

	/**
		 * @author vncs
		 *
		 */
	public enum Sort {//infantry, cavalry, or artillery
		INFANTRY,
		CAVALRY,
		ARTILLERY
	}

	private Sort type;

	public Sort getType() {
		return type;
	}

}

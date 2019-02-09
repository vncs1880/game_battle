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
		// TODO Auto-generated method stub
		return type;
	}

}

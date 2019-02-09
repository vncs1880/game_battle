/**
 * 
 */
package org.game_battle.gameplay;

import java.util.List;

/**
 * @author voda
 *
 */
public class Country {

	private int armies;

	/**
	 * 
	 */
	public Country() {
		// TODO Auto-generated constructor stub
	}

	public void setArmyQty(int resp) {
		armies = resp;
	}

	public int getArmies() {
		// TODO Auto-generated method stub
		return armies;
	}

	public List<Country> getNeighbours() {
		// TODO Auto-generated method stub
		return null;
	}

}

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
    }

	public void setArmyQty(int n_armies) {
		armies = n_armies;
	}

	public int getArmies() {
		return armies;
	}

	public List<Country> getNeighbours() {
		return MapInterface.getNeighbours(this);
	}



}

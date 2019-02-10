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

	public void setArmyQty(int n_armies) {
		armies = n_armies;
	}

	public int getArmies() {
		return armies;
	}

	public List<Country> getNeighbours() {
		// TODO Auto-generated method stub
		return MapInterface.getNeighbours(this);
	}



}

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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name + " armies: "+armies;//super.toString();
	}
	private int armies;
	private String name;
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
     * @param name 
     *
     */
    public Country(String name) {
    	this.name = name;
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

package org.game_battle.gameplay;

/**
 * Allows the creation of Continent objects.
 * 
 * @author Vini
 * @version Alpha
 * @date 5/02/19
 **/
public class Continent {
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;// super.toString();
	}

	private int controlValue;
	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param ctrlValue control value associated with the continent
	 * @param name      Name of the continent
	 * 
	 */
	public Continent(int ctrlValue, String name) {
		controlValue = ctrlValue;
		this.name = name;
	}

	/**
	 * @ return Returns the control value associated with a continent
	 * 
	 **/
	public int getControlValue() {
		return controlValue;
	}

}
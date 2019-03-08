package org.game_battle.model.Implementation;

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
	 * Method to get name of the continent
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Continent Constructor
	 * 
	 * @param ctrlValue control value associated with the continent
	 * @param name      Name of the continent
	 * 
	 */
	public Continent(int ctrlValue, String name) {
		controlValue = ctrlValue;
		this.name = name;
	}

	/**
	 * Method to get control value belongs to a continent
	 * 
	 * @return Returns the control value associated with a continent
	 * 
	 **/
	public int getControlValue() {
		return controlValue;
	}

}
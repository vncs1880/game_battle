package org.game_battle.model.Implementation;

/**
 * Allows the creation of Continent objects.
 * 
 * @author Vini
 * @version Alpha
 * 
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
	 * @return name name of the continent
	 */
	public String getName() {
		return name;
	}

	/**
	 * Continent Constructor
	 * 
	 * @param ctrlValue  control value
	 * @param name     name continent
	 *  
	 */
	public Continent(int ctrlValue, String name) {
		controlValue = ctrlValue;
		this.name = name;
	}

	/**
	 * Method to get control value belongs to a continent
	 * 
	 * @return controlValue control value of the continent
	 * 
	 **/
	public int getControlValue() {
		return controlValue;
	}

}
package org.game_battle.gameplay;

public class Continent {
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;//super.toString();
	}

	private int controlValue;
	private String name;
	
	public Continent(int ctrlValue, String name) {
		controlValue = ctrlValue;
		this.name = name;
	}

	public int getControlValue() {
		return controlValue;
	}

}
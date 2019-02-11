/**
 * 
 */
package org.game_battle.gameplay;

import java.util.List;

/**
 * @author vncs
 *
 */
public class UI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	public static boolean isUserOk(String string) {
		// TODO always true here
		return true;
	}

	public static int askNumber(String string) {
		// TODO always returning 1
		return 1;
	}

	public static Country selectCountry(String prompt, List<Country> countries) {
		// TODO always returning 1st country in the list
		return countries.get(0);
	}

}

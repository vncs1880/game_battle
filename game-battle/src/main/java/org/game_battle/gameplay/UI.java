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
		// TODO Auto-generated method stub

	}

	public static boolean isUserOk(String string) {
		// TODO Auto-generated method stub
		return true;
	}

	public static int askNumber(String string) {
		// TODO always returning 1
		return 1;
	}

	public static Country select(String prompt, List<Country> countries) {
		// TODO always returning 1st country in the list
		return countries.get(0);
	}

}

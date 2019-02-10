/**
 * 
 */
package org.game_battle.gameplay;

import java.util.Arrays;
import java.util.List;

/**
 * @author vncs
 *
 */
public class MapInterface {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static List<Country> getCountries() {
		// TODO Auto-generated method stub
		return Arrays.asList(new Country(),new Country(),new Country(),new Country());
	}

	public static List<Continent> getContinents() {
		// TODO Auto-generated method stub
		return Arrays.asList(new Continent(10),new Continent(20),new Continent(30),new Continent(40));
	}

	public static List<Country> getCountriesByContinent(Continent continent) {
		// TODO Auto-generated method stub
		return null;
	}

}

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

	private static List<Continent> continents;
	private static List<Country> countries;
	private static List<List<Country>> countriesByContinent;

	/**
	 * ADDING DUMMY VALUES HERE JUST FOR DEBUGGING THE SYSTEM
	 * THESE SHOULD COME FROM USER MAP CREATION/EDITION
	 * I.E. INSTANTIATE A Country BASED ON THE HASHMAP KEY/VALUE FROM WORLDMAP.JAVA
	 */
	public MapInterface(/* OR THE CONSTRUCTOR MAY RECEIVE THESE LISTS
						 * List<Continent> continents, 
						 * List<Country> countries, 
						 * List<List<Country>> countriesByContinent
						 */) {
		super();
		countries = Arrays.asList(new Country(),new Country(),new Country(),new Country(),new Country(),new Country(),new Country(),new Country(),new Country(),new Country());
		continents = Arrays.asList(new Continent(10),new Continent(20),new Continent(30),new Continent(40));
		countriesByContinent = Arrays.asList(
				Arrays.asList(countries.get(0), countries.get(1)),
				Arrays.asList(countries.get(2), countries.get(3)),
				Arrays.asList(countries.get(4), countries.get(5)),
				Arrays.asList(countries.get(6), countries.get(7), countries.get(8), countries.get(9))
				);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {


	}

	public static List<Country> getCountries() {
		// TODO return all territories
		return countries;
	}

	public static List<Continent> getContinents() {
		// TODO return all continents
		return continents;
	}

	public static List<Country> getCountriesByContinent(Continent continent) {
		// TODO return all countries in a given continent
		return countriesByContinent.get(continents.indexOf(continent));
	}

	public static List<Country> getNeighbours(Country country) {
		// TODO return all adjacent countries
		return countries.subList(0, 3);
	}

}

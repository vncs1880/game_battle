/**
 * 
 */
package org.game_battle.gameplay;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * This class sets up the map interface for the game
 * 
 * @author Vini
 * @version Alpha
 * @date 5/02/19
 **/
public class MapInterface {

	private static List<Continent> continents;
	private static List<Country> countries;
	private static List<List<Country>> countriesByContinent;

	/**
	 * ADDING DUMMY VALUES HERE JUST FOR DEBUGGING THE SYSTEM THESE SHOULD COME FROM
	 * USER MAP CREATION/EDITION I.E. INSTANTIATE A Country BASED ON THE HASHMAP
	 * KEY/VALUE FROM WORLDMAP.JAVA
	 */
	public MapInterface(/*
						 * OR THE CONSTRUCTOR MAY RECEIVE THESE LISTS List<Continent> continents,
						 * List<Country> countries, List<List<Country>> countriesByContinent
						 */) {
		super();
		countries = new LinkedList<Country>(Arrays.asList(new Country("ca"), new Country("cb"), new Country("cc"),
				new Country("cd"), new Country("ce"), new Country("cf"), new Country("cg"), new Country("ch"),
				new Country("ci"), new Country("cj")));
		continents = new LinkedList<Continent>(Arrays.asList(new Continent(10, "cta"), new Continent(20, "ctb"),
				new Continent(30, "ctc"), new Continent(40, "ctd")));
		countriesByContinent = Arrays.asList(Arrays.asList(countries.get(0), countries.get(1)),
				Arrays.asList(countries.get(2), countries.get(3)), Arrays.asList(countries.get(4), countries.get(5)),
				Arrays.asList(countries.get(6), countries.get(7), countries.get(8), countries.get(9)));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	/**
	 * 
	 * @return list of countries
	 */
	public static List<Country> getCountries() {
		// TODO return all territories
		return countries;
	}

	/**
	 * 
	 * @return list of contitnents
	 */
	public static List<Continent> getContinents() {
		// TODO return all continents
		return continents;
	}

	/**
	 * @param continent name of the continent
	 * @return list of countries belongs to a continent
	 */

	public static List<Country> getCountriesByContinent(Continent continent) {
		// TODO return all countries in a given continent
		return countriesByContinent.get(continents.indexOf(continent));
	}

	/**
	 * @param country country name
	 * @return neighbouring country associated with the country passed
	 */
	public static List<Country> getNeighbours(Country country) {
		// TODO return all adjacent countries
		return countries.subList(0, 3);
	}

}

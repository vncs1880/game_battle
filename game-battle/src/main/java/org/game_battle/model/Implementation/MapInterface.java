
package org.game_battle.model.Implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game_battle.Constant;
import org.game_battle.controler.MapController;
import org.game_battle.model.Implementation.ContinentZone;
import org.game_battle.model.Implementation.TerritoryZone;
import org.game_battle.model.Implementation.WorldMap;
import org.game_battle.utility.FileReaderWriter;
import org.game_battle.utility.MapDataExtractor;
import org.game_battle.view.WorldMapView;

/**
 * This class sets up the map interface for the game
 * 
 * @author Vini
 * @version Alpha
 *
 **/
public class MapInterface {
	private  final Logger LOG = LogManager.getLogger(MapInterface.class);

	private  List<Continent> continents;
	private  List<Country> countries;
	private  List<List<Country>> countriesByContinent;
	private  WorldMap model = null;
	
	

	public  WorldMap getModel() {
		return model;
	}

	public  void setModel(WorldMap model) {
		this.model = model;
	}

	public MapInterface() {
		super();
		/*
		 * countries = new LinkedList<Country>(Arrays.asList(new Country("ca"), new
		 * Country("cb"), new Country("cc"), new Country("cd"), new Country("ce"), new
		 * Country("cf"), new Country("cg"), new Country("ch"), new Country("ci"), new
		 * Country("cj")));
		 */
		/*
		 * continents = new LinkedList<Continent>(Arrays.asList(new Continent(10,
		 * "cta"), new Continent(20, "ctb"), new Continent(30, "ctc"), new Continent(40,
		 * "ctd")));
		 */
		/*
		 * countriesByContinent = Arrays.asList(Arrays.asList(countries.get(0),
		 * countries.get(1)), Arrays.asList(countries.get(2), countries.get(3)),
		 * Arrays.asList(countries.get(4), countries.get(5)),
		 * Arrays.asList(countries.get(6), countries.get(7), countries.get(8),
		 * countries.get(9)));
		 */
		
		model  = new WorldMap();
		WorldMapView view = new WorldMapView();
		MapController controller = new MapController(view, model);
		//Load Map Method
		controller.loadMap(view);
		//Print Map Method
		controller.printMap(view, model);
		//Edit Map condition
		if(!view.isAddMap())
		{
			if(view.intiateMapEdit())
			{
				//Edit Map Method
				controller.editMap(view, model);
				//Print Map Mathod
				controller.printMap(view, model);
			}
		}
		countries = getCountriesFromTerritories(model.getTerritories());
		continents = getContinentFromContinentZone(model.getContinents());
		
	}
	
	public MapInterface(String mapPath) {//overloaded for tournament mode
		super();
		//synchronized (this) {
			model = new WorldMap();
			ArrayList<String> mapData = new ArrayList<String>();
			mapData = FileReaderWriter.readFile(mapPath);
			MapDataExtractor.extractData(mapData, model);

			countries = getCountriesFromTerritories(model.getTerritories());
			continents = getContinentFromContinentZone(model.getContinents());			
		//}

	}

	/**
	 * getContinentFromContinentZone gets the continents from the continent zone
	 * @param continents2
	 * @return cts continents
	 */

	private LinkedList<Continent> getContinentFromContinentZone(List<ContinentZone> continents2) {
		LinkedList<Continent> cts = new LinkedList<>();
		for (ContinentZone continentZone : continents2) {
			String continentName = continentZone.getContinentName();
			Continent ct = new Continent(model.getContinentValues().get(continentName), continentName);
			cts.add(ct);
		}
		return cts;
	}
/**
 * getCountriesFromTerritories gets the countries from the terriotries
 * @param territories
 * @return countries countries from territories
 */
	private List<Country> getCountriesFromTerritories(List<TerritoryZone> territories) {
		LinkedList<Country> countries = new LinkedList<Country>();
		for (TerritoryZone t : territories) {
			Country c = new Country(t.getTerritoryName());
			c.setMapInterface(this);
			countries.add(c);
			//c.add(new Country(t));
		}
		return countries;
	}
	/**
	 * getCountries gets the list of countries
	 * @return countries list of countries
	 */
	public  List<Country> getCountries() {
		return countries;
	}

	/**
	 * getContinents gets the list of continents
	 * @return continents list of contitnents
	 */
	public  List<Continent> getContinents() {
		return continents;
	}

	/**
	 * getCountriesByContinents gets the countries by continents
	 * @param continent continent name
	 * @return continent
	 */

	public  List<Country> getCountriesByContinent(Continent continent) {
		return  getCountriesByContinentFromTerritoryZone(continent.getName());//countriesByContinent.get(continents.indexOf(continent));
	}
/**
 * getCountriesByContinentFromTerritoryZone gets the countries by the continent from territory zone
 * @param name
 * @return countrylist list of countries
 */
	private  List<Country> getCountriesByContinentFromTerritoryZone(String name) {
		List<Country> countrylist =  new LinkedList<Country>();
		List<TerritoryZone> l = model.getCountriesByContinent(name);
		if (l == null) {
			l = new ArrayList<TerritoryZone>();
		}
		for (TerritoryZone territoryZone : l) {
			Country countryByName = getCountryByName(territoryZone.getTerritoryName());
			if (countryByName != null) {
				countrylist.add(countryByName);
			}
		}
		return countrylist;
	}

	/**
	 * getCountryByName gets the countries name list
	 * @param name
	 * @return  c country name
	 */
	private  Country getCountryByName(String name) {
		for (Country c : countries) {
			//LOG.info("Name: "+name+" Country: "+c);
			if (name.equalsIgnoreCase(c.getName())) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * getNeighbours gets the neighbours list 
	 * @param country country names
	 * @return countrylist
	 */
	public  List<Country> getNeighbours(Country country) {
		List<Country> countrylist = new LinkedList<Country>();
		ArrayList<String> l = model.getTerritoryNeighbour(country.getName());
		if (l == null) {
			l = new ArrayList<String>();
		}
		LOG.debug("Territory neighbours: "+l);
		for (String countryname : l) {
			Country countryByName = getCountryByName(countryname);
			if (countryByName != null) {
				countrylist.add(countryByName);
			} 
		}
		
		return countrylist;
	}

}


/*

public  List<Country> getCountries()   							=> 	public List<TerritoryZone> getTerritories()
public  List<Continent> getContinents() 							=>	public List<ContinentZone> getContinents()
public  List<Country> getCountriesByContinent(Continent continent)=>	public List<TerritoryZone> getCountriesByContinent(String continent) 
public  List<Country> getNeighbours(Country country) 				=>  public  ArrayList<String> getTerritoryNeighbour(String territory )

*/	


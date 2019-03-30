package org.game_battle.controler;

import java.util.ArrayList;

import java.util.Map;

import org.game_battle.Constant;
import org.game_battle.model.Implementation.WorldMap;
import org.game_battle.utility.FileReaderWriter;
import org.game_battle.utility.MapDataExtractor;
import org.game_battle.view.WorldMapView;
import org.game_battle.Enums.MapView.userAction;

/**
 * MapController Class for loadMap,printMap, EditMap and Check Map Connectivtity
 * methods.
 * 
 * @author basant
 * @version Alpha
 *
 */

public class MapController {

	private WorldMap model;
	private WorldMapView view;

	/**
	 * Mapcontroller Constructor
	 * 
	 * @param view view of the world map
	 * @param model model of map
	 */
	public MapController(WorldMapView view, WorldMap model) {
		this.view = view;
		this.model = model;
	}

	/**
	 * loadMap is to load the existing Map or to create a new map and display the
	 * new created Map.
	 * 
	 * @param view view of the world map
	 */
	public void loadMap(WorldMapView view) {
		view.initiateMapLoad();
		boolean isAddMap = view.isAddMap();
		ArrayList<String> mapData = new ArrayList<String>();
		if (isAddMap) {
			view.setInputMapData();
			mapData = view.getInputMapData();
		} else
			mapData = FileReaderWriter.readFile(Constant.ReadFilePATH);

		MapDataExtractor.extractData(mapData, model);

		if (isAddMap) {
			FileReaderWriter.writeFile(Constant.WriteFilePATH, model);
		}

	}

	/**
	 * editMap edits the map : continent control value or editing neighbours or
	 * deleting neighbours
	 * 
	 * @param view view of the world map
	 * @param map map object
	 */
	public void editMap(WorldMapView view, WorldMap map) {
		userAction value = view.editMap();
		if (value == userAction.EDIT_CONTINENT_VALUE) {
			map.updateContinent(view.editContinentValue());
		} else if (value == userAction.ADD_NEIGHBOURS) {
			map.updateNeighbours(view.editNeighbours());
		} else if (value == userAction.REMOVE_NEIGHBOURS) {
			map.removeNeighbours(view.removeNeighbours());

		}

		else
			System.out.println("error");

		FileReaderWriter.writeFile(Constant.ReadFilePATH, map);

	}

	/**
	 * checkcorrectnessOfMap is to validate the map with respect to its neighbours.
	 * 
	 * @param tn territory neighbours
	 */
	public void checkCorrectnessOfMap(Map<String, ArrayList<String>> tn) {
		view.validateMap(tn);
	}

	/**
	 * printMap is to print the map to the console.
	 * 
	 * @param view view of the world map
	 * @param map map object
	 */
	public void printMap(WorldMapView view, WorldMap map) {
		view.displayMap(map);
		checkCorrectnessOfMap(map.getTerrirotyNeighbourList());

	}

}

package org.game_battle.controler;

import java.util.ArrayList;
import java.util.Map;

import org.game_battle.Constant;
import org.game_battle.model.Implementation.WorldMap;
import org.game_battle.utility.FileReaderWriter;
import org.game_battle.utility.MapDataExtractor;
import org.game_battle.view.WorldMapView;;

public class MapController {

	
	private WorldMap model;
	private WorldMapView view;

	public MapController( WorldMapView view, WorldMap model ){
	      this.view = view;
	      this.model = model;
	   }	
	/**
	 * loadMap is to load the existing Map or to create a new map and
	 * display the new created Map.
	 * 
	 */
	public void loadMap()
	{
		boolean isEditMap = view.initiateMapLoad();
		ArrayList<String> mapData = new ArrayList<String>();
		if(isEditMap)
		{
			view.setInputMapData();
			mapData = view.getInputMapData();	
		}
		else mapData = FileReaderWriter.readFile(Constant.ReadFilePATH); 
		
		MapDataExtractor.extractData(mapData, model);
		 
		if(isEditMap)
			FileReaderWriter.writeFile(Constant.WriteFilePATH, model );
	
	}
	public void editMap() {
		
	}
	
	
	/**
	 * checkcorrectnessOfMap is to validate the map with respect to 
	 * its neighbours.
	 * @param tn
	 */
	public void checkCorrectnessOfMap( Map<String, ArrayList<String>> tn)
	{
		view.validateMap(tn);
	}
	/**
	 * printMap is to print the map to the console.
	 */
	public void printMap()
	{
		System.out.println(this.model.getTerritoryNeighbour());
		System.out.println(this.model.getContinentValues());
	}
		 
	
}

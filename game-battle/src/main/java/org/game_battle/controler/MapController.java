package org.game_battle.controler;

import org.game_battle.view.*;
import org.game_battle.model.Implementation.*;
import org.game_battle.utility.FileReaderWriter;
import org.game_battle.utility.MapDataExtractor;

import java.util.ArrayList;
import java.util.Map;

import org.game_battle.*;;

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
	
	
	
	public void checkCorrectnessOfMap( Map<String, ArrayList<String>> tn)
	{
		view.validateMap(tn);
	}

	public void printMap()
	{
		System.out.println(this.model.getTerritoryNeighbour());
		System.out.println(this.model.getContinentValues());
	}
		 
	
}

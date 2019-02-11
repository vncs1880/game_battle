package org.game_battle.controler;

import org.game_battle.view.*;
import org.game_battle.model.Implementation.*;
import org.game_battle.utility.FileReaderWriter;
import org.game_battle.utility.MapDataExtractor;

import java.util.ArrayList;

import org.game_battle.*;;

public class MapController {

	
	private WorldMap model;
	private WorldMapView view;

	public MapController( WorldMapView view, WorldMap model ){
	      this.view = view;
	      this.model = model;
	   }	
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
	
	public void checkCorrectnessOfMap( )
	{
		view.validateMap(model.getTerritoryNeighbour());
	}

	public void printMap()
	{
		System.out.println(this.model.getTerritoryNeighbour());
		System.out.println(this.model.getContinentValues());
	}
		 
	
}

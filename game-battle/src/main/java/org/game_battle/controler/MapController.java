package org.game_battle.controler;

import java.util.ArrayList;
import java.util.Map;

import org.game_battle.Constant;
import org.game_battle.userAction;
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
	public void loadMap(WorldMapView view)
	{
		view.initiateMapLoad();
		boolean isAddMap = view.isAddMap();
		ArrayList<String> mapData = new ArrayList<String>();
		if(isAddMap)
		{
			view.setInputMapData();
			mapData = view.getInputMapData();	
		}
		else mapData = FileReaderWriter.readFile(Constant.ReadFilePATH); 
		
		MapDataExtractor.extractData(mapData, model);
		 
		if(isAddMap)
		{
			FileReaderWriter.writeFile(Constant.WriteFilePATH, model );
		}
	
	}
	public void editMap(WorldMapView view ,WorldMap map ) 
	{
		userAction value = view.editMap();
		if (value== userAction.EDIT_CONTINENT_VALUE)
			{
				map.updateContinent(view.editContinentValue()) ;
			}
		else if (value== userAction.ADD_NEIGHBOURS)
			{
				map.updateNeighbours(view.editNeighbours());
			}
		else if (value==userAction.REMOVE_NEIGHBOURS)
			{
				map.removeNeighbours(view.removeNeighbours());
				
			}

		else System.out.println("error");
	

		FileReaderWriter.writeFile(Constant.ReadFilePATH, map );

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
	public void printMap(WorldMapView view ,WorldMap map )
	{
		view.displayMap(map);
	}
		 
	
}

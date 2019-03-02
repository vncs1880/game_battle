package org.game_battle;

import org.game_battle.model.Implementation.*;
import org.game_battle.view.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.game_battle.controler.*;
/**
 * Main Class for loading map and editing Map
 * @author 91950
 * 
 *
 */

public class Driver {
	static public void main(String[] args)
	{
		WorldMap model  = new WorldMap();
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
		
		for (ContinentZone x:  model.getContinents() )
		{
			System.out.println(x.getContinentName());
		}

	

	}

}





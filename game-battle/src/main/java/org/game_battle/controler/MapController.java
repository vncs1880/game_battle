package org.game_battle.controler;

import org.game_battle.view.*;
import org.game_battle.model.Implementation.*;

public class MapController {
	
	   private WorldMap model;
	   private MapView view;

	public MapController(WorldMap model, MapView view ) {
		this.model = model;
	    this.view = view;
	}
	
	public void addGraphEdge(int src, int dest){
	      model.addEdge(src, dest);		
	   }
	
	public void printUpdatedDetails(WorldMap model)
		{
	      view.printGraphDetails(model);	
	   }
	
	
	
	
}

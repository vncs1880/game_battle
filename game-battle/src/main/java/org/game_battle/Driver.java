package org.game_battle;
 
import org.game_battle.model.Implementation.*;
import org.game_battle.view.*;
import org.game_battle.controler.*;

public class Driver {
	static public void main(String[] args)
    {
		WorldMap model  = new WorldMap();
        WorldMapView view = new WorldMapView();
        MapController controller = new MapController(view, model);
        controller.loadMap(view);
        controller.printMap(view, model);
        if(!view.isAddMap())
        {
        	if(view.intiateMapEdit())
        	{
        		controller.editMap(view, model);
                controller.printMap(view, model);

        	}
        }
      
    }
    
}





package org.game_battle;



 
import org.game_battle.model.Implementation.*;
import org.game_battle.utility.*;
import org.game_battle.view.*;
import org.json.simple.JSONObject;
import org.game_battle.booatstrap.*;
import org.game_battle.controler.*;

public class Driver {
public static String PATH = "/Users/basantsingh/git/game_battle/game-battle/resource/file.map" ;

    static public void main(String[] args)
    {
    	 
    	MapFile obj = new MapFile();
    	obj.readFiles(PATH);
        MapView view = new MapView();
        WorldMap2 model = createIntialGraph();
   
        MapController controller = new MapController(model, view);
        controller.printUpdatedDetails(model);
        ConnectedGraph.connected_components(model);
        controller.addGraphEdge(4 , 7);
        controller.printUpdatedDetails(model);
        ConnectedGraph.connected_components(model);
 	
    }
    
    
    private static WorldMap2 createIntialGraph(){
    	  int V =9 ;
    	  WorldMap2 g = new WorldMap2(V);
          g.addEdge(1, 2); 
          g.addEdge(2, 3); 
          g.addEdge(3, 4);
          g.addEdge(2, 4);
          g.addEdge(1, 3);
          g.addEdge(2, 8);
          g.addEdge(5, 7);
          g.addEdge(6, 7);
          g.addEdge(5, 6);
        return g;
     }


}





//**** Test case 2 ****  

//int V =4 ;
//Graph g = new Graph(V);
//g.addEdge(1, 2); 
//g.addEdge(2, 3); 
//g.addEdge(2, 4);
//g.addEdge(1, 4);
//g.addEdge(1, 3);
//g.addEdge(3, 4);

//**** Test case 3 ****  
//int V =8 ;
//	Graph g = new Graph(V);
//g.addEdge(1, 2); 
//g.addEdge(2, 3); 
//g.addEdge(3, 4);
//g.addEdge(1, 3); 
//g.addEdge(1, 4); 
//g.addEdge(2, 4); 
//g.addEdge(5, 6);
//g.addEdge(6, 8);
//g.addEdge(6, 7);
//g.addEdge(5, 7);

//**** Test case 4 ****  

//int V =6 ;
//	Graph g = new Graph(V);
//	g.addEdge(1, 4); 
//	g.addEdge(2, 4); 
//	g.addEdge(2, 6);
//	g.addEdge(2, 5); 


//**** Test case 5 ****  

//
//	int V =5 ;
//	Graph g = new Graph(V);
//
//	g.addEdge(1, 2); 
//	g.addEdge(2, 3); 
//	g.addEdge(2, 4);

//**** Test case 6 ****  

//	int V =4 ;
//Graph g = new Graph(V);
//
//g.addEdge(1, 4); 
//g.addEdge(2, 3); 





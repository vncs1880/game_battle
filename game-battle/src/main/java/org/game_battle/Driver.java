package org.game_battle;



 
import org.game_battle.model.Implementation.*;
import org.game_battle.utility.ConnectedGraph;
import org.game_battle.view.*;
import org.json.simple.JSONObject;
import org.game_battle.controler.*;

public class Driver {
	
    static public void main(String[] args)
    {

    	 
    	JSONObject obj=new JSONObject();    
    	obj.put("name","sonoo");    
    	  obj.put("age",new Integer(27));    
    	  obj.put("salary",new Double(600000));    
    	   System.out.print(obj);   
    	
        MapView view = new MapView();
        WorldMap model = createIntialGraph();
   
        MapController controller = new MapController(model, view);
        controller.printUpdatedDetails(model);
        ConnectedGraph.connected_components(model);
        controller.addGraphEdge(4 , 7);
        controller.printUpdatedDetails(model);
        ConnectedGraph.connected_components(model);
 	
    }
    
    
    private static WorldMap createIntialGraph(){
    	  int V =9 ;
    	  WorldMap g = new WorldMap(V);
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





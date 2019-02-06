package org.game_battle.view;

import org.game_battle.model.Implementation.*;

public class MapView {

	public MapView() {
		
		  

}
	
public void printGraphDetails(WorldMap  g)
	{
	for(int i = 0 ; i < g.noOfCountries ; i++)
		        {
		        	 System.out.print(i + 1 + " -> " ); 
		            for(Integer x : g.adjCountry[i] )
		            {
		                System.out.print( x +1 + " ");    
		            }
		             System.out.print("\n");

		        }
		    }
	 
}
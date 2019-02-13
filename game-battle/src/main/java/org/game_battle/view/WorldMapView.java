package org.game_battle.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.game_battle.model.Implementation.WorldMap;
import org.game_battle.utility.ConnectedGraph;

public class WorldMapView {
	
	private boolean isEditMap;
	private ArrayList<String> inputMapData;
	private Scanner sc;
	
	
	public void setInputMapData()
	{	
		inputMapData = new  ArrayList<String>();
		sc = new Scanner(System.in);
		System.out.println("Enter number of new coninent you want to add");
		int numberOfContinent = Integer.parseInt(sc.nextLine());
		System.out.println("Enter control value of " + numberOfContinent + " continents sepearted by =" );
		if(numberOfContinent > 0)
		{
			this.inputMapData.add("[Continents]");

		}

		for(int i =0 ; i <numberOfContinent ; i++ )
		{
			String line = sc.nextLine();
			this.inputMapData.add(line);	
		}

		System.out.println("Enter number of country you want to add");
		int numberOfTerritory = Integer.parseInt(sc.nextLine());
		System.out.println("Enter country in format of country,x_cordinates, y_cordinates , continent , neighbours seperated in comma  ");
		if(numberOfTerritory > 0)
		{
			this.inputMapData.add("[Territories]");

		}
		for(int i =0 ; i <numberOfTerritory; i++ )
		{
			String line = sc.nextLine();
			this.inputMapData.add(line);
		}
		
	}
	
	
	public boolean initiateMapLoad()
	{
		sc = new Scanner(System.in);
    	System.out.println("Enter 0 to load existing map, Enter 1 to create a new map");
    	int input = sc.nextInt();
    	if(input == 0) 
    	{
    		isEditMap= false;
    	}
    	else if(input == 1) 
    	{
    		isEditMap = true;
    	}
    	else
    	{
    		System.out.println("Incorrect input");
    	    System.exit(0);
    	}
    	return isEditMap;
		
	}
	
	public boolean editMap()
	{
		return isEditMap;
	}
	
	public  ArrayList<String> getInputMapData()
	{
		return inputMapData;
	}



	public void validateMap(Map<String, ArrayList<String>> wm) {
		
		int c = ConnectedGraph.connected_components(wm);
        if(c==1){
            System.out.println("Map is connected");
        }
        else{
            System.out.print("No. of Disconnected map: " +  c);
        }
        System.out.print("\n");

		
	}

}

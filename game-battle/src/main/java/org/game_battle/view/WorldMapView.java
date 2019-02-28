package org.game_battle.view;
import org.game_battle.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.game_battle.model.Implementation.WorldMap;
import org.game_battle.utility.ConnectedGraph;

public class WorldMapView {
	
	private boolean isAddMap;
	private boolean isEditMap;

	private ArrayList<String> inputMapData;
	private Scanner sc;
	/**
	 * setInputMapData sets the continents,control values and countries
	 * for the new map load
	 */
	
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
	/**
	 * initiateMapLoad method is to get user input for
	 * loading previous map or creating a new map.
	 * @return isEditMap
	 */
	
	public void initiateMapLoad()
	{
		sc = new Scanner(System.in);
    	System.out.println("Enter 0 to load existing map, Enter 1 to create a new map");
    	int input = sc.nextInt();
    	if(input == 0) 
    	{
    		isAddMap= false;
        	System.out.println("Enter 0 to edit the map, else enter 1 to proceed ");
        	int input1 = sc.nextInt();
        	isEditMap = input1 == 0 ? true : false;
    	}
    	else if(input == 1) 
    	{
    		isAddMap = true;
    	}
    	else
    	{
    		System.out.println("Incorrect input");
    	    System.exit(0);
    	}
		
	}
	
	/**
	 * getter for the getting the new map data
	 * @return inputMapData
	 */
	
	public  ArrayList<String> getInputMapData()
	{
		return inputMapData;
	}


	/**
	 * validateMap checks if the countries,continents and its neighbours are mapped 
	 * properly.
	 * @param wm
	 */
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
	
	public boolean getEditMap() {
		return isEditMap;
	}
	public boolean getAddMap() {
		return isAddMap;
	}
	
	/**
	 * printMap is to print the map to the console.
	 */

	public void displayMap(WorldMap map) {
		System.out.println("\nList of continents and their respective continent values");
		System.out.println("[Continent] : values");
		map.getContinentValues().forEach((k,v)-> System.out.println( "["+  k+ "]" + " : " + v));
		System.out.println("\nList of countires and their respective neighbours");
		System.out.println("[Country]    : [Neighbours] ");
		map.getTerritoryNeighbour().forEach((k,v)->System.out.println( "["+  k+ "]" + "  : " + v));
	}
	
	public userAction editMap()
	{
		System.out.println("\n Enter 0 to edit Continent vlaue");
		System.out.println("\n Enter 1 to add neighbours ");
		System.out.println("\n Enter 2 to remove neighbours ");
		ArrayList<String> mapEdit = new ArrayList<String>();
		sc = new Scanner(System.in);
		int input = sc.nextInt();
		if(input == 0)
		{
//			System.out.println("\n Enter the continent name you want to edit");
			return userAction.EDIT_CONTINENT_VALUE;
		}
		else if(input == 1)
		{
//			System.out.println("\n Enter the country for which neighbour has to be added");
//			String country = sc.nextLine();
//			System.out.println("\n Enter name of neighbour in comma seperatedd");
//			String neighbour = sc.nextLine();
			return userAction.ADD_NEIGHBOURS;

		}
		else if(input == 2)
		{
//			System.out.println("\n Enter the country for which neighbour has to be added");
//			String country = sc.nextLine();
//			System.out.println("\n Enter name of neighbour in comma seperatedd");
//			String neighbour = sc.nextLine();
			return userAction.REMOVE_NEIGHBOURS;

		}
		else return null; 
	}
	
public	String editContinentValue()
	{
		sc = new Scanner(System.in);
		System.out.println("\n Enter the continent name you want to edit");
		String input = sc.nextLine();
		System.out.println("\n Enter the continent value");
		String value = sc.nextLine();
		return input+ ":"+ value; 
	}
public	String editNeighbours()
	{
		sc = new Scanner(System.in);
		System.out.println("\n Enter the country name you want to add");
		String input = sc.nextLine();
		System.out.println("\n Enter the neighbours in commma seprated");
		String value = sc.nextLine();
		return input+ ":"+ value; 
	}
	

public	String removeNeighbours()
	{
		sc = new Scanner(System.in);
		System.out.println("\n Enter the country name you want to remove");
		String input = sc.nextLine();
		System.out.println("\n Enter the neighbours in commma seprated");
		String value = sc.nextLine();
		return input+ ":"+ value; 
	}
	
	

}

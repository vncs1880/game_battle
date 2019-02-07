package org.game_battle.model.Implementation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import org.game_battle.model.Contract.Continent;
import org.game_battle.model.Contract.World;

public class WorldMap2 implements World {
		public int noOfCountries;
		public int noOfContinents;
        public LinkedList<Integer> adjCountry[]; 

	 public WorldMap2( int V) 
	 {
		 noOfCountries = V;
		 adjCountry = new LinkedList[V]; 
         for(int i = 0; i < noOfCountries ; i++)
        	 adjCountry[i] = new LinkedList<Integer>();
         }
	
	 public void addEdge(int src, int dest)
	    {
		 
		 adjCountry[src-1].add(dest-1); 
		 adjCountry[dest-1].add(src-1); 
	    }
	 
	 public boolean removeEdge(int src, int dest)
	    {
		 

		 return true;
	    }

	public ArrayList<Continent> getContinent() {
		// TODO Auto-generated method stub
		return null;
	}


}

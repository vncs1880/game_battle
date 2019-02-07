package org.game_battle.utility;
import org.game_battle.model.Implementation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;


public class ConnectedGraph2
{
    static final int MAXV         = 100;
    static boolean   discovered[] = new boolean[MAXV];
    static boolean   processed[]  = new boolean[MAXV];
    static int       parent[]     = new int[MAXV];
    
    static ArrayList<String>discovered_ = new ArrayList<String>();
    static ArrayList<String>processed_ = new ArrayList<String>();

    public static void bfs(String start)
    {
        Queue<String> queue = new LinkedList<String>();
        int i;
		String v;
        queue.offer(start);
        discovered_.add(start);
        while (!queue.isEmpty())
        {
            v = queue.remove();
            processed_.add(v); 
            ArrayList<String> entry = new  ArrayList<String>();
            if(WorldMap.neighbours.containsKey(v))
            {
            	entry =  WorldMap.neighbours.get(v);
            }
            for (String x: entry )
            {
            	if(!discovered_.contains(x))
            	{
            		discovered_.add(x);
                    queue.add(x); 

            	}
            	
            }
            	
        }

    }
    
 
   public static void initialize_search()
    {
        for (int i = 0; i < WorldMap.noOfCountries; i++)
        {
            processed[i] = discovered[i] = false;
            parent[i] = -1;
        }
    }
 
   public static void connected_components()
    {
        int c;
        initialize_search();
        c = 0;
        
        for (Entry<String, ArrayList<String>> entry : WorldMap.neighbours.entrySet()) {
        	
    		System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
    		String x = entry.getKey();
    		if (!discovered_.contains(x))
            {
                c++;
                bfs(x);
            }  
    		
    	}
            if(c==1){
                System.out.println("Graph is connected");
            }
            else{
                System.out.print("No. of Disconnected graph: ");
                System.out.print(c);
            }
            System.out.print("\n");
    }
 
}
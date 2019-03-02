package org.game_battle.utility;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

/**
 * ConnectedGraph class has the methods to check the connectivity of the countries
 * @author 91950
 *
 *
 */

public class ConnectedGraph
{
    
    static ArrayList<String>discovered_ = new ArrayList<String>();
    static ArrayList<String>processed_ = new ArrayList<String>();
    /**
     * Checks the connectivity of the map by checking its neighbours,
     * if the neighbour and the country matches.
     * @param wm
     * @param start
     */
    public static void bfs(Map<String, ArrayList<String>> wm, String start)
    {
        Queue<String> queue = new LinkedList<String>();
		String v;
        queue.offer(start);
        discovered_.add(start);
        while (!queue.isEmpty())
        {
            v = queue.remove();
            processed_.add(v); 
            ArrayList<String> entry = new  ArrayList<String>();
            if(wm.containsKey(v))
            {
            	entry =  wm.get(v);
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
    
 
   /**
    * connected_components returns the c value as one if 
    * the maps are connected else it gives 0
    * @param wm
    * @return c
    */
 
   public static int connected_components(Map<String, ArrayList<String>> wm)
    {
        int c = 0;
        discovered_.clear();
        for (Entry<String, ArrayList<String>> entry : wm.entrySet()) 
        {
       		String x = entry.getKey();
    		if (!discovered_.contains(x))
            {
                c++;
                bfs(wm, x);
            }  
    		
    	}
            return c;
    }
 
}
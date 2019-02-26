package org.game_battle.utility;
import org.game_battle.model.Implementation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;


public class ConnectedGraph
{
    static final int MAXV         = 100;
    static boolean   discovered[] = new boolean[MAXV];
    static boolean   processed[]  = new boolean[MAXV];
    static int       parent[]     = new int[MAXV];
    
    static ArrayList<String>discovered_ = new ArrayList<String>();
    static ArrayList<String>processed_ = new ArrayList<String>();

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
    
 
   public static void initialize_search(Map<String, ArrayList<String>> wm)
    {
     //  System.out.println( wm.size()) ;
	   for (int i = 0; i < wm.size(); i++)
        {
            processed[i] = discovered[i] = false;
            parent[i] = -1;
        }
    }
   /**
    * 
    * @param wm
    * @return
    */
   public static int connected_components(Map<String, ArrayList<String>> wm)
    {
        int c;
        initialize_search(wm);
        c = 0;
        
        for (Entry<String, ArrayList<String>> entry : wm.entrySet()) {
        	
    	//	System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
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
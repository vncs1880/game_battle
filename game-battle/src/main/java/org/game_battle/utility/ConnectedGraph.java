package org.game_battle.utility;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;


public class ConnectedGraph
{
    
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
    
 

 
   public static int connected_components(Map<String, ArrayList<String>> wm)
    {
        int c;
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
package org.game_battle.utility;
import org.game_battle.model.Implementation.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


public class ConnectedGraph
{
    static final int MAXV         = 100;
    static boolean   discovered[] = new boolean[MAXV];
    static boolean   processed[]  = new boolean[MAXV];
    static int       parent[]     = new int[MAXV];
 
    public static void bfs(WorldMap g, int start)
    {
        Queue<Integer> queue = new LinkedList<Integer>();
        int i, v;
        queue.offer(start);
        discovered[start] = true;
        while (!queue.isEmpty())
        {
            v = queue.remove();
//            System.out.print(v +" "); 

            processed[v] = true;
            Iterator<Integer> it = g.adjCountry[v].listIterator(); 
            while (it.hasNext()) 
            {
            	int n = it.next(); 
            	 if (!discovered[n]) 
                 { 
            		 discovered[n] = true; 
                     queue.add(n); 
                 }
            }
        }
//    	System.out.print("\n");
//
//        for(boolean  x :  discovered )
//        {
//        	System.out.print(x+ " ");
//        }
//    	System.out.print("\n");
//
//        for(boolean  x :  processed )
//        {
//        	System.out.print(x+ " ");
//        }

    }
    
 
   public static void initialize_search(WorldMap g)
    {
        for (int i = 0; i < g.noOfCountries; i++)
        {
            processed[i] = discovered[i] = false;
            parent[i] = -1;
        }
    }
 
   public static void connected_components(WorldMap g)
    {
        int c;
        initialize_search(g);
        c = 0;
        for (int i = 0; i < g.noOfCountries; i++)
        {
            if (!discovered[i])
            {
                c++;
                bfs(g, i);
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
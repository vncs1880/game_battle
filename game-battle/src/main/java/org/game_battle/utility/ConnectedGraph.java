package org.game_battle.utility;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

/**
 * ConnectedGraph class has the methods to check the connectivity of the
 * countries
 * 
 * @author basant
 * @version Alpha
 */

public class ConnectedGraph {

	static ArrayList<String> discovered = new ArrayList<String>();
	static ArrayList<String> processed = new ArrayList<String>();

	/**
	 * Checks the connectivity of the map by checking its neighbours, if the
	 * neighbour and the country matches.
	 * 
	 * @param wm    world map
	 * @param start first continent
	 */
	public static void bfs(Map<String, ArrayList<String>> wm, String start) {
		Queue<String> queue = new LinkedList<String>();
		String v;
		queue.offer(start);
		discovered.add(start);
		while (!queue.isEmpty()) {
			v = queue.remove();
			processed.add(v);
			ArrayList<String> entry = new ArrayList<String>();
			if (wm.containsKey(v)) {
				entry = wm.get(v);
			}
			for (String x : entry) {
				if (!discovered.contains(x)) {
					discovered.add(x);
					queue.add(x);

				}

			}

		}

	}

	/**
	 * connected_components returns the c value as one if the maps are connected
	 * else it gives 0
	 * 
	 * @param wm world map
	 * @return c connectivity check
	 */

	public static int connectedComponents(Map<String, ArrayList<String>> wm) {
		int c = 0;
		discovered.clear();
		for (Entry<String, ArrayList<String>> entry : wm.entrySet()) {
			String x = entry.getKey();
			if (!discovered.contains(x)) {
				c++;
				bfs(wm, x);
			}

		}
		return c;
	}

}
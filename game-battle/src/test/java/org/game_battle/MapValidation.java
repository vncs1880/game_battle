
package org.game_battle;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.game_battle.model.Implementation.ContinentZone;
import org.game_battle.model.Implementation.WorldMap;
import org.game_battle.utility.ConnectedGraph;
import org.game_battle.utility.FileReaderWriter;
import org.game_battle.utility.MapDataExtractor;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

public class MapValidation {
	WorldMap worldmap;
	FileReaderWriter filereadwrie;
	ConnectedGraph connectedgraph;
	ArrayList<String> mapdata;
	MapDataExtractor mapextract;
	
	@Before
	public void setUp() {
		worldmap = new WorldMap();
		filereadwrie = new FileReaderWriter();
		mapdata = filereadwrie.readFile(Constant.ReadFilePATH);
		//connectedgraph.connected_components(worldmap);
	}

	/**
	 * checks if the country list and continent list are stored in the WorldMap
	 */

	@Test
	public void testMapValidation() {
		Assert.assertEquals(true,mapdata.contains(worldmap.getContinents()));
		
		Assert.assertEquals(true,mapdata.contains(worldmap.getTerritories()));
	}

	


}






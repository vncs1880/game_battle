package org.game_battle;

import org.game_battle.model.Implementation.WorldMap;
import org.game_battle.utility.ConnectedGraph;
import org.game_battle.utility.FileReaderWriter;
import org.game_battle.utility.MapDataExtractor;
import org.game_battle.view.WorldMapView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.*;


public class WorldMapTests {

    WorldMapView worldmapview;
    WorldMap invalidMap, validMap;
    private int continentCount, territoryCount;


    @Before
    public void setup() throws IOException {
        validMap = new WorldMap();
        MapDataExtractor.extractData(FileReaderWriter.readFile(TestingConstants.MAP_PATH+TestingConstants.EXISTING_NAME),validMap);

        invalidMap = new WorldMap();
        MapDataExtractor.extractData(FileReaderWriter.readFile(TestingConstants.MAP_PATH+TestingConstants.INVALID_STRCUTURE_MAP),invalidMap);

        BufferedReader reader = new BufferedReader(new FileReader(TestingConstants.MAP_PATH+TestingConstants.EXISTING_NAME));
        String currentLine = reader.readLine();
        boolean countingCont = false;
        boolean countingTerro = false;

        int contCount = 0;
        int terroCount = 0;

        while(currentLine!=null){
            currentLine = currentLine.trim();
            if(currentLine.equals("[Continents]")){
                countingCont=true;
                countingTerro=false;
            }
            else if(currentLine.equals("[Territories]")){
                countingTerro=true;
                countingCont=false;
            }
            else if(currentLine.length()>0){
                if(countingCont) contCount++;
                if(countingTerro) terroCount++;
            }
            currentLine = reader.readLine();
        }
        continentCount = contCount;
        territoryCount = terroCount;
    }


    /**
     * Ensure valid WorldMap contains correct number of continents based on calculated value at start.
     */

    @Test
    public void ensureContinentSize(){
        Assert.assertEquals(validMap.getContinentValues().entrySet().size(),continentCount);
    }

    /**
     * Ensure valid WorldMap contains correct number of territories based on calculated value at start.
     */

    @Test
    public void ensureTerritoriesSize(){
        Assert.assertEquals(validMap.getContinentsInfo().entrySet().size(),territoryCount);
    }

    /**
     * Ensure that the loaded invalid map, which contains disconnected territories, is given the correct
     * number when passed to ConnectedGraph, used when validating maps.
     */

    @Test
    public void invalidMapReturnsInvalid(){

        //int map_connected_count = ConnectedGraph.connected_components(invalidMap.getTerritoryNeighbour());

        int map_connected_count = ConnectedGraph.connected_components(invalidMap.getTerrirotyNeighbourList());

        Assert.assertTrue(map_connected_count>1);
    }

    /**
     * Ensure that the correct map returns correct number when passed to ConnectedGraph.
     */

    @Test
    public void validMapReturnsValid(){

       // int map_connected_count = ConnectedGraph.connected_components(validMap.getTerritoryNeighbour());

        int map_connected_count = ConnectedGraph.connected_components(validMap.getTerrirotyNeighbourList());

        Assert.assertTrue(map_connected_count<=1);
    }


}
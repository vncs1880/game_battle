package org.game_battle;

import org.game_battle.gameplay.MapInterface;
import org.game_battle.model.Implementation.WorldMap;
import org.game_battle.utility.FileReaderWriter;
import org.game_battle.utility.MapDataExtractor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.game_battle.TestingConstants.*;

/**
 *  A series of test executed in order to validate functionality of the utility class 'FileReaderWriter',
 *  which extracts data by reading a file given a path or writes a file to a new directory.
 *
 *  MapInterface is tested with limited data
 *
 */

public class ReaderWriterTests {

    ArrayList<String> testMapData, newTestMapData;
    String firstContinentName;
    File writtenFile;
    WorldMap testFileMap, mapToWrite;

    /**
     * Executed prior to tests being executed, loads a map into an ArrayList given path location.
     * After loading the map an arbitrary alteration is made (control value updated) and the file written.
     */

    @Before
    public void readMapsAndWriteNew(){
        testMapData = FileReaderWriter.readFile(MAP_PATH + EXISTING_NAME);
        testFileMap = new WorldMap();
        MapDataExtractor.extractData(testMapData,testFileMap);
        newTestMapData = testMapData; // Arbitrary alteration of file (changing of a control value) for testing of purposes

        int indexToUpdate=0;
        for(int i=0;i<newTestMapData.size();i++){
           String s = newTestMapData.get(i);
           if(s.contains("[Continents]")){
               indexToUpdate=i+1;
               break;
           }
        }

        String[] splitLineToUpdate = newTestMapData.get(indexToUpdate).split("=");
        String updatedValueString = splitLineToUpdate[0]+="="+UPDATED_CONTROL_VALUE;
        firstContinentName = splitLineToUpdate[0];
        newTestMapData.set(indexToUpdate,updatedValueString);

        WorldMap updatedWorldMap = new WorldMap();
        MapDataExtractor.extractData(newTestMapData,updatedWorldMap);

        FileReaderWriter.writeFile(MAP_PATH+NEW_FILE_NAME,updatedWorldMap);
        writtenFile = new File(MAP_PATH+NEW_FILE_NAME); //Store reference to new written file
    }


    /**
     * Ensure map data container (ArrayList) is Initialized.
     */

    @Test
    public void dataContainerNullTest(){
        Assert.assertTrue(testFileMap!=null);
    }

    /**
     * Test responsible for ensuring that the number of lines read equals the size of the map file
     */

    @Test
    public void sizeCheck() throws IOException {
        Path path = Paths.get(MAP_PATH+EXISTING_NAME);
        long lineCount = Files.lines(path).count();
        Assert.assertEquals(lineCount,testMapData.size());
    }

    /**
     * Check that written file exists within map directory exists.
     */
    @Test
    public void writtenFileExists(){
        Assert.assertNotNull(writtenFile);
    }

    /**
     * Ensure written file contains correct number of continents and correct control values,
     * validated by comparing correct values to written file values.
     */

    @Test
    public void writtenFileHasUpdatedControlValue() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(writtenFile));
        String currentLine = reader.readLine();

        while(currentLine!=null){
            if(currentLine.contains(firstContinentName)){
                break;
            }
            currentLine = reader.readLine();
        }
        int firstTerritoriesControlValue = Integer.parseInt(currentLine.split("=")[1]);
        Assert.assertEquals(firstTerritoriesControlValue ,UPDATED_CONTROL_VALUE);
    }

    /**
     * Testing if when reading from a valid map format but invalid structure (no [Continent] or [Territories] tag) that an exception is
     * thrown or data container is null.
     */

    @Test(expected = Exception.class)
    public void invalidMapRead(){
        ArrayList<String> test = FileReaderWriter.readFile(MAP_PATH+"InvalidMapFormat.map");
        Assert.assertNull(test);
    }

    /**
     * Testing if when reading from an invalid data format (.txt) that an exception is thrown or container is null.
     */

    @Test(expected = Exception.class)
    public void invalidMapFileFormat(){
        ArrayList<String> test = FileReaderWriter.readFile(MAP_PATH+"InvalidMapFormat.txt");
        Assert.assertNull(test);
    }


}

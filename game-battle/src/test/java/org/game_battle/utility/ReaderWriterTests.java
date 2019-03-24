package org.game_battle.utility;

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
 * ReaderWriterTests checks if new control value is updated,checks the map file
 * size.
 * 
 * @author Reshma
 * @version Alpha
 *
 */

public class ReaderWriterTests {

	ArrayList<String> testMapData, newTestMapData;
	String firstContinentName;
	File writtenFile;
	WorldMap worldMap, mapToWrite;

	/**
	 * readMapsAndWriteNew initialises the map path and worldmap values
	 */

	@Before
	public void readMapsAndWriteNew() {
		testMapData = FileReaderWriter.readFile(MAP_PATH + EXISTING_NAME);
		worldMap = new WorldMap();
		MapDataExtractor.extractData(testMapData, worldMap);
		newTestMapData = testMapData;

		int indexToUpdate = 0;
		for (int i = 0; i < newTestMapData.size(); i++) {
			String s = newTestMapData.get(i);
			if (s.contains("[Continents]")) {
				indexToUpdate = i + 1;
				break;
			}
		}

		String[] splitLineToUpdate = newTestMapData.get(indexToUpdate).split("=");
		// updating new control value to continent
		String updatedValueString = splitLineToUpdate[0] += "=" + UPDATED_CONTROL_VALUE;
		firstContinentName = splitLineToUpdate[0];
		newTestMapData.set(indexToUpdate, updatedValueString);

		WorldMap updatedWorldMap = new WorldMap();
		MapDataExtractor.extractData(newTestMapData, updatedWorldMap);

		FileReaderWriter.writeFile(MAP_PATH + NEW_FILE_NAME, updatedWorldMap);
		writtenFile = new File(MAP_PATH + NEW_FILE_NAME); // Store reference to new written file
	}

	/**
	 * File has map values
	 */

	@Test
	public void testWorldMap() {
		Assert.assertTrue(worldMap != null);
	}

	/**
	 * Test responsible for ensuring that the number of lines read equals the size
	 * of the map file
	 */

	@Test
	public void testworldMapSizeCheck() throws IOException {
		Path path = Paths.get(MAP_PATH + EXISTING_NAME);
		long lineCount = Files.lines(path).count();
		Assert.assertEquals(lineCount, testMapData.size());
	}

	/**
	 * write operation check
	 */
	@Test
	public void testWrittenFileExists() {
		Assert.assertNotNull(writtenFile);
	}

	/**
	 * Ensure if the control values are updated
	 */

	@Test
	public void testControlValueUpdation() throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(writtenFile));
		String currentLine = reader.readLine();

		while (currentLine != null) {
			if (currentLine.contains(firstContinentName)) {
				break;
			}
			currentLine = reader.readLine();
		}
		int firstTerritoriesControlValue = Integer.parseInt(currentLine.split("=")[1]);
		Assert.assertEquals(firstTerritoriesControlValue, UPDATED_CONTROL_VALUE);
	}

	/**
	 * Testing if when reading from a valid map format but invalid structure (no
	 * [Continent] or [Territories] tag) that an exception is thrown or data
	 * container is null.
	 */

	@Test(expected = Exception.class)
	public void testInvalidMapRead() {
		ArrayList<String> test = FileReaderWriter.readFile(MAP_PATH + "InvalidMapFormat.map");
		Assert.assertNull(test);
	}

	/**
	 * Testing if when reading from an invalid data format (.txt) that an exception
	 * is thrown or container is null.
	 */

	@Test(expected = Exception.class)
	public void testInvalidMapFileFormat() {
		ArrayList<String> test = FileReaderWriter.readFile(MAP_PATH + "InvalidMapFormat.txt");
		Assert.assertNull(test);
	}

}

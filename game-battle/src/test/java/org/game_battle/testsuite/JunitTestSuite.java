package org.game_battle.testsuite;

import org.game_battle.CountryContinentTests;
import org.game_battle.MapValidation;
import org.game_battle.PlayerTests;
import org.game_battle.ReaderWriterTests;
import org.game_battle.WorldMapTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CountryContinentTests.class, MapValidation.class, PlayerTests.class, ReaderWriterTests.class,
		WorldMapTests.class })

/**
 * TestSuite Class to test all test cases
 * 
 * @author Gagandeep Singh
 * @version Alpha
 */

public class JunitTestSuite {

}

package org.game_battle.testsuite;

import org.game_battle.modelutility.CountryContinentTests;
import org.game_battle.modelutility.MapValidation;
import org.game_battle.modelutility.PlayerTests;
import org.game_battle.modelutility.ReaderWriterTests;
import org.game_battle.modelutility.WorldMapTests;
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

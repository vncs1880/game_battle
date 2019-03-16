package org.game_battle.testsuite;

import org.game_battle.utility.CountryContinentTests;
import org.game_battle.model.MapValidation;
import org.game_battle.utility.PlayerTests;
import org.game_battle.model.ReaderWriterTests;
import org.game_battle.model.WorldMapTests;
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

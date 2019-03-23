package org.game_battle.testsuite;

import org.game_battle.utility.*;
import org.game_battle.model.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CountryContinentTests.class, MapValidation.class, PlayerTests.class, ReaderWriterTests.class,
		WorldMapTests.class,ContinentTest.class,CardTest.class,TerritoryZoneTest.class })

/**
 * TestSuite Class to test all test cases
 * 
 * @author Gagandeep Singh
 * @version Alpha
 */

public class JunitTestSuite {

}

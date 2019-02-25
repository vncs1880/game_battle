package org.game_battle;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase

{
    String testName;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */

    public AppTest( String testName )

    {
        super(  );
        this.testName= testName;
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */

    protected void testApp()
    {
        assertTrue( true );
    }
}

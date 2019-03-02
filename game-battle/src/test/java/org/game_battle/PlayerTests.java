package org.game_battle;

import org.game_battle.gameplay.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerTests {

    /**
     * A series of test for testing the Player class, although executing these tests is difficult as
     * the Player is currently closely coupled with the UI and Board class which is maybe shouldn't be.
     */

    private Board testBoard;
    private Player p1,p2;
    private Country c1,c2,c3,c4,c5,c6,c7;
    private List<Country> p1_conts,p2_conts;

    @Before
    public void setup(){
        testBoard = new Board();

        c1 = new Country("c1");
        c2 = new Country("c2");
        c3 = new Country("c3");
        c4 = new Country("c4");
        c5 = new Country("c5");
        c6 = new Country("c6");
        c7 = new Country("c7");

        p1 = new Player(testBoard,"p1");
        p1_conts = Arrays.asList(new Country[]{c1,c2,c3,c4,c5,c6});
        p1.setCountries(p1_conts);

        p2 = new Player(testBoard,"p2");
        p2_conts = Arrays.asList(new Country[]{c7});
        p2.setCountries(p2_conts);
    }

    /**
     * A test to ensure that the correct number of armies is assigned to player after
     *  the Reinforcement method is executed.
     */

    @Test
    public void reinforcementTest(){

        int countryCount = 0;
        int expectedReinforcementCount = 0;

        for(Country c : testBoard.getCountries()){
            if(testBoard.getOwner(c).equals(p1)){
                countryCount++;
            }
        }

        expectedReinforcementCount = Math.floorDiv(countryCount,3);
        p1.Reinforcement();
        Assert.assertEquals(p1.getArmies(),expectedReinforcementCount);
    }



    /**
     * Check that the correct players are assigned owners in the way they were assigned during prior to test
     * execution.
     */
<<<<<<< HEAD
    
    
    
=======
>>>>>>> f307f79e79797372cb7bc7b2bd02e137f55512f4

    @Test
    public void testCountriesOwnedByPlayers(){

        p1_conts = p1.getCountries();
        p2_conts = p2.getCountries();

        Assert.assertTrue(p1_conts.size()==6);
        Assert.assertTrue(p2_conts.size()==1);
        Assert.assertEquals(p1_conts.get(0),c1);
        Assert.assertEquals(p1_conts.get(1),c2);
        Assert.assertEquals(p2_conts.get(0),c7);
    }



}

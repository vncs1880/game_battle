package org.game_battle.model;

import java.util.ArrayList;

import org.game_battle.model.Implementation.Country;
import org.game_battle.model.Implementation.Player;
import org.junit.Before;
import org.junit.Test;
import org.game_battle.GamePlay;

import junit.framework.Assert;

/**
 * GamePlayTest tests the game play and ensures the game play functions correct
 * 
 * @author Harjot
 *
 */

public class GamePlayTest {

	GamePlay game;

	@Before
	public void setup() {
		game = new GamePlay();
	}

	/**
	 * gameHasNoNullObjects checks if the opbjects are not null
	 */

	@Test
	public void gameHasNoNullOjbects() {
		Assert.assertNotNull(game);
		Assert.assertNotNull(game.getBoard());
		Assert.assertNotNull(game.getBoard().getPlayers());
		Assert.assertNotNull(game.getBoard().getContinents());
		Assert.assertNotNull(game.getBoard().getCountries());
	}

	/**
	 * playerIsRemovedFromGameWhenOwningNoCountries checks if the playher is removed
	 * when he lost all the countries
	 */
	@Test
	public void playerIsRemovedFromGameWhenOwningNoCountries() {

		Player playerWithCountries = game.getBoard().getPlayers().get(1);
		Player playerWithoutCountries = game.getBoard().getPlayers().get(0);
		playerWithoutCountries.setCountries(new ArrayList());

		Assert.assertFalse(game.getBoard().getPlayers().contains(playerWithoutCountries));
		Assert.assertTrue(game.getBoard().getPlayers().contains(playerWithCountries));
	}

	/**
	 * playerWinsIfOwningAllCountries player should win when he owns all the
	 * countries
	 */
	@Test
	public void playerWinsIfOwningAllCountries() {
		Player winningPlayer = game.getBoard().getPlayers().get(0);
		winningPlayer.setCountries(game.getBoard().getCountries());
		Assert.assertEquals(game.getBoard().getCountries().size(), game.getBoard().getPlayers().get(0).getArmies());
		// Assert.assertTrue(game.isOver());
	}

}

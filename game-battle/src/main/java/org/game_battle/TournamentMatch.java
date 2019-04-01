/**
 * 
 */
package org.game_battle;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game_battle.model.Implementation.Player;

/**
 * @author vncs
 *
 */
public class TournamentMatch {
	private static final Random RANDOM = new Random();
	private static final Logger LOG = LogManager.getLogger(TournamentMatch.class);
	private String winner;
	private Player player1;
	private Player player2;
	private int map;
	private GamePlay game;

	public TournamentMatch(int map, GamePlay gamePlay) {
		this.map = map;
		this.game = gamePlay;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TournamentMatch tm = new TournamentMatch(1, new GamePlay());
		tm.startMatch();
		LOG.info("cabou");
	}

	public void setWinner(String winner) {
		this.winner= winner;
	}

	public void startMatch() {
		List<Player> players = null;
		String[] strategies = {"Aggresive", "Benevolent", "Random","Cheater"};
		players.add(new Player(game.getBoard(), "computer 1",
				strategies[0] /*strategies[RANDOM.nextInt(strategies.length)]*/));
		players.add(new Player(game.getBoard(), "computer 2",
				strategies[0] /* strategies[RANDOM.nextInt(strategies.length)] */));
		this.game.startMatch(players);
	}

}

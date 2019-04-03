/**
 * 
 */
package org.game_battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ProgressMonitor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game_battle.model.Contract.TurnSubscriber;
import org.game_battle.model.Implementation.Player;

/**
 * @author vncs
 *
 */
public class TournamentMatch implements TurnSubscriber {
	private static final Random RANDOM = new Random();
	private static final Logger LOG = LogManager.getLogger(TournamentMatch.class);
	private String winner;
	private Player player1;
	private Player player2;
	private int map;
	private GamePlay game;
	private ProgressMonitor progressMonitor;

	public TournamentMatch(GamePlay gamePlay, int max_turns) {
		this.map = map;
		this.game = gamePlay;
		progressMonitor = new ProgressMonitor(null, "Running " + gamePlay, "", 0, max_turns);
		this.game.getBoard().subscribeTurnEvents(this);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TournamentMatch tm = new TournamentMatch(new GamePlay("resource/file.map"), 5000);
		tm.startMatch();
		LOG.info("cabou");
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public void startMatch() {
		/*
		 * List<Player> players = new ArrayList<Player>(); String[] strategies =
		 * {"Aggresive", "Benevolent", "Random","Cheater"}; players.add(new
		 * Player(game.getBoard(), "computer 1", strategies[0]
		 * strategies[RANDOM.nextInt(strategies.length)])); players.add(new
		 * Player(game.getBoard(), "computer 2", strategies[0]
		 * strategies[RANDOM.nextInt(strategies.length)] ));
		 */
		this.game.startMatch();
		// game.getBoard().getTurn();//use observer for this
	}

	@Override
	public void turnChangedTo(int turn) {
		progressMonitor.setProgress(turn);
		if (turn == progressMonitor.getMaximum() + 1) {
			this.winner = "DRAW";
			LOG.info("In order to minimize run completion time, each game is declared a draw after "
					+ progressMonitor.getMaximum() + " turns.");
			System.exit(0);
		}
	}

}

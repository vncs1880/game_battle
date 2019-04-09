/**
 * 
 */
package org.game_battle;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game_battle.model.Contract.TurnSubscriber;
import org.game_battle.model.Implementation.Player;
import org.game_battle.utility.UtilsGUI;

/**
 * @author vncs
 *
 */
public class TournamentMatch implements TurnSubscriber{
	private final Random RANDOM = new Random();
	private final Logger LOG = LogManager.getLogger(TournamentMatch.class);
	private Player winner;
	private GamePlay game;
	/**
	 * @return the game
	 */
	public synchronized GamePlay getGame() {
		return game;
	}

	/**
	 * @param game the game to set
	 */
	public synchronized void setGame(GamePlay game) {
		this.game = game;
	}

	private ProgressMonitor progressMonitor;
	private int turn;
	private int max_turns;
	private Thread my_thread;

	/**
	 * @return the my_thread
	 */
	synchronized public Thread getMy_thread() {
		return my_thread;
	}

	/**
	 * @param my_thread the my_thread to set
	 */
	synchronized public void setMy_thread(Thread my_thread) {
		this.my_thread = my_thread;
	}
		
	public TournamentMatch(GamePlay gamePlay, int max_turns) {
		synchronized (this) {
		this.game = gamePlay;
		this.max_turns = max_turns;
		this.game.getBoard().subscribeTurnEvents(this);//turn observer
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
  		HashMap<String,Object> config = new HashMap<String,Object>() {{
  			put("mapslist", null);
  			put("strategieslist", null);
  			put("gamesnumber", 3);
  			put("turnsnumber", 30);
  			}};
		TournamentMatch tm = new TournamentMatch(new GamePlay("resource/file.map",config), 50);//TODO replace nulls
   		tm.startMatch();
	}

	synchronized public void startMatch() {
		this.game.startMatch();
	}

	@Override
	synchronized public void turnChangedTo(int turn) {
		if (turn==-1||turn == max_turns) {
			this.winner=game.getBoard().getWinner();
			if (turn==max_turns) LOG.info("In order to minimize run completion time, ["+my_thread.getName()+"] was declared a draw after " + max_turns + " turns.");
			if (turn==-1) LOG.info("match winner is "+winner); 

			my_thread.setName(my_thread.getName() + ((turn==max_turns)?" DRAW":" Winner is "+winner.getName()));
			try {
				my_thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} else {
			this.turn = turn;
			LOG.info("Just been notified current turn is "+turn);
		}

	}
}

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
import java.util.List;
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
import org.game_battle.utility.TournamentOutput;
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
	private TournamentOutput output;
	private int row;
	private int col;

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
		
	public TournamentMatch(TournamentOutput tournamentOutputdialog, GamePlay gamePlay, int max_turns, int row, int col) {
		synchronized (this) {
		this.game = gamePlay;
		this.max_turns = max_turns;
		this.game.getBoard().subscribeTurnEvents(this);//turn observer
		this.output = tournamentOutputdialog;
		this.row = row;
		this.col = col;
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
		TournamentMatch tm = new TournamentMatch(null,new GamePlay("resource/file.map",config), 50,0,0);//TODO replace nulls
   		tm.startMatch();
	}

	synchronized public void startMatch() {
		this.game.startMatch();
	}

	@Override
	synchronized public void turnChangedTo(int turn) {
		if (turn==-1||turn == max_turns) {
			this.winner=game.getBoard().getWinner();
			
			if (turn==max_turns) LOG.info("In order to minimize run completion time, was declared a draw after " + max_turns + " turns.");
			if (turn==-1) { 
				LOG.info("match winner is "+winner);
				this.winner.setName("<"+this.winner.getName()+">");
			} 
			//List<Player> players = game.getBoard().getPlayers();
			//String competitors = players.get(0).getName()+"("+players.get(0).getPlayerMode()+")"+" vs "+players.get(1).getName()+"("+players.get(1).getPlayerMode()+")";
			String matchresult = (turn==max_turns)?" DRAW":" "+" WINNER is "+this.winner.getName();
			String threadname = my_thread.getName() + matchresult;
			my_thread.setName(threadname);
			
			output.table.getModel().setValueAt(threadname, row, col);
			
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

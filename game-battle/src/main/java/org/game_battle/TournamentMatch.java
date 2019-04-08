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
public class TournamentMatch implements TurnSubscriber, PropertyChangeListener {
	private static final Random RANDOM = new Random();
	private static final Logger LOG = LogManager.getLogger(TournamentMatch.class);
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
	private Task task;
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

	class Task extends SwingWorker<Void, Void> {
        @Override
        public Void doInBackground() {
            Random random = new Random();
            int progress = 0;
            setProgress(0);
            try {
                Thread.sleep(1000 + random.nextInt(2000));
            } catch (InterruptedException ignore) {}
            while (progress < 100) {
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException ignore) {}
                progress += random.nextInt(10);
                setProgress(Math.min(progress, 100));
            	//setProgress(turn);
            	LOG.info("Updating progress");
            }
            return null;
        }
 
        public void done() {
            Toolkit.getDefaultToolkit().beep();
            LOG.info("In order to minimize run completion time, each game is declared a draw after "
					+ max_turns + " turns.");
        }
    }
		
	public TournamentMatch(GamePlay gamePlay, int max_turns) {
		synchronized (this) {
		//this.my_thread = mythread;
		this.game = gamePlay;
		this.max_turns = max_turns;
		
		/*
		 * javax.swing.SwingUtilities.invokeLater(new Runnable() { public void run() {
		 * task = new Task(); task.addPropertyChangeListener(TournamentMatch.this);
		 * task.execute(); //progressMonitor = new ProgressMonitor(null, "Running " +
		 * gamePlay, "", 0, max_turns);
		 * 
		 * } });
		 */
		UtilsGUI gui = new UtilsGUI();
		//gui.progressBar();
		this.game.getBoard().subscribeTurnEvents(this);
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
			if (turn==max_turns) LOG.info("In order to minimize run completion time, each game is declared a draw after " + max_turns + " turns.");
			if (turn==-1) LOG.info("match winner is "+winner); 
			//Thread.currentThread().setName(Thread.currentThread().getName() + ((turn==max_turns)?" DRAW":" Winner is "+winner.getName()));
			my_thread.setName(my_thread.getName() + ((turn==max_turns)?" DRAW":" Winner is "+winner.getName()));
			try {
				my_thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * synchronized (this) { try { this.wait(); } catch (InterruptedException e) {
			 * e.printStackTrace(); } }
			 */
		} else {
			this.turn = turn;
			LOG.info("Just been notified current turn is "+turn);
		}
		/*
		 * if (progressMonitor != null) { //progressMonitor.setProgress(turn); }
		 */
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            LOG.info("Progress set to "+progress);
            if (progressMonitor != null) {
            	progressMonitor.setProgress(progress);
			}
            //progressBar.setIndeterminate(false);
            //progressBar.setValue(progress);
        }
	}

}

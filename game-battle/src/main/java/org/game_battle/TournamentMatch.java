/**
 * 
 */
package org.game_battle;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game_battle.model.Contract.TurnSubscriber;
import org.game_battle.utility.UtilsGUI;

/**
 * @author vncs
 *
 */
public class TournamentMatch implements TurnSubscriber, PropertyChangeListener {
	private static final Random RANDOM = new Random();
	private static final Logger LOG = LogManager.getLogger(TournamentMatch.class);
	private String winner;
	private GamePlay game;
	private ProgressMonitor progressMonitor;
	private int turn;
	private Task task;
	private int max_turns;

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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
  		TournamentMatch tm = new TournamentMatch(new GamePlay("resource/file.map"), 50);
   		tm.startMatch();
	}

	public void startMatch() {
		this.game.startMatch();
	}

	@Override
	public void turnChangedTo(int turn) {
		this.turn = turn;
		LOG.info("Just been notified current turn is "+turn);
		if (turn == max_turns/*progressMonitor.getMaximum() + 1*/) { // this.winner = "DRAW"; 
			LOG.info("In order to minimize run completion time, each game is declared a draw after "
				+ max_turns + " turns."); 
			System.exit(0); 
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

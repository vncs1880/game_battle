/**
 * 
 */
package org.game_battle;

import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game_battle.model.Contract.TurnSubscriber;
import org.game_battle.model.Implementation.Player;

/**
 * @author vncs
 *
 */
public class TournamentMatch implements TurnSubscriber, PropertyChangeListener {
	private static final Random RANDOM = new Random();
	private static final Logger LOG = LogManager.getLogger(TournamentMatch.class);
	private String winner;
	private Player player1;
	private Player player2;
	private int map;
	private GamePlay game;
	private ProgressMonitor progressMonitor;
	private int turn;
	private Task task;

	class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */
        @Override
        public Void doInBackground() {
            Random random = new Random();
            int progress = 0;
            //Initialize progress property.
            setProgress(0);
            //Sleep for at least one second to simulate "startup".
            try {
                Thread.sleep(1000 + random.nextInt(2000));
            } catch (InterruptedException ignore) {}
            while (progress < 100) {
                //Sleep for up to one second.
                //try {
                //    Thread.sleep(random.nextInt(1000));
                //} catch (InterruptedException ignore) {}
                //Make random progress.
                //progress += random.nextInt(10);
                //setProgress(Math.min(progress, 100));
            	setProgress(turn);
            }
            return null;
        }
 
        /*
         * Executed in event dispatch thread
         */
        public void done() {
            Toolkit.getDefaultToolkit().beep();
            //startButton.setEnabled(true);
            //taskOutput.append("Done!\n");
            LOG.info("In order to minimize run completion time, each game is declared a draw after "
					+ progressMonitor.getMaximum() + " turns.");
            //winner = "Draw";
        }
    }
	
	public void addPropertyChangeListener(TournamentMatch tournamentMatch) {
		// TODO Auto-generated method stub
		
	}
	
	public TournamentMatch(GamePlay gamePlay, int max_turns) {
		this.map = map;
		this.game = gamePlay;
		
		task = new Task();
        task.addPropertyChangeListener(this);
        task.execute();
        
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	progressMonitor = new ProgressMonitor(null, "Running " + gamePlay, "", 0, max_turns);
            }
        });
		this.game.getBoard().subscribeTurnEvents(this);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TournamentMatch tm = new TournamentMatch(new GamePlay("resource/file.map"), 50);
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
		this.turn = turn;
		if (progressMonitor != null) {
			progressMonitor.setProgress(turn);
			if (turn == progressMonitor.getMaximum() + 1) {
			//	this.winner = "DRAW";
			//	LOG.info("In order to minimize run completion time, each game is declared a draw after "
			//			+ progressMonitor.getMaximum() + " turns.");
				System.exit(0);
			}
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            //progressBar.setIndeterminate(false);
            //progressBar.setValue(progress);
        }
	}

}

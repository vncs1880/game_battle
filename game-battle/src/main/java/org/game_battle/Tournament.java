/**
 * 
 */
package org.game_battle;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JDialog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game_battle.model.Implementation.AggresiveStrategyImpl;
import org.game_battle.model.Implementation.Board;
import org.game_battle.model.Implementation.Player;
import org.game_battle.utility.TournamentConfigGUI;
import org.game_battle.utility.UtilsGUI;
import org.game_battle.view.UI;

/**
 * @author vncs
 *
 */
public class Tournament {

	private final Logger LOG = LogManager.getLogger(Tournament.class);
	private TournamentMatch tournamentPanel[][];
	private List<Object> player_strategies;
	private int games_number;
	private int max_turns;
	private List<Object> maps;
	private UtilsGUI gui;
	private HashMap config = new HashMap<>();
	//private List<Thread> matches = new LinkedList<Thread>();
	private final Random RANDOM = new Random();
	
	/**
	 * @param maps 
	 * @param games_number 
	 * 
	 */
	public Tournament(HashMap config_arg) {
		super();
		config = config_arg;//gui.initTournamentForm(maps);
		this.games_number = (int) config.get("gamesnumber"); //games_number;
		this.maps = (List<Object>)config.get("mapslist");
		this.max_turns = (int) config.get("turnsnumber");
		this.player_strategies = (List<Object>) config.get("strategieslist");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*Tournament Mode
		A tournament starts with the user choosing M = 1 to 5 different maps, P = 2 to 4 different
		computer players strategies, G = 1 to 5 games to be played on each map, D = 10 to 50
		maximum number of turns for each game. A tournament is then automatically played by
		playing G games on each of the M different maps between the chosen computer player
		strategies. In order to minimize run completion time, each game should be declared a
		draw after D turns. Once started, the tournament plays all the games automatically
		without user interaction. At the end of the tournament, a report of the results should be
		displayed, e.g.
		M: Map1, Map2, Map3
		P: Aggressive, Benevolent, Random, Cheater.
		G: 4
		D: 30
			   Game 1 Game 2 Game 3 Game 4
		Map 1 Aggressive Random Cheater Cheater
		Map 2 Cheater Draw Cheater Aggressive
		Map 3 Cheater Aggressive Cheater Draw 
		*/
		TournamentConfigGUI dialog = new TournamentConfigGUI();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		HashMap config = new HashMap<>();
		config =  dialog.getConfig();
		
		Tournament tournament = new Tournament(config);
		int gamesNumber = tournament.getGamesNumber();
		int mapsNumber = tournament.getMapsNumber();
		tournament.setTournamentPanel(new TournamentMatch[gamesNumber][mapsNumber]);
	}

	private int getMapsNumber() {
		return this.maps.size();
	}

	private int getStrategiesNumber() {
		return this.player_strategies.size();
	}

	private int getGamesNumber() {
		return this.games_number;
	}

	private void setTournamentPanel(TournamentMatch[][] tournamentPanel) {
		this.tournamentPanel = tournamentPanel;
		
		for (int game = 0; game < (int)(this.config.get("gamesnumber")); game++) {
			for (int map = 0; map < maps.size(); map++) {
				String mapPath = "resource/"+(String) maps.get(map);
				int max_turns2 = (int)this.config.get("turnsnumber");

				final int game_number = game;
				final int map_number = map;
				
				GamePlay game1 = new GamePlay(mapPath,config);
				List<Player> players = game1.getBoard().getPlayers();
				String competitors = players.get(0).getName()+"("+players.get(0).getPlayerMode()+")"+" vs "+players.get(1).getName()+"("+players.get(1).getPlayerMode()+")";
				TournamentMatch tm = new TournamentMatch(game1, max_turns2);
				tournamentPanel[game_number][map_number] = tm;

				Thread match = new Thread("game:" + (game + 1) + " map:" + (map + 1) + " " + competitors){//TODO maybe show file name here
			        public void run(){
			        	tm.setMy_thread(this);
						tournamentPanel[game_number][map_number].startMatch();
			        }
			    };
			    match.start();
			}
		}
		
		LOG.info("Tournament end");
	}

}

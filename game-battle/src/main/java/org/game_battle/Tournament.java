/**
 * 
 */
package org.game_battle;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game_battle.model.Implementation.AggresiveStrategyImpl;
import org.game_battle.model.Implementation.Board;
import org.game_battle.model.Implementation.Player;
import org.game_battle.view.UI;

/**
 * @author vncs
 *
 */
public class Tournament {

	private static final Logger LOG = LogManager.getLogger(Tournament.class);
	private TournamentMatch tournamentPanel[][];
	private int player_strategies_number;
	private int games_number;
	private int max_turns;
	private List<Object> maps;
	
	/**
	 * @param maps 
	 * @param games_number 
	 * 
	 */
	public Tournament(int games_number, List<Object> maps) {
		super();
		this.games_number = games_number;
		this.maps = maps;
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
		
		Board board = null;
		List<Object> M = null;
		int player_strategies_number = 4; 
		int games_number = 5;
		int max_turns = 50;
		
		Tournament tournament = new Tournament(games_number, M);
		tournament.setTournamentPanel(new TournamentMatch[tournament.getGamesNumber()][player_strategies_number]);
		
		LOG.info("cabou");
	}

	private int getGamesNumber() {
		return this.games_number;
	}

	private void setTournamentPanel(TournamentMatch[][] tournamentPanel) {
		this.tournamentPanel = tournamentPanel;
		for (int game = 0; game < this.getGamesNumber(); game++) {
			for (int map = 0; map < 4; map++) {
				TournamentMatch tm = new TournamentMatch(map, new GamePlay());

				this.tournamentPanel[game][map] = tm;
				this.tournamentPanel[game][map].startMatch();
				tm.setWinner("Draw");
			}
		}
	}

}

/**
 * 
 */
package org.game_battle;

import org.game_battle.model.Implementation.Player;

/**
 * @author vncs
 *
 */
public class TournamentMatch {

	private String winner;
	private Player player1;
	private Player player2;
	private int map;
	private int game;

	/**
	 * 
	 */
	public TournamentMatch() {
		// TODO Auto-generated constructor stub
	}

	public TournamentMatch(int map, int game) {
		// TODO Auto-generated constructor stub
		this.map = map;
		this.game = game;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void setCompetitors(Player player1, Player player2) {
		// TODO Auto-generated method stub
		this.player1 = player1;
		this.player2 = player2;
	}

	public void setWinner(String winner) {
		// TODO Auto-generated method stub
		this.winner= winner;
	}

	public void startMatch() {
		// TODO Auto-generated method stub
		
	}

}

/**
 * 
 */
package org.game_battle.gameplay;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 * @author voda
 * 
 */
public class GamePlay {
    private static final Logger LOG = LogManager.getLogger(GamePlay.class);

	private Board board;
	private static List<Player> players;
	
	/**
	 * @param board
	 */
	public GamePlay() {
		super();
		
		board = new Board(); //Start up phase
		players = board.getPlayers();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {	
	//Then the turn-based main play phase begins, in which all players are given a turn in a round-robin fashion. 
	//
	//Each player’s turn is itself divided into three phases: 
	//1) reinforcement phase, 2) attack phase, 3) fortifications phase. 
	//
	//Game driver
	//
	//While NOT GameBoard.isGameOver() //checks if there is only one player left etc
	//For each Player:
//			Player.Reinforcement()
//		Player.Attack()
//			Player.Fortification()
	//
	//Once a player is finished with these three phases, the next player’s turn starts.
		/*
		 * while (it.hasNext()) { Player player = (Player) it.next();
		 * player.Reinforcement(); //player.Attack(); //player.Fortification(); }
		 */
		GamePlay game = new GamePlay();
		boolean gameOver = false;
		while (!gameOver/*players.size() > 1*/) {
			for (Player player : players) {
				LOG.info("\r\n"+game.board+"\r\n\r\n<<PLAYER TURN "+player+">>\r\nReinforcement START");
				player.Reinforcement();
				LOG.info("\r\nReinforcement END: "+player+"\r\nAttack START");
				player.Attack();
				LOG.info("\r\nAttack END: "+player+"\r\nFortification START");
				player.Fortification();
				LOG.info("\r\nFortification END: " + player);
				/* Any player that does not control at least one country is removed from the game. The game ends at
				 * any time one of the players owns all the countries in the map. Cards: A
				 * player receives a card at the end of his turn if he successfully conquered at
				 * least one country during his turn.
				 */ 				
				int currentCountriesQty = player.getCountries().size();
				if (currentCountriesQty == game.board.getCountries().size()) {
					UI.isUserOk("end of game! congratulations "+ player);
					gameOver = true;
				}
				if (currentCountriesQty == 0) {
					game.board.getPlayers().remove(player);
				} else if (currentCountriesQty > player.getPreviousCountriesQty()) {
					player.getCards().add(game.board.getRandomCard());
				}
				player.setPreviousCountriesQty(currentCountriesQty);
			}

			//L = GameBoard.getPlayers()
			//If L.length() == 1:
//				GameBoard.GameOver()
			//Else:
			//For each player in L:
//				countries = length(player.getCountryList())
//					If countries == 0:		
//						L.remove[player]
//					If countries > player.getPreviousCountriesQty():
//						player.receiveCard(GameBoard.getCard())
//					player.setPreviousCountriesQty(countries)			
			/*
			 * Each card is either an infantry, cavalry, or artillery card. During a
			 * player’s reinforcement phase, a player can exchange a set of three cards of
			 * the same kind, or a set of three cards of all different kinds for a number of
			 * armies that increases every time any player does so. The number of armies a
			 * player will get for cards is first 5, then increases by 5 every time any
			 * player does so (i.e. 5, 10, 15, …). A player that conquers the last country
			 * owned by another player receives all the cards held by that player. If a
			 * player holds five cards during his reinforcement phase, he must exchange
			 * three of them for armies.
			 */

			//After battle/during attack phase, inside Board.updateTerritories(DeffendingCountry):
			//
			//defeated = DeffendingCountry.getOwner()
			//If length(defeated.getCountryList()) == 0: //lost last country
			//cardslist = GameBoard.getDistributedCards
			//cardslist[winner].append(cardslist[defeated])
		};
		
	}

	public Board getBoard() {
		return board;
	}

}

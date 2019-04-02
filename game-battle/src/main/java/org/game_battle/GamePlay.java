package org.game_battle;

import java.util.List;

import javax.naming.Context;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game_battle.model.Implementation.AggresiveStrategyImpl;
import org.game_battle.model.Implementation.BenevolentStrategyImpl;
import org.game_battle.model.Implementation.Board;
import org.game_battle.model.Implementation.CheaterStrategyImpl;
import org.game_battle.model.Implementation.MapInterface;
import org.game_battle.model.Implementation.Player;
import org.game_battle.model.Implementation.PlayerStrategy;
import org.game_battle.model.Implementation.RandomStrategyImpl;
import org.game_battle.view.PhaseView;
import org.game_battle.view.PlayerDominationView;
import org.game_battle.view.CardView;

import org.game_battle.view.UI;

/**
 * This class sets up the gameplay of the game
 * 
 * @author Vini
 * @version Alpha
 * 
 **/
public class GamePlay {
	// System.setProperty("log4j.configurationFile","./path_to_the_log4j2_config_file/log4j2.xml");
	private static final Logger LOG = LogManager.getLogger(GamePlay.class);

	private Board board = null;
	private Player player;
	private static List<Player> players;
	private PhaseView phaseView;
	private PlayerDominationView playerDominationView;
	private CardView cardView;

	/**
	 * Gameplay constructor
	 * 
	 */
	public GamePlay() {
		super();
		board = new Board(); //Start up phase
		initGamePlay();
	}

	public GamePlay(String mapPath) {//overloaded for tournament mode
		super();
		board = new Board(mapPath);
		initGamePlay();
	}

	/**
	 * Main class to run the entire flow
	 * 
	 * @param args arguments
	 */
	public static void main(String[] args) {
		// Then the turn-based main play phase begins, in which all players are given a
		// turn in a round-robin fashion.
		//
		// Each player’s turn is itself divided into three phases:
		// 1) reinforcement phase, 2) attack phase, 3) fortifications phase.
		//
		// Game driver
		//
		// While NOT GameBoard.isGameOver() //checks if there is only one player left
		// etc
		// For each Player:
//			Player.Reinforcement()
//		Player.Attack()
//			Player.Fortification()
		//
		// Once a player is finished with these three phases, the next player’s turn
		// starts.
		/*
		 * while (it.hasNext()) { Player player = (Player) it.next();
		 * player.Reinforcement(); //player.Attack(); //player.Fortification(); }
		 */

		GamePlay game = new GamePlay();
		game.startMatch();

	}

	public void startMatch(/* List<Player> players_list */) {
		
		
		boolean gameOver = false;
		int i = 0;
		String action = "";
		while (!gameOver/* players.size() > 1 */) {
			i++;
			getBoard().setTurn(i);
			for (Player player : players/*players_list*/) {

				if (player.getPlayerMode().equals("Human")) {
					board.playerStrategy.setStrategy(new UI());
				} else if (player.getPlayerMode().equals("Aggresive")) {
					board.playerStrategy.setStrategy(new AggresiveStrategyImpl());

				} else if (player.getPlayerMode().equals("Benevolent")) {
					board.playerStrategy.setStrategy(new BenevolentStrategyImpl());

				} else if (player.getPlayerMode().equals("Random")) {
					board.playerStrategy.setStrategy(new RandomStrategyImpl());
				}

				else {
					board.playerStrategy.setStrategy(new CheaterStrategyImpl());

				}

				board.setCurrentPlayer(player.getName() + "  " + player.getPlayerMode());

				action = "PLAYER :" + player.getName() + " TURN #" + i + "\n" + board;
				board.setActionTakingPlace(action);

				// LOG.info("\r\n" + game.board + "\r\n\r\n<<PLAYER " + player.getName()
				// + " TURN #" + i + ">>\r\n\r\n Reinforcement START");
				// added 23rd as part of pahse 2
				board.setGamePhaseName("Reinforcement");
				board.getBoardInfo();

				player.addObserver(cardView);

				player.Reinforcement();

				action = "\r\nReinforcement END: \r\n\r\n" + player;
				board.setActionTakingPlace(action);
				board.getBoardInfo();

				if (getBoard().getTurn() > 1) {
					// added 23rd as part of pahse 2
					board.setGamePhaseName("Attack");
					board.getBoardInfo();
					player.Attack();
					action = "\nAttack END: \r\n\r\n" + player;
					board.setActionTakingPlace(action);
					// LOG.info("\r\nAttack END: \r\n\r\n" + player + "\r\n\r\nFortification
					// START");
				} else {
					action = "First turn, skipping attack phase \r\n\r\n" + player;
					board.setActionTakingPlace(action);
					// LOG.info("First turn, skipping attack phase \r\n\r\n"+ player +
					// "\r\n\r\nFortification START");
				}
				// added 23rd as part of pahse 2
				board.setGamePhaseName("Fortification");
				board.getBoardInfo();
				player.Fortification();
				action = "\nFortification END: \r\n\r\n" + player;
				board.setActionTakingPlace(action);
				board.getBoardInfo();
				// LOG.info("\r\nFortification END: \r\n\r\n" + player);
				/*
				 * Any player that does not control at least one country is removed from the
				 * game. The game ends at any time one of the players owns all the countries in
				 * the map. Cards: A player receives a card at the end of his turn if he
				 * successfully conquered at least one country during his turn.
				 * https://docs.google.com/document/d/1dm4wG1lrqY6gxv315bQzvsdfSGi36X9BB-
				 * 8Y1byZSe0/edit?disco=AAAAClD6TuY
				 */
				int currentCountriesQty = player.getCountries().size();
				if (currentCountriesQty == board.getCountries().size()) {

					board.playerStrategy.askNumber("it is over :P",
							"end of game! congratulations " + player.getName() + "!\r\n\r\nPlease rate this game:", 1,
							5, 0, false);
					gameOver = true;
				}
				if (currentCountriesQty == 0) {
					board.getPlayers().remove(player);
					action = player.getName()
							+ ", you lost all your countries. Thank you for your participation. Good bye.";
					board.setActionTakingPlace(action);
					// LOG.info(player.getName()
					// + ", you lost all your countries. Thank you for your participation. Good
					// bye.");
				} else if (currentCountriesQty > player.getPreviousCountriesQty()) {
					player.getCards().add(board.getRandomCard());
					action = "You conquered at least one new country. Have an extra card for that. Congratulations.";
					board.setActionTakingPlace(action);
					// LOG.info("You conquered at least one new country. Have an extra card for
					// that. Congratulations.");
				}
				player.setPreviousCountriesQty(currentCountriesQty);
			}

			// L = GameBoard.getPlayers()
			// If L.length() == 1:
//				GameBoard.GameOver()
			// Else:
			// For each player in L:
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

			// After battle/during attack phase, inside
			// Board.updateTerritories(DeffendingCountry):
			//
			// defeated = DeffendingCountry.getOwner()
			// If length(defeated.getCountryList()) == 0: //lost last country
			// cardslist = GameBoard.getDistributedCards
			// cardslist[winner].append(cardslist[defeated])
		}
	}

	/**
	 * 
	 */
	private void initGamePlay() {
		players = board.getPlayers();

		phaseView = new PhaseView();
		playerDominationView = new PlayerDominationView();
		cardView = new CardView();
		
		board.addObserver(phaseView);
		board.addObserver(playerDominationView);
	}

	/**
	 * getBoard class to get Board details
	 * 
	 * @return game board
	 */
	public Board getBoard() {
		return board;
	}

}

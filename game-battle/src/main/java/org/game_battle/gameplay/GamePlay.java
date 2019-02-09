/**
 * 
 */
package org.game_battle.gameplay;

import java.util.List;

/**
 * @author voda
 *
 */
public class GamePlay {

	private Board board;
	private static List<Player> players;
	
	/**
	 * @param board
	 */
	public GamePlay() {
		super();
		initialization();
	}

	/**
	 * 
	 */
	private void initialization() {
		//Board LOADS Graph
		//Game: The game starts by the start-up phase, where the number of players is determined, then all the
		//countries are randomly assigned to the players. 
		//
		//L = GameBoard.loadGraph(filename).getRandomCountriesLists(PlayersTotalNumber)
		////generate a list of lists, each w random countries
		//For player in PlayersTotalNumber:
//			New Player().AssignCountries( L[player] )
//			//set countries_list in Player OR set owner in country
		board = new Board();
		board.startup();
		List<List<Country>> L = board.getRandomCountriesLists(board.getPlayersCount());
		players = board.getPlayers();
		for (Player player : players) {
			player.setCountries(L.get(players.indexOf(player)));//TODO check this later
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
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
		while (game.getBoard().getPlayers().size() > 1) {
			for (Player player : players) {
				player.Reinforcement();
				//player.Attack();
				//player.Fortification();
			}
		};
		
	}

	public Board getBoard() {
		// TODO Auto-generated method stub
		return board;
	}

	//Once all the reinforcement armies have been placed by the player, the attacks phase begins. In the attack phase, the player may choose one of the countries he owns that contains two or more armies, and declare an attack on an adjacent country that is owned by another player. 
	//
	//elligibleAttackerCountries = Player.getCountries(Country.getArmiesCount() > 2)
	//
	//A battle is then simulated by the attacker rolling at most 3 dice (which should not be more than the number of armies contained in the attacking country) and the defender rolling at most 2 dice (which should not be more than the number of armies contained in the attacking country). 
	//
	//OffendingCountry = elligibleAttackerCountries[UI.get_user_selection]
	//DeffendingCountry = Board.getElligibleTargets(OffendingCountry)[UI.get_user_selection] 
	////elligible targets are adjacent nodes
	//Board.Battle(OffendingCountry, DeffendingCountry) 
	////Battle() updates players armies numbers according to logic below
	//
	//The outcome of the attack is determined by comparing the defenders best dice
	//roll with the attackers best dice roll. If the defender rolls greater or equal to the attacker, then the attacker loses an army otherwise the defender loses an army. If the defender rolled two dice, then his other dice roll is compared to the attacker's second best dice roll and a second army is lost by the attacker or defender in the same way. 
	//
	//Still to do 
	//pls help😅
	//
	//The attacker can choose to continue attacking until either all his armies or all the defending armies have been eliminated. 
	//
	//While (OffendingCountry.getTotalArmies() > 0) OR (DeffendingCountry.getTotalArmies() > 0) do {
	//<<Board.Battle()>>
	//}
	//
	//If all the defender's armies are eliminated the attacker captures the territory. 
	//
	//Board.updateTerritories(DeffendingCountry) 
	////just change ownership if DeffendingCountry.getTotalArmies() == 0
	//
	//The attacking player must then place a number of armies in the conquered country which is greater or equal than the number of dice that was used in the attack that resulted in conquering the country. A player may do as many attacks as he wants during his turn. 
	//
	//MinimumArmies = Board.Battle.getLastRollDiceResult()
	//
	//Once he declares that he will not attack anymore (or cannot attack because none of his countries that have an adjacent country controlled by another player is containing more than one army), the fortification phase begins. In the fortification phase, the player may move any number of armies from one of his owed countries to the other, provided that there is a path between these two countries that is composed of countries that he owns. Only one such move is allowed per fortification phase.
	//
	//For each country in Player.getCountryList():
	//If coutry.hasArmies() AND GameBoard.areConnected(country, defeatedCountry):
//		Z = UI.ask(“Move how many armies from X to Y? [0 to country.getArmiesNumber()]”)
//		country.setArmiesNumber(country.getArmiesNumber() - Z)
//		defeatedCountry.setArmiesNumber(Z)	
	//break FOR //only 1 move is allowed
	//
	//Once the move is made or the player forfeits his fortification phase, the player’s turn ends and it is now the next player’s turn. Any player than does not control at least one country is removed from the game. The game ends at any time one of the players owns all the countries in the map. Cards: A player receives a card at the end of his turn if he successfully conquered at least one country during his turn. 
	//
	//L = GameBoard.getPlayers()
	//If L.length() == 1:
//		GameBoard.GameOver()
	//Else:
	//For each player in L:
//		countries = length(player.getCountryList())
//			If countries == 0:		
//				L.remove[player]
//			If countries > player.getPreviousCountriesQty():
//				player.receiveCard(GameBoard.getCard())
//			player.setPreviousCountriesQty(countries)
	//
	//Each card is either an infantry, cavalry, or artillery card. During a player’s reinforcement phase, a player can exchange a set of three cards of the same kind, or a set of three cards of all different kinds for a number of armies that increases every time any player does so. The number of armies a player will get for cards is first 5, then increases by 5 every time any player does so (i.e. 5, 10, 15, …). A player that conquers the last country owned by another player receives all the cards held by that player. If a player holds five cards during his reinforcement phase, he must exchange three of them for armies.
	//
	//After battle/during attack phase, inside Board.updateTerritories(DeffendingCountry):
	//
	//defeated = DeffendingCountry.getOwner()
	//If length(defeated.getCountryList()) == 0: //lost last country
	//cardslist = GameBoard.getDistributedCards
	//cardslist[winner].append(cardslist[defeated])
}

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
	
	/**
	 * @param board
	 */
	public GamePlay() {
		super();
		board = new Board();
		board.init();
		List<List<Country>> L = board.getRandomCountriesLists(board.getPlayersCount());
		for (Player player : board.getPlayers()) {
			
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Board LOADS Graph
		//
		//Game: The game starts by the start-up phase, where the number of players is determined, then all the
		//countries are randomly assigned to the players. 
		//
		//L = GameBoard.loadGraph(filename).getRandomCountriesLists(PlayersTotalNumber)
		////generate a list of lists, each w random countries
		//
		//For player in PlayersTotalNumber:
//			New Player().AssignCountries( L[player] )
//			//set countries_list in Player OR set owner in country
		
		

	}
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
	//
	//In the reinforcements phase, the player is given a number of armies that depends on the number of countries he owns (# of countries owned divided by 3, rounded down). 
	//If the player owns all the countries of an entire continent, the player is given an amount of armies
	//corresponding to the continent’s control value. 
	//
	//Player.resetNumberArmies
	//
	//For each Continent:
//		if allContinentBelongsTo(Player) 
//			Player.updateNumberArmies(NumberArmies(Continent.control_value))  
	////updateNumberArmies just accumulates the number 
//		else
//			totalCountriesOwnedByPlayer += Continent.CountriesOwnedBy(Player)
	////add accumulated countries after all continents are checked
	//Player.updateNumberArmies(RoundDown(totalCountriesOwnedByPlayer/3))
	//
	//OR (needs elaboration tho)...
	//
	//For each Continent
//		Continent.CalculateNumberArmies(Player)
	//
	//
	//Finally, if the player owns three cards of different sorts or the same sorts, he can exchange them for armies. 
	//
	//“Do you want to try to get armies from your cards?”
	//If YES:
	//Player.updateNumberArmies(Cards.getEligibleArmies(Player.getCards()))
	////if not elligible,   Cards.getEligibleArmies = 0
	////Cards.getEligibleArmies manages the counter to 5, 10, 15.. across players turns 
	////Cards is an utility class for now (maybe should be Board.Cards because Board HAS Cards).
	//
	//The number of armies a player will get for cards is first 5, then increases by 5 every time any player does so (i.e. 5, 10, 15, …). In any case, the minimal number of reinforcement armies is 3. 
	//
	//If totalArmiesOwnedByPlayer < 3:
//		totalArmiesOwnedByPlayer = 3
	//
	//Once the total number of reinforcements is determined for the player’s turn, the player may place the armies on any country he owns, divided as he wants. 
	//
	//For each Player.getCountries():
//		If totalArmiesOwnedByPlayer == 0 then break
//		N = user_input //if >  totalArmiesOwnedByPlayer, N = totalArmiesOwnedByPlayer
//		Country.setArmiesNumber(n)
	//
	//
	//Question : Do player need to play based on strategy or everything should happen in background with randomly generated value
	//
	//
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

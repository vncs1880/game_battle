/**
 * 
 */
package org.game_battle.model.Implementation;

import java.util.*;

import org.game_battle.TournamentMatch;
import org.game_battle.model.Contract.*;
import org.game_battle.model.Implementation.*;
import org.game_battle.view.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game_battle.model.Implementation.Card.Sort;

/**
 * This class sets up the countries and continents on the game board
 * 
 * @author Vini
 * @version Alpha
 * @date 5/02/19
 **/
public class Board extends Observable {
	private static final int MAX_DICE_ROLLS_ATTACKER = 3;
	private static final Logger LOG = LogManager.getLogger(Board.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String s = "\r\ncardsToGive: " + cardsToGive + ", lastDiceRollResult: " + lastDiceRollResult + ", \r\nplayers: "
				+ players + " \r\ncontinents:" + continents;
		return s;// super.toString();
	}

	private static final int CARDS_EQUAL_DIFFERENT_AMOUNT = 3;
	private List<Continent> continents;
	private List<List<Country>> countriesByContinent;
	private List<Player> players;
	private int cardsToGive;
	private int lastDiceRollResult;
	private MapInterface mapinterface;
	private int turn = 0;
	private String actionTakingPlace;
	private String gamePhaseName;
	private String currentPlayer;
	private String currentPlayerMode;

	public String getCurrentPlayerMode() {
		return currentPlayerMode;
	}

	public void setCurrentPlayerMode(String currentPlayerMode) {
		this.currentPlayerMode = currentPlayerMode;
	}

	private float totalNoOfCountries;
	private boolean isAllOutMode = false;
	public UI ui;
	public PlayerStrategy playerStrategy;
	// public Player currePlayer;
	private List<TurnSubscriber> turnsubscribers = new LinkedList<TurnSubscriber>();

	public String getActionTakingPlace() {
		return actionTakingPlace;
	}

	public void setActionTakingPlace(String actionTakingPlace) {
		this.actionTakingPlace = actionTakingPlace;
	}

	public String getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public String getGamePhaseName() {
		return gamePhaseName;
	}

	public void setGamePhaseName(String gamePhaseName) {
		this.gamePhaseName = gamePhaseName;
	}

	private static final Random RANDOM = new Random();
	private static final int MAX_DICE_ROLLS_DEFFENDER = 2;

	/**
	 * 
	 */
	public Board() {
		// Board LOADS Graph
		// Game: The game starts by the start-up phase, where the number of players is
		// determined, then all the
		// countries are randomly assigned to the players.
		// L = GameBoard.loadGraph(filename).getRandomCountriesLists(PlayersTotalNumber)
		//// generate a list of lists, each w random countries
		mapinterface = new MapInterface();
		playerStrategy = new PlayerStrategy();
		playerStrategy.setStrategy(new UI());
		LOG.info("\r\n\r\n<<UI prompt in the background. Please use ALT-TAB>>");// TODO make the UI come to foreground
		int totalPlayers = playerStrategy.askNumber("Initializing board", "How many players?", 2, 6, 0, false);

		LinkedList<Player> players = initPlayers(totalPlayers);
		
		totalNoOfCountries = MapInterface.getCountries().size();
		//int numofCountries = MapInterface.getCountries().size(); //used nowhere
		/*
		 * setPlayers(new LinkedList<Player>(Arrays.asList(new Player(this, "x"), new
		 * Player(this, "y"), new Player(this, "z"), new Player(this, "a"))));
		 */
		// List<List<Country>> L = getRandomCountriesLists(getPlayers().size());
		distributeCountries(getPlayers().size());
		// added 23rd as part of pahse 2

		actionTakingPlace = "After Random Distribution of the countries to the players: " + players;

		// For player in PlayersTotalNumber:
//		New Player().AssignCountries( L[player] )
//		//set countries_list in Player OR set owner in country		
		// for (Player player : players) {
		// player.setCountries(L.get(players.indexOf(player)));
		// }
		// User-driven creation of map elements, such as country, continent, and
		// connectivity between countries. 4
		continents = MapInterface.getContinents();
		// Saving a map to a text file exactly as edited (using the “conquest” game map
		// format). 3
		// Loading a map from an existing “conquest” map file, then editing the map, or
		// create a new map from scratch. 3
		// Verification of map correctness upon loading and before saving (at least 3
		// types of incorrect maps). 2
		// Implementation of a game driver implementing the game phases according to the
		// Risk rules. 2
		// Game starts by user selection of a user-saved map file. 1
		// Map is loaded as a connected graph, which is rendered effectively to the user
		// to enable efficient play. 3
		// User chooses the number of players,
		// getBoardInfo();

	}
	public Board(String mapPath) {//overloaded for tournament mode
		mapinterface = new MapInterface(mapPath);
		//LOG.info("\r\n\r\n<<UI prompt in the background. Please use ALT-TAB>>");// TODO make the UI come to foreground
		playerStrategy = new PlayerStrategy();
		totalNoOfCountries = MapInterface.getCountries().size();
		List<Player> players = new ArrayList<Player>();
		String[] strategies = {"Aggresive", "Benevolent", "Random","Cheater"};
		players.add(new Player(this, "computer 1", strategies[0] /*strategies[RANDOM.nextInt(strategies.length)]*/));
		players.add(new Player(this, "computer 2", "Aggresive" /* strategies[RANDOM.nextInt(strategies.length)] */));
		setPlayers(players);
		distributeCountries(getPlayers().size());
		actionTakingPlace = "After Random Distribution of the countries to the players: " + players;
		continents = MapInterface.getContinents();
	}

	/**
	 * @param totalPlayers
	 * @return
	 */
	private LinkedList<Player> initPlayers(int totalPlayers) {
		LinkedList<Player> players = new LinkedList<Player>();
		for (int i = 0; i < totalPlayers; i++) {
			String name = "dummy player";
			name = playerStrategy.askText("What is player " + (i + 1) + " name?",
					"Initializing board - " + totalPlayers + " players");
			String tempPrompt = "Select your player type of " + name;
			String tempTitle = "Player Type";
			List<String> playersType = new ArrayList();
			playersType.add("Human");
			playersType.add("Aggresive");
			playersType.add("Benevolent");
			playersType.add("Random");
			playersType.add("Cheater");
			Object[] playerMode_array = playersType.toArray();
			String playerMode = (String) JOptionPane.showInputDialog(null, tempPrompt, tempTitle,
					JOptionPane.QUESTION_MESSAGE, null, playerMode_array, playerMode_array[0]);
			players.add(new Player(this, name, playerMode));

		}

		setPlayers(players);
		// added 23rd as part of pahse 2
		actionTakingPlace = "Players playing in the game : " + players;
		return players;
	}

	public void distributeCountries(int playersCount) {
		List<Country> countriesToDistribute = MapInterface.getCountries();
		Collections.shuffle(countriesToDistribute);
		/*
		 * int i = 0; int delta = countriesToDistribute.size()/players.size(); for
		 * (Player player : players) { int j = i + delta;
		 * player.setCountries(countriesToDistribute.subList(i, j)); i = j; }//
		 * distribute remaining countries
		 */
		Iterator<Country> it = countriesToDistribute.iterator();
		int k = 0;
		while (it.hasNext()) {
			Country c = (Country) it.next();
			Player player = players.get(k);
			List<Country> countries = player.getCountries();
			countries.add(c);
			player.setPreviousCountriesQty(countries.size());
			// countriesToDistribute.remove(c);
			k = (++k == players.size()) ? 0 : k;

		}
	}

	public int getArmiesFromCards(Player p) {
		List<Card> player_cards = p.getCards();
		Collection selected = new ArrayList();
		while (selected.size() != 3) {
			selected = playerStrategy.getObjs("Select 3 cards to exchange (use CTRL for multi select)",
					player_cards.toArray());
		}

		HashMap<Card.Sort, Integer> cardsCount = new HashMap<Card.Sort, Integer>() {
			{
				put(Card.Sort.INFANTRY, 0);
				put(Card.Sort.CAVALRY, 0);
				put(Card.Sort.ARTILLERY, 0);
			}
		};

		List<Card> cards = (List<Card>) selected;

		for (Card card : cards) {
			Sort cardtype = card.getType();
			cardsCount.replace(cardtype, cardsCount.get(cardtype) + 1);
		}
		LOG.info("cards: " + cards + " cardtype: " + " cardscount: " + cardsCount);
		// LOG.info("cards: " +cards+" cardscount: "+cardsCount);
		// Finally, if the player owns three cards of different sorts or the same sorts,
		// he can exchange them for armies.

		// Each card is either an infantry, cavalry, or artillery card. During a
		// player’s reinforcement phase, a player can exchange a set of three cards of
		// the same kind,
		// or a set of three cards of all different kinds for a number of armies that
		// increases every time any player does so. The number of armies a player will
		// get for
		// cards is first 5, then increases by 5 every time any player does so (i.e. 5,
		// 10, 15, …). TODO A player that conquers the last country owned by another
		// player
		// receives
		// all the cards held by that player. If a player holds five cards during his
		// reinforcement phase, he must exchange three of them for armies.

		//// getEligibleArmies manages the counter to 5, 10, 15.. across players turns
		//// Cards is an utility class for now (maybe should be Board.Cards because
		//// Board HAS Cards).
		// The number of armies a player will get for cards is first 5, then increases
		//// by 5 every time any player does so (i.e. 5, 10, 15, …). In any case, the
		//// minimal number of reinforcement armies is 3.

		// boolean equals = cardsCount.values().contains(CARDS_EQUAL_DIFFERENT_AMOUNT);

		Collection<Integer> cardsCountValues = cardsCount.values();
		int equals = 0, diffs = 0;
		for (Integer count : cardsCountValues) {
			if (count == CARDS_EQUAL_DIFFERENT_AMOUNT) {
				equals = CARDS_EQUAL_DIFFERENT_AMOUNT;
				LOG.info("detected " + CARDS_EQUAL_DIFFERENT_AMOUNT + " cards of equal type.");

				break;
			} else if (count != 0) {
				diffs++;
				if (diffs == CARDS_EQUAL_DIFFERENT_AMOUNT) {
					LOG.info("detected " + CARDS_EQUAL_DIFFERENT_AMOUNT + " cards of different type.");
					break;
				}
			}
		}

		/*
		 * Collection<Integer> eqs = cardsCount.values(); LOG.info(" eqs: "+eqs);
		 * eqs.removeIf(new Predicate<Integer>() { public boolean test(Integer i) {
		 * return i < CARDS_EQUAL_DIFFERENT_AMOUNT; } }); boolean equals = eqs.size()>0;
		 * 
		 * Collection<Integer> diffs = cardsCount.values(); LOG.info(" diffs: "+diffs);
		 * diffs.removeIf(new Predicate<Integer>() { public boolean test(Integer i) {
		 * return i != 1; } }); boolean differents = diffs.size() >=
		 * CARDS_EQUAL_DIFFERENT_AMOUNT;
		 */

		// LOG.info("equals: " + equals +" diffs: "+diffs);

		if (equals >= CARDS_EQUAL_DIFFERENT_AMOUNT || diffs >= CARDS_EQUAL_DIFFERENT_AMOUNT) {
			cardsToGive += 5;
			LOG.info("player gained " + cardsToGive + " armies from their cards.");
			return cardsToGive;
		} else {
			LOG.info("player didnt get any armies from their cards.");
			return 0;
		}
	}

	/*
	 * public List<Country> getElligibleTargets(Country offendingCountry) { return
	 * null; }
	 */

	public void doBattle(Country offendingCountry, Country deffendingCountry) {
		// ALMOST DONE BUILD2 update players armies numbers according to logic below
		/*
		 * A battle is then simulated by the attacker rolling at most 3 dice (which
		 * should not be more than the number of armies contained in the attacking
		 * country) and the defender rolling at most 2 dice (which should not be more
		 * than the number of armies contained in the attacking country).
		 */
		Player attacker = getOwner(offendingCountry);
		Player deffender = getOwner(deffendingCountry);
		LOG.info("\r\nATTACKER: " + attacker + "\r\nDEFFENDER: " + deffender);

		List<Integer> attackerDices = rollDices(attacker, offendingCountry,
				(offendingCountry.getArmies() < MAX_DICE_ROLLS_ATTACKER) ? offendingCountry.getArmies()
						: MAX_DICE_ROLLS_ATTACKER);
		List<Integer> deffenderDices = rollDices(deffender, deffendingCountry,
				(/* deffendingCountry */offendingCountry.getArmies() < MAX_DICE_ROLLS_DEFFENDER)
						? /* deffendingCountry */offendingCountry.getArmies()
						: MAX_DICE_ROLLS_DEFFENDER);

		int deffender_dice_list_size = deffenderDices.size()<1?1:deffenderDices.size();
		int attacker_dice_list_size = attackerDices.size()<1?1:attackerDices.size();
		
		Integer deffenderBestDice = deffenderDices.get(deffender_dice_list_size - 1);
		Integer attackerBestDice = attackerDices.get(attacker_dice_list_size - 1);
		Integer deffender2ndBestDice = 1;// deffenderDices.get(deffenderDices.size()-2);
		Integer attacker2ndBestDice = 2;// attackerDices.get(attackerDices.size()-2);
		while ((attackerBestDice == deffenderBestDice) || (attacker2ndBestDice == deffender2ndBestDice)) {// workaround
																											// to avoid
																											// ties
			LOG.info("Dice tie detected. Rolling dices again.");
			attackerDices = rollDices(attacker, offendingCountry,
					(offendingCountry.getArmies() < MAX_DICE_ROLLS_ATTACKER) ? offendingCountry.getArmies()
							: MAX_DICE_ROLLS_ATTACKER);
			deffenderDices = rollDices(deffender, deffendingCountry,
					(/* deffendingCountry */offendingCountry.getArmies() < MAX_DICE_ROLLS_DEFFENDER)
							? /* deffendingCountry */offendingCountry.getArmies()
							: MAX_DICE_ROLLS_DEFFENDER);

			deffenderBestDice = deffenderDices.get(deffender_dice_list_size - 1);
			attackerBestDice = attackerDices.get(attacker_dice_list_size - 1);
			if (deffender_dice_list_size == 2) {// TODO check if this is working
				deffender2ndBestDice = deffenderDices.get(deffender_dice_list_size - 2);
				attacker2ndBestDice = attackerDices.get(attacker_dice_list_size - 2);
			}
		}

		attacker.setDiceRollResultSet(attackerDices);
		deffender.setDiceRollResultSet(deffenderDices);
		List<Integer> attackerDiceRollResultSet = attacker.getDiceRollResultSet();
		List<Integer> deffenderDiceRollResultSet = deffender.getDiceRollResultSet();
		LOG.info("Battle dice rolls\r\nAttacker (" + attacker.getName() + "," + offendingCountry.getName()
				+ ") dice rolls results: " + attackerDiceRollResultSet + "\r\nDeffender  (" + deffender.getName() + ","
				+ deffendingCountry.getName() + ") dice rolls results: " + deffenderDiceRollResultSet);
		/*
		 * The outcome of the attack is determined by comparing the defenders best dice
		 * roll with the attackers best dice roll. If the defender rolls greater or
		 * equal to the attacker, then the attacker loses an army otherwise the defender
		 * loses an army.
		 */

		Integer attackerLastDice = attackerDiceRollResultSet.get(attackerDiceRollResultSet.size() - 1);
		Integer deffenderLastDice = deffenderDiceRollResultSet.get(deffenderDiceRollResultSet.size() - 1);

		Country[] cn = new Country[2];
		cn = this.playerStrategy.doAttack(offendingCountry, deffendingCountry, attackerLastDice, deffenderLastDice);
//		
//		Country winner = (attackerLastDice > deffenderLastDice) ? offendingCountry : deffendingCountry;
//		Country loser = (attackerLastDice < deffenderLastDice) ? offendingCountry : deffendingCountry;
//		this.playerStrategy.doAttack(offendingCountry,deffendingCountry,attackerLastDice,deffenderLastDice);
//		
		
		Country winner = cn[0];
		Country loser = cn[1];
		LOG.info(winner.getName() + " (" + getOwner(winner).getName() + ") has the best dice roll of all. "
				+ loser.getName() + " loses one army for that.");
		// loser.setArmies(loser.getArmies()>0?loser.getArmies()-1:0);
		loser.setArmyQty(loser.getArmies() > 0 ? loser.getArmies() - 1 : 0); // update country army instead of player
																				// army

		LOG.info("1st round WINNER: " + winner + " LOSER: " + loser);
		/*
		 * If the defender rolled two dice, then his other dice roll is compared to the
		 * attacker's second best dice roll and a second army is lost by the attacker or
		 * defender in the same way.
		 */
		if (deffenderDiceRollResultSet.size() == 2) {// TODO fix this:assumes the attacker rolled more dices than the
														// deffender
			try {
				winner = (attackerDiceRollResultSet
						.get(attackerDiceRollResultSet.size() - 2) > deffenderDiceRollResultSet.get(0))
								? offendingCountry
								: deffendingCountry;
				loser = (attackerDiceRollResultSet
						.get(attackerDiceRollResultSet.size() - 2) < deffenderDiceRollResultSet.get(0))
								? offendingCountry
								: deffendingCountry;
				LOG.info(winner.getName() + " (" + getOwner(winner).getName() + ") has the 2nd best dice roll. "
						+ loser.getName() + " loses one army.");
				// loser.setArmies(loser.getArmies()>0?loser.getArmies()-1:0);
				loser.setArmyQty(loser.getArmies() > 0 ? loser.getArmies() - 1 : 0); // update country army instead of
																						// player army
				LOG.info("2nd round WINNER: " + winner + " LOSER: " + loser);
			} catch (Exception e) {
				// TODO e.printStackTrace();
				LOG.error("deffender picked more dice rolls than attacker.. context not found on project description");
			}
		}

		setIsAllOutMode(false);
		LOG.info("ALL OUT MODE DISABLED");

	}

	/**
	 * @param player
	 * @param maxDiceRolls
	 * @param player
	 * @param country
	 * @return
	 * 
	 */
	private List<Integer> rollDices(Player player, Country country, int maxDiceRolls) {
		LOG.info("Rolling dices for " + player.getName() + " (" + country.getName() + ")");

		int diceRollsNum = maxDiceRolls;

		if (!this.getIsAllOutMode()) {
			diceRollsNum = playerStrategy.askNumber("Rolling dices",
					player.getName() + ", how many dices do you want to roll?", 1, maxDiceRolls, 0, false);
		} else
			LOG.info("All Out Mode: maximum dice rolls selected: " + maxDiceRolls);

		List<Integer> diceRollsResultSet = new ArrayList<Integer>();
		for (int i = 0; i < diceRollsNum; i++) {
			diceRollsResultSet.add(getDiceRollResult(null));
		}
		Collections.sort(diceRollsResultSet);
		return diceRollsResultSet;
	}

	private int getDiceRollResult(Player attacker) {// TODO doesnt really need parameter for now
		return RANDOM.nextInt(6);
	}

	public Player getOwner(Country country) {
		for (Player player : getPlayers()) {
			if (player.getCountries().contains(country)) {
				return player;
			}
		}
		return null;
	}

	/**
	 * @param OffendingCountry
	 * @param DeffendingCountry
	 */
	void giveLoserCountryToWinnerPlayer(Country OffendingCountry, Country DeffendingCountry) {
		Player attacker = getOwner(OffendingCountry);
		Player deffender = getOwner(DeffendingCountry);
		LOG.info("ANNEXING COUNTRY START\r\nattacker: " + attacker + "\r\ndefender: " + deffender);

		List<Country> attacker_countries = new CopyOnWriteArrayList<Country>(attacker.getCountries());
		attacker_countries.add(DeffendingCountry);
		attacker.setCountries(attacker_countries);
		List<Country> deffender_countries = new CopyOnWriteArrayList<Country>(deffender.getCountries());
		deffender_countries.remove(DeffendingCountry);
		deffender.setCountries(deffender_countries);
		LOG.info("ANNEXING COUNTRY END\r\nattacker: " + attacker + "\r\ndefender: " + deffender);
	}

	public int getLastDiceRollResult() {
		return lastDiceRollResult;
	}

	public void setPlayers(List<Player> asList) {
		players = asList;
	}

	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * @param continent
	 * @return the countriesByContinent
	 */
	public List<Country> getCountriesByContinent(Continent continent) {
		return MapInterface.getCountriesByContinent(continent); // countriesByContinent.get(countriesByContinent.indexOf(continent));
	}

	public List<Continent> getContinents() {
		List<Continent> continents = MapInterface.getContinents();
		return continents;
	}

	public List<Country> getCountries() {
		List<Country> countries = MapInterface.getCountries();
		return countries;
	}

	public Card getRandomCard() {
		Sort[] card_types = Sort.values();
		return new Card(card_types[RANDOM.nextInt(card_types.length)]);
	}

	public void setTurn(int i) {
		this.turn = i;
		broadcastTurnChanged(turn);
	}

	private void broadcastTurnChanged(int turn) {
		for (TurnSubscriber subscriber : turnsubscribers) {
			subscriber.turnChangedTo(turn);
		}
	}

	public int getTurn() {
		return this.turn;
	}

	public void getBoardInfo() {
		setChanged();
		// notify all attached Observers of a change
		notifyObservers(this);

	}

	public HashMap<String, Double> getPercentageByPlayers() {
		HashMap<String, Double> playersDomination = new HashMap<String, Double>();
		for (Player x : players) {
			float noOfCountriesOwned = x.getCountries().size();

			double result = (float) (noOfCountriesOwned / totalNoOfCountries) * 100;
			playersDomination.put(x.getName(), result);

		}
		return playersDomination;

	}

	public HashMap<String, String> getContinentsByPlayers() {
		HashMap<String, String> continentOwner = new HashMap<String, String>();
		for (Player x : players) {
			if (x.getOwnedContinent() != null)
				continentOwner.put(x.getName(), x.getOwnedContinent().toString());
			else
				continentOwner.put(x.getName(), "NULL");

		}

		return continentOwner;

	}

	public HashMap<String, Integer> getTotalArmiesOwnedByAllPlayers() {
		HashMap<String, Integer> armiesList = new HashMap<String, Integer>();

		for (Player x : players) {
			int countryArmy = 0;
			for (Country el : x.getCountries()) {
				countryArmy += el.getArmies();
			}

			Integer totalArmies = x.getArmies() + countryArmy;

			armiesList.put(x.getName(), totalArmies);

		}
		return armiesList;

	}

	public boolean getIsAllOutMode() {
		return this.isAllOutMode;
	}

	public void setIsAllOutMode(boolean userOk) {
		isAllOutMode = userOk;
	}

	public void subscribeTurnEvents(TurnSubscriber turnsubscriber) {
		this.turnsubscribers.add(turnsubscriber);
		
	}

}
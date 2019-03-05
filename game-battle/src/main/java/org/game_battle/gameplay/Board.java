/**
 * 
 */
package org.game_battle.gameplay;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game_battle.gameplay.Card.Sort;

/**
 * This class sets up the countries and continents on the game board
 * 
 * @author Vini
 * @version Alpha
 * @date 5/02/19
 **/
public class Board {
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

	private static final Random RANDOM = new Random();

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
		LOG.info("\r\n\r\n<<UI prompt in the background. Please use ALT-TAB>>");//TODO make the UI come to foreground
		int totalPlayers = UI.askNumber("Initializing board", "How many players?", 2, 25);
		LinkedList<Player> players = new LinkedList<Player>();
		for (int i = 0; i < totalPlayers; i++) {
			String name = "dummy player";
			name = UI.askText("What is player "+(i+1)+" name?", "Initializing board - "+totalPlayers+" players");
			players.add(new Player(this, name ));
		}
		setPlayers(players);
		/*
		 * setPlayers(new LinkedList<Player>(Arrays.asList(new Player(this, "x"), new
		 * Player(this, "y"), new Player(this, "z"), new Player(this, "a"))));
		 */
		// List<List<Country>> L = getRandomCountriesLists(getPlayers().size());
		distributeCountries(getPlayers().size());
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
			//countriesToDistribute.remove(c);
			k = (++k==players.size())?0:k;

		}
	}

	public int getArmiesFromCards(List<Card> cards) {
		HashMap<Card.Sort, Integer> cardsCount = new HashMap<Card.Sort, Integer>() {
			{
				put(Card.Sort.INFANTRY, 0);
				put(Card.Sort.CAVALRY, 0);
				put(Card.Sort.ARTILLERY, 0);
			}
		};

		for (Card card : cards) {
			Sort cardtype = card.getType();
			cardsCount.replace(cardtype, cardsCount.get(cardtype) + 1);
		}
		LOG.info("cards: " + cards + " cardtype: "+" cardscount: "+cardsCount);
		//LOG.info("cards: " +cards+" cardscount: "+cardsCount);
		// Finally, if the player owns three cards of different sorts or the same sorts,
		// he can exchange them for armies.

		// Each card is either an infantry, cavalry, or artillery card. During a
		// player’s reinforcement phase, a player can exchange a set of three cards of
		// the same kind, 
		// or a set of three cards of all different kinds for a number of armies that
		// increases every time any player does so. The number of armies a player will
		// get for
		// cards is first 5, then increases by 5 every time any player does so (i.e. 5,
		// 10, 15, …). TODO A player that conquers the last country owned by another player
		// receives
		// all the cards held by that player. If a player holds five cards during his
		// reinforcement phase, he must exchange three of them for armies.

		//// getEligibleArmies manages the counter to 5, 10, 15.. across players turns
		//// Cards is an utility class for now (maybe should be Board.Cards because
		//// Board HAS Cards).
		// The number of armies a player will get for cards is first 5, then increases
		//// by 5 every time any player does so (i.e. 5, 10, 15, …). In any case, the
		//// minimal number of reinforcement armies is 3.

		//boolean equals = cardsCount.values().contains(CARDS_EQUAL_DIFFERENT_AMOUNT);
		
		Collection<Integer> cardsCountValues = cardsCount.values();
		int equals = 0, diffs = 0;
		for (Integer count : cardsCountValues) {
			if (count==CARDS_EQUAL_DIFFERENT_AMOUNT) {
				equals=CARDS_EQUAL_DIFFERENT_AMOUNT;
				LOG.info("detected " + CARDS_EQUAL_DIFFERENT_AMOUNT + " cards of equal type.");
				
				break;
			} else if (count!=0) {
				diffs++;
				if (diffs==CARDS_EQUAL_DIFFERENT_AMOUNT) {
					LOG.info("detected "+CARDS_EQUAL_DIFFERENT_AMOUNT+" cards of different type.");
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

		//LOG.info("equals: " + equals +" diffs: "+diffs);
		
		if (equals>=CARDS_EQUAL_DIFFERENT_AMOUNT || diffs>=CARDS_EQUAL_DIFFERENT_AMOUNT) {
			cardsToGive += 5;
			LOG.info("player gained "+cardsToGive+" armies from their cards.");
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

	public Player doBattle(Country offendingCountry, Country deffendingCountry) {
		// TODO BUILD2 update players armies numbers according to logic below
		/*
		 * A battle is then simulated by the attacker rolling at most 3 dice (which
		 * should not be more than the number of armies contained in the attacking
		 * country) and the defender rolling at most 2 dice (which should not be more
		 * than the number of armies contained in the attacking country).
		 */
		/*
		 * The outcome of the attack is determined by comparing the defenders best dice
		 * roll with the attackers best dice roll. If the defender rolls greater or
		 * equal to the attacker, then the attacker loses an army otherwise the defender
		 * loses an army. If the defender rolled two dice, then his other dice roll is
		 * compared to the attacker's second best dice roll and a second army is lost by
		 * the attacker or defender in the same way.
		 */
		// Still to do
		// pls help😅
		//
		Player attacker = getOwner(offendingCountry);
		Player deffender = getOwner(deffendingCountry);
		Player winner = (getDiceRollResult(attacker) > getDiceRollResult(deffender)) ? attacker : deffender;
		LOG.info("\r\nBattle winner: " + winner);
		return winner;
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
		LOG.info("ANNEXING COUNTRY START\r\nattacker: "+attacker+"\r\ndefender: "+deffender);
		
		List<Country> attacker_countries = new CopyOnWriteArrayList<Country>(attacker.getCountries());
		attacker_countries.add(DeffendingCountry);
		attacker.setCountries(attacker_countries);
		List<Country> deffender_countries = new CopyOnWriteArrayList<Country>(deffender.getCountries());
		deffender_countries.remove(DeffendingCountry);
		deffender.setCountries(deffender_countries);
		LOG.info("ANNEXING COUNTRY END\r\nattacker: "+attacker+"\r\ndefender: "+deffender);
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
		this.turn  = i;
	}

	public int getTurn() {
		return this.turn;
	}

}
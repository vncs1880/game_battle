/**
 * 
 */
package org.game_battle.model.Implementation;

import org.game_battle.view.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Allows the creation of player objects.
 * 
 * @author Vini
 * @version Alpha
 * @date 5/02/19
 **/
public class Player extends Observable {
	private static final Logger LOG = LogManager.getLogger(Player.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name + "(" + "armies:" + this.armies + ", cards:" + cards + ", countries:" + countries + ")";// super.toString();
	}

	private static final int MINIMUM_ARMIES_TO_QUALIFY_FOR_ATTACK = 2;
	private int armies = 0;

	/**
	 * @param armies the armies to set
	 */
	public void setArmies(int armies) {
		this.armies = armies;
	}

	private Board board;
	private List<Country> countries = new ArrayList<Country>();
	private List<Card> cards;
	private int previousCountriesQty = 0;
	private String name;
	private List<Integer> diceRollsResultSet = new ArrayList<Integer>();
	private Continent ownedContinent;
	private UI ui;
	// private boolean isAllOutMode = false;

	public Continent getOwnedContinent() {
		return ownedContinent;
	}

	public void setOwnedContinent(Continent ownedContinent) {
		this.ownedContinent = ownedContinent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param board game board
	 * @param name
	 * 
	 */
	public Player(Board board, String name) {
		ownedContinent = null;
		this.board = board;
		this.name = name;
		cards = new LinkedList<Card>(distributeCards(board));
		ui = new UI();
	}

	/**
	 * @param board
	 * @return Return Random List of Cards
	 */
	private List<Card> distributeCards(Board board) {
		return Arrays.asList(board.getRandomCard(), board.getRandomCard(), board.getRandomCard());
	}

	public void setCountries(List<Country> list) {
		countries = list;
	}

	public void Reinforcement() {
		// In the reinforcements phase, the player is given a number of armies that
		// depends on the number of countries he owns (# of countries owned divided by
		// 3, rounded down).
		// If the player owns all the countries of an entire continent, the player is
		// given an amount of armies
		// corresponding to the continent’s control value.
		// Player.resetNumberArmies
		// armies = 0;
		// For each Continent:
//			if allContinentBelongsTo(Player) 
//				Player.updateNumberArmies(NumberArmies(Continent.control_value))  
		//// updateNumberArmies just accumulates the number
//			else
//				totalCountriesOwnedByPlayer += Continent.CountriesOwnedBy(Player)
		//// add accumulated countries after all continents are checked
		// Player.updateNumberArmies(RoundDown(totalCountriesOwnedByPlayer/3))
		// OR (needs elaboration tho)...
		// For each Continent
//			Continent.CalculateNumberArmies(Player)
		List<Continent> continents = board.getContinents();
		int totalCountriesOwnedInAllContinents = 0;
		int totalArmies = 0;// this.getArmies();//0;
		for (Continent continent : continents) {
			int totalCountriesOwnedInThisContinent = 0;
			List<Country> countriesByContinent = board.getCountriesByContinent(continent);
			for (Country country : countriesByContinent) {
				if (countries.contains(country)) {
					totalCountriesOwnedInAllContinents += 1;
					totalCountriesOwnedInThisContinent += 1;
				}
			}
			if (totalCountriesOwnedInThisContinent == countriesByContinent.size()) {
				totalArmies += continent.getControlValue();
				ownedContinent = continent;

				LOG.info(/* "\r\n" + */this.name + " occupies all " + continent + ". Gained "
						+ continent.getControlValue() + " armies for that.");
				totalCountriesOwnedInAllContinents -= totalCountriesOwnedInThisContinent;
			}
		}
		setArmiesQtyFromCountriesQty(totalCountriesOwnedInAllContinents, /* this.getArmies() + */totalArmies);

		// Finally, if the player owns three cards of different sorts or the same sorts,
		// he can exchange them for armies.
		//
		// “Do you want to try to get armies from your cards?”
		// If YES:
		// Player.updateNumberArmies(Cards.getEligibleArmies(Player.getCards()))
		//// if not eligible, Cards.getEligibleArmies = 0
		LOG.info("Starting player " + getName() + "'s turn #" + board.getTurn());

		List<Card> player_cards = getCards();
		/*
		 * if ( player_cards.size() > 4 || UI.isUserOk("Reinforcement phase",
		 * this.getClass().getEnclosingMethod(). getName()+
		 * 
		 * ". \n\rDo you wanna try to get MORE armies from your cards? " +
		 * player_cards)) {
		 */

		if ((!(player_cards.size() < 3)) && (board.playerStrategy.isUserOk("Reinforcement phase",
				"Initiate Card Exchange? " + player_cards + "\r\nDo you wanna try to get MORE armies from your cards?")
				|| player_cards.size() > 4)) {
			LOG.info("Card Exchange started.");
			int armiesFromCards = board.getArmiesFromCards(this);

			if (armiesFromCards > 0) {
				setArmiesFortification(/* this.getArmies() + */ armiesFromCards);
				LOG.info("Success exchanging cards, gained " + armiesFromCards + " armies.");

				List<Card> playercards = new CopyOnWriteArrayList<Card>(player_cards);
				for (Card card : playercards) {
					getCards().remove(card);
					LOG.info("Removing card " + card + " from player hand.");// TODO fix this removing all cards
				}
			}
		} else
			LOG.info("Skipping Card Exchange view");
		// LOG.info("Getting more armies from cards result: " + this.toString());
	//}else LOG.info("User opted out of Card Exchange");

	// Once the total number of reinforcements is determined for the player’s turn,
	// the player may place the armies on any country he owns, divided as he wants.
	//
	// For each Player.getCountries():
//			If totalArmiesOwnedByPlayer == 0 then break
//			N = user_input //if >  totalArmiesOwnedByPlayer, N = totalArmiesOwnedByPlayer
//			Country.setArmiesNumber(n)
	if(this.armies>0)

	{
		for (Country country : countries) {			
			int qtyArmies = ui.askNumber(
							"Reinforcement phase", "How many armies do you want to put in country " + country.toString()
									+ " ? [" + (countries.indexOf(country) + 1) + "/" + countries.size() + "]",
							0, this.armies);

			if (qtyArmies <= this.armies) {
				LOG.info("Adding " + qtyArmies + " armies to country " + country.getName() + ". Previous was "
						+ country.getArmies()/* this.toString() */);
				country.setArmyQty(country.getArmies() + qtyArmies);
				this.armies -= qtyArmies;
			}
			if (this.armies == 0)
				break;
		}
	}
	// LOG.info(this.toString());

	setChanged();

	// notify all attached Observers of a change
	notifyObservers(this);

	}

	/**
	 * 
	 * @return Return Armies
	 */
	public int getArmies() {
		return this.armies;
	}

	/**
	 * 
	 * @return Return list of Cards
	 */

	public List<Card> getCards() {
		return cards;
	}

	/**
	 * 
	 * @param totalCountriesOwned countries owned by the player
	 * @param totalArmies         totalArmies belongs to a player
	 */

	private void setArmiesQtyFromCountriesQty(int totalCountriesOwned, int totalArmies) {
		// TODO make sure this is rounded down
		// setArmies(totalCountriesOwned / 3 + totalArmies);
		int countries_div = totalCountriesOwned / 3;
		this.setArmiesFortification(/* this.getArmies() + */ countries_div + totalArmies);
	}

	/**
	 * @param i number of armies
	 */
	private void setArmiesFortification(int i) {
		// In any case, the minimal number of reinforcement armies is 3.
		// If totalArmiesOwnedByPlayer < 3:
//			totalArmiesOwnedByPlayer = 3
		//
		int r = i;
		if (i < 3) {
			r = 3;
		}
		LOG.info("Updating " + r + " armies now. Previous amount was " + this.armies/* +" \r\n"+this.toString() */);
		this.armies += r;
		/*
		 * if (i == -1) { this.armies = 0; }
		 */

	}

	public void Attack() {
		/*
		 * Once all the reinforcement armies have been placed by the player, the attacks
		 * phase begins. In the attack phase, the player may choose one of the countries
		 * he owns that contains two or more armies, and declare an attack on an
		 * ADJACENT country that is owned by another player.
		 */
		// elligibleAttackerCountries = Player.getCountries(Country.getArmiesCount() >
		// 2)

		List<Country> elligibleAttackerCountries = getAttackerCountries(MINIMUM_ARMIES_TO_QUALIFY_FOR_ATTACK);
		// LOG.info("elligible Attacker Countries: " + elligibleAttackerCountries);
		if (!elligibleAttackerCountries.isEmpty()) {
			// OffendingCountry = elligibleAttackerCountries[UI.get_user_selection]
			// DeffendingCountry =
			// Board.getElligibleTargets(OffendingCountry)[UI.get_user_selection]
			//// elligible targets are adjacent nodes

			Country OffendingCountry = board.playerStrategy.selectCountry("Attack phase", "Select attacker country",
					elligibleAttackerCountries);
			List<Country> neighbours = new CopyOnWriteArrayList<>(OffendingCountry.getNeighbours());
			// LOG.info("neighbours before filtering: "+neighbours);
			neighbours.remove(OffendingCountry);
			for (Country country : neighbours) {
				if (board.getOwner(OffendingCountry) == board.getOwner(country)) {
					neighbours.remove(country);
				}
			}
			// LOG.info("neighbours after filtering: "+neighbours); //DONE BUILD2 currently
			// showing adjacent, should look for connected. Actually this is correct.
			LOG.info("neighbour countries/elligible targets: " + neighbours);
			if (!neighbours.isEmpty()) {
				Country DeffendingCountry = board.playerStrategy.selectCountry("Attack phase", "Select target country",
						neighbours /* board.getElligibleTargets(OffendingCountry) */);
				// The attacker can choose to continue attacking until either all his armies or
				// all the defending armies have been eliminated.
				// While (OffendingCountry.getTotalArmies() > 0) AND
				// (DeffendingCountry.getTotalArmies() > 0) do {
				// <<Board.Battle()>>
				// }
				LOG.info(OffendingCountry + " vs " + DeffendingCountry);
				String attacker = board.getOwner(OffendingCountry).name;
				String deffender = board.getOwner(DeffendingCountry).name;

				boolean allOutModeOk = board.playerStrategy.isUserOk("All out mode?",
						" Attack proceeds with maximum number of dice and end only \r\n"
								+ "when either the attacker conquers the attacked, or the \r\n"
								+ "attacker cannot attack anymore.");
				this.board.setIsAllOutMode(allOutModeOk);
				if (allOutModeOk) {
					LOG.info("ALL OUT MODE ENABLED");
				}

				// TODO is it the player armies or the country armies
				while (((OffendingCountry.getArmies() > 0) && (DeffendingCountry.getArmies() > 0))) {
					if (!this.board.getIsAllOutMode()) {
						if (!board.playerStrategy.isUserOk("Attack phase", attacker + ", do you want to attack " + deffender + " ?")) {
							break;
						}
					} else
						LOG.info("All Out Mode: skipping user input.");
					LOG.info("Starting Battle: " + attacker + " attacking " + deffender + ".");
					// Board.Battle(OffendingCountry, DeffendingCountry)
					board.doBattle(OffendingCountry, DeffendingCountry);
					// If all the defender's armies are eliminated the attacker captures the
					// territory.
					// Board.updateTerritories(DeffendingCountry)
					//// just change ownership if DeffendingCountry.getTotalArmies() == 0
				}
				if (DeffendingCountry.getArmies() == 0) {
					board.giveLoserCountryToWinnerPlayer(OffendingCountry, DeffendingCountry);
					// LOG.info("All the defender's armies are eliminated." + attacker + " captured
					// " + DeffendingCountry);
					LOG.info("All the defender's armies are eliminated. Player " + attacker + " captured "
							+ DeffendingCountry);
					/*
					 * The attacking player must then place a number of armies in the conquered
					 * country which is greater or equal than the number of dice that was used in
					 * the attack that resulted in conquering the country. A player may do as many
					 * attacks as he wants during his turn.
					 */
					if (this.armies > 0) {
						int minimumArmies = board.getLastDiceRollResult();
						int armies_to_occupy = board.ui.askNumber("Attack phase",
								"How many armies to occupy defeated country?", minimumArmies, this.armies);
						DeffendingCountry.setArmyQty(DeffendingCountry.getArmies() + armies_to_occupy);
						this.armies -= armies_to_occupy;
						LOG.info(attacker + " places " + armies_to_occupy + " armies in " + DeffendingCountry);
					} else
						LOG.info("no armies to occupy defeated country.");

				} /*
					 * * else if (OffendingCountry.getArmies() == 0) { LOG.info(attacker +
					 * " lost battle."); }
					 */
			} else {
				LOG.info("No elligible target countries.");
			}

		} else {
			LOG.info("No elligible attacker countries.");
		}
	}

	/**
	 * 
	 * @param
	 * @return Return attacking neighbour country
	 */
	private List<Country> getAttackerCountries(int i) {
		List<Country> attackers = new ArrayList<Country>();

		for (Country country : countries) {
			if (country.getArmies() >= i) {
				attackers.add(country);
			}
		}
		return attackers;
	}

	/**
	 * 
	 * @return list of countries
	 */

	public List<Country> getCountries() {
		return countries;
	}

	public void Fortification() {
		/*
		 * Once he declares that he will not attack anymore (or cannot attack because
		 * none of his countries that have an adjacent country controlled by another
		 * player is containing more than one army), the fortification phase begins. In
		 * the fortification phase, the player may move any number of armies from one of
		 * his owed countries to the other, provided that there is a path between these
		 * two countries that is composed of countries that he owns. Only one such move
		 * is allowed per fortification phase.
		 * 
		 */

		// For each country in Player.getCountryList():
		// If coutry.hasArmies() AND GameBoard.areConnected(country, defeatedCountry):
//			Z = UI.ask(“Move how many armies from X to Y? [0 to country.getArmiesNumber()]”)
//			country.setArmiesNumber(country.getArmiesNumber() - Z)
//			defeatedCountry.setArmiesNumber(Z)	
		// break FOR //only 1 move is allowed

		for (Country country : countries) {
			List<Country> neighbours = new CopyOnWriteArrayList<>(country.getNeighbours());
			for (Country neighbour : neighbours) {
				if ((board.getOwner(country) != board.getOwner(neighbour))
						|| (country.getName().equals(neighbour.getName()))) {
					neighbours.remove(neighbour);
				}
			}
			LOG.info(
					"Elligible territory neighbours owned by " + board.getOwner(country).getName() + ": " + neighbours);
			if (country.getArmies() > 0 && neighbours.size() > 0) {
				Country selected = board.playerStrategy.selectCountry("Fortification phase",
						"Want to move armies from " + country + " to a neighbour?", neighbours);
				if (selected != null && country.getArmies() > 0) {
					int n_armies = ui.askNumber("Fortification phase",
							"How many armies from " + country + " to " + selected, 0, country.getArmies());
					country.setArmyQty(country.getArmies() - n_armies);
					LOG.info("Player " + this.name + " moved " + n_armies + " army from " + country.getName() + " to "
							+ selected.getName() + " previous army qty was " + selected.getArmies());
					selected.setArmyQty(selected.getArmies() + n_armies);// DONE bug here. not really updating selected
																			// army qty
					break;
				}
			} else {
				LOG.info(country.getName() + ": No armies or no neighbours to move around.");
			}
		}

		/*
		 * Once the move is made or the player forfeits his fortification phase, the
		 * player’s turn ends and it is now the next player’s turn.
		 */

	}

	/**
	 * 
	 * @return number of countries before attack
	 */
	public int getPreviousCountriesQty() {
		return previousCountriesQty;
	}

	public void setPreviousCountriesQty(int currentCountriesQty) {
		previousCountriesQty = currentCountriesQty;
	}

	public void setDiceRollResultSet(List<Integer> rollDices) {
		this.diceRollsResultSet = rollDices;

	}

	public List<Integer> getDiceRollResultSet() {
		// TODO Auto-generated method stub
		return diceRollsResultSet;
	}

	public void getCardInfo() {
		setChanged();
		// notify all attached Observers of a change
		notifyObservers(this);

	}

	public List<Card> getCardExchange() {
		return this.getCards();
	}

}
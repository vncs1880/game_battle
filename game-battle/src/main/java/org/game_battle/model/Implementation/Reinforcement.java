package org.game_battle.model.Implementation;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.game_battle.view.UI;

import org.game_battle.view.UI;

public class Reinforcement {

	private static final Logger LOG = LogManager.getLogger(Player.class);

	Player player;

	public void Reinforcement(int armies, Board board, List<Country> countries, String playerName, Player player) {

		/*
		 * // In the reinforcements phase, the player is given a number of armies that
		 * // depends on the number of countries he owns (# of countries owned divided
		 * by // 3, rounded down). // If the player owns all the countries of an entire
		 * continent, the player is // given an amount of armies // corresponding to the
		 * continent’s control value. // Player.resetNumberArmies armies = 0; // For
		 * each Continent: // if allContinentBelongsTo(Player) //
		 * Player.updateNumberArmies(NumberArmies(Continent.control_value)) ////
		 * updateNumberArmies just accumulates the number // else //
		 * totalCountriesOwnedByPlayer += Continent.CountriesOwnedBy(Player) //// add
		 * accumulated countries after all continents are checked //
		 * Player.updateNumberArmies(RoundDown(totalCountriesOwnedByPlayer/3)) // OR
		 * (needs elaboration tho)... // For each Continent //
		 * Continent.CalculateNumberArmies(Player)
		 */
		List<Continent> continents = board.getContinents();
		int totalCountriesOwnedInAllContinents = 0;
		int totalArmies = 0;
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
				totalArmies = continent.getControlValue();
				LOG.info(/* "\r\n" + */playerName + " occupies all " + continent + ". Gained " + totalArmies
						+ " armies for that.");
				totalCountriesOwnedInAllContinents -= totalCountriesOwnedInThisContinent;
			}
		}

			
		List<Card> player_cards = player.getCards();
		if (player_cards.size() > 4 || UI.isUserOk("Reinforcement phase", /*
																			 * this.getClass().getEnclosingMethod().
																			 * getName()+
																			 */
				"Starting player " + player.getName()
						+ "'s turn. \n\rDo you wanna try to get MORE armies from your cards? " + player_cards)) {

			int armiesFromCards = board.getArmiesFromCards(player);
//			armies = player.setArmies(armies + armiesFromCards);
			if (armiesFromCards > 0) {
				LOG.info("Success exchanging cards, gained " + armiesFromCards + " armies.");
				List<Card> playercards = new CopyOnWriteArrayList<Card>(player_cards);
				for (Card card : playercards) {
					player.getCards().remove(card);
					LOG.info("Removing card " + card + " from player hand.");// TODO fix this removing all cards
				}
			}

			// LOG.info("Getting more armies from cards result: " + this.toString());
		}

		/*
		 * // Once the total number of reinforcements is determined for the player’s
		 * turn, // the player may place the armies on any country he owns, divided as
		 * he wants. // // For each Player.getCountries(): // If
		 * totalArmiesOwnedByPlayer == 0 then break // N = user_input //if >
		 * totalArmiesOwnedByPlayer, N = totalArmiesOwnedByPlayer //
		 * Country.setArmiesNumber(n)
		 */
		if (armies > 0) {
			for (Country country : countries) {
				int qtyArmies = UI.askNumber("Reinforcement phase", "How many armies do you want to put in country "
						+ country.toString() + " ? [" + (countries.indexOf(country) + 1) + "/" + countries.size() + "]",
						0, armies);
				if (qtyArmies <= armies) {
					LOG.info("Adding " + qtyArmies + " armies to country " + country.getName() + ". Previous was "
							+ country.getArmies()/* this.toString() */);
					country.setArmyQty(country.getArmies() + qtyArmies);
					armies -= qtyArmies;
					
				//	player.updateArmy(armies);

				}
				if (armies == 0)
					break;
			}
		}
		// LOG.info(this.toString());
	}
}
package org.game_battle.view;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import org.game_battle.model.Implementation.Board;
import org.game_battle.model.Implementation.Continent;
import org.game_battle.model.Implementation.Player;

public class PlayerDominationView implements Observer {

	public void update(Observable obs, Object x) {
		// redraw my clock�s reading after I was notified

		HashMap<String, Double> playersDomination = ((Board) obs).getPercentageByPlayers();
		HashMap<String, String> ownedContinent = ((Board) obs).getContinentsByPlayers();
		HashMap<String, Integer> armiesOfPlayers = ((Board) obs).getTotalArmiesOwnedByAllPlayers();
		System.out.println("\n###########   Begin : Player World Domination View change ###############");
		System.out.println(
				"\nPerecentage of Map controlled by Players: " + playersDomination + "\nNo of Armies owned by Players :"
						+ armiesOfPlayers + "\n Continent Owned by Players :" + ownedContinent);
		System.out.println("###########   End : Player World Domination View change   ###############\n");

	};

}

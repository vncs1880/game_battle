package org.game_battle.view;

import java.util.Observable;
import java.util.Observer;

import org.game_battle.model.Implementation.Player;
import org.game_battle.model.Implementation.Card;

public class CardView implements Observer {

	public void update(Observable obs, Object x) {
		// redraw my clock�s reading after I was notified
//		Card card = (Card) ((Player) obs).getCardExchange();
		System.out.println("\n###########   Begin : Card View  ###############");
		System.out.println(
				"\nCard Exchange: " + ((Player) obs).getCardExchange());
		System.out.println("###########   End : Card View    ###############\n");

	};

}

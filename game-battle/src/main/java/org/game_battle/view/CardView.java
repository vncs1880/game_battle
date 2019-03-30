package org.game_battle.view;

import java.util.Observable;
/**
 * CardView creates the view for observer pattern
 */
import java.util.Observer;

import org.game_battle.model.Implementation.Player;
import org.game_battle.model.Implementation.Card;

public class CardView implements Observer {

	/**
	 * update gets the latest updated model data
	 */
	public void update(Observable obs, Object x) {
		// redraw my clock�s reading after I was notified
		// Card card = (Card) ((Player) obs).getCardExchange();
		System.out.println("\n###########   Begin : Card View  ###############");
		if( ((Player) obs).getCardExchange().isEmpty()){
			System.out.println("Card has been successfully exchanged for armies,"
					+ "The player does not have any cards left");
		}
		else
		System.out.println("\nCard Exchange: " + ((Player) obs).getCardExchange());
		System.out.println("###########   End : Card View    ###############\n");

	};

}

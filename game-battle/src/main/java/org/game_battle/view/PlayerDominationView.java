package org.game_battle.view;


import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import org.game_battle.model.Implementation.Board;


public class PlayerDominationView implements Observer {
	
	public void update(Observable obs, Object x) {
    	//redraw my clock�s reading after I was notified
    	
		
		HashMap<String, Double> playersDomination   = ((Board) obs).getPercentageByPlayers();
    	//String gamePhase = ((Player) obs).getGamePhaseName();
		HashMap<String, Integer> armiesOfPlayers = ((Board) obs).getTotalArmiesOwnedByAllPlayers();
    	System.out.println("\n###########   Begin : Player World Domination View change ###############" ); 
    	System.out.println( "\nPerecentage of Map controlled by Players: "+ playersDomination + "\nNo of Armies owned by Players :" +armiesOfPlayers); 
    	System.out.println("###########   End : Player World Domination View change   ###############\n" ); 


    };

}

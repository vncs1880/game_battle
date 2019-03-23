package org.game_battle.view;


import java.util.Observable;
import java.util.Observer;

import org.game_battle.model.Implementation.Board;


public class PhaseView implements Observer {
	
	public void update(Observable obs, Object x) {
    	//redraw my clock�s reading after I was notified
    	String player   = ((Board) obs).getCurrentPlayer();
    	String gamePhase = ((Board) obs).getGamePhaseName();
    	String actions = ((Board) obs).getActionTakingPlace();
    	System.out.println("\n###########   Begin : Phase View change ###############" ); 
    	System.out.println( "\nCurrent Player: "+ player + "\nGame Phase :" +gamePhase + "\nInfo of current action: "+actions  ); 
    	System.out.println("###########   End : Phase View change   ###############\n" ); 


    };

}

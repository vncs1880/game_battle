package org.game_battle.model.Implementation;


import java.util.List;

import java.util.Observable;
/**
 * PhaseWorldModel creates the obeserver pattern model
 * @author Basant
 *
 */

public class PhaseWorldModel extends Observable {

	public 
	String GetCurrentGamePhase(){return currentGamePhase;};
	String GetCurrentPlayerName(){return currentPlayerName;};
	String GetActions(){return actions;}; 
	String getPerentageControl() {return percentageControl ;};
	List<String> getContinentsControlled(){ return continentsControlled ;};
	int getNumberOfArmies() {return numberOfArmies ; } ;
	void start(Player player, Board board , String game ){
    	// update internal state
		this.currentGamePhase = game;
		this.currentPlayerName = player.getName();
		this.actions = actions;
		this.percentageControl = percentageControl;
		this.continentsControlled = null;
		this.numberOfArmies = numberOfArmies;
		
    	// specify that my state was changed  
    	setChanged();
    	// notify all attached Observers of a change
    	notifyObservers(this);
		};

	private
		String currentGamePhase;
		String currentPlayerName; 		
		String actions; 
		String percentageControl;
		List<String> continentsControlled; 
		int numberOfArmies;
	
}

package org.game_battle.model.Implementation;

import java.util.Collection;
import java.util.List;

import javax.swing.JList;
import javax.swing.JOptionPane;

import org.game_battle.model.Contract.*;

public class PlayerStrategy{

    private Strategy strategy;
    
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
        System.out.println(strategy.toString());
    } 
    
    
    
	public String askText(String msg, String title) {
        return this.strategy.askText(msg, title);

		
	}
	public Country[] doAttack(Country offendingCountry, Country deffendingCountry, int attackerDiceRoll,
			int defenderDiceRoll) {
		return this.strategy.doAttack(offendingCountry,deffendingCountry,attackerDiceRoll,defenderDiceRoll);
	}
	
	public int setArmies(int title) {
        return this.strategy.setArmies(title);

		
	}
	
	public int askNumber(String title,  String prompt, int min, int max, int numOfCountries , boolean flag) {
        return this.strategy.askNumber(title, prompt, min, max, numOfCountries,  flag);

	}
	
	public Collection getObjs(String prompt, Object[] objs) {
		
		return this.strategy.getObjs( prompt, objs);
	}
	
	public boolean isUserOk(String title, String prompt) {
		return this.strategy.isUserOk( title,prompt) ;

	}
	public  Country selectCountry(String title, String prompt, List<Country> countries) {
		return this.strategy.selectCountry(title,  prompt, countries) ;

	}

}
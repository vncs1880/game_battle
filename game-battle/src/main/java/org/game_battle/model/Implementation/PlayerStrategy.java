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
	
	public int askNumber(String title,  String prompt, int min, int max) {
        return this.strategy.askNumber(title, prompt, min, max);

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

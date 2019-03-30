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
    
    public int executeStrategy(int a, int b) {
        return this.strategy.execute(a, b);
    }
    
    
	public String askText(String msg, String title) {
        return this.strategy.askText(msg, title);

		
	}
	
	public int askNumber(String title,  String prompt, int min, int max) {
        return this.strategy.askNumber(title, prompt, min, max);

	}
	
	public Collection getObjs(String prompt, Object[] objs) {
		JList list = new JList(objs);
		JOptionPane.showMessageDialog(null, list, prompt, JOptionPane.PLAIN_MESSAGE);
		return list.getSelectedValuesList();
	}
	
	public boolean isUserOk(String title, String prompt) {
		int picked = JOptionPane.showConfirmDialog(null, prompt, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		return (picked==JOptionPane.OK_OPTION)?true:false;
	}
	public  Country selectCountry(String title, String prompt, List<Country> countries) {
		//MapInterface m = new MapInterface();
		//String[] y = x.toArray(new String[0]);
		Country[] countries_array = countries.toArray(new Country[0]);
		
		Country picked;
		do {
	        //JFrame frame = new JFrame();  frame.toFront();          

			picked = (Country) JOptionPane.showInputDialog(null, prompt
					, title, JOptionPane.QUESTION_MESSAGE
					, null, countries_array, countries_array[0]);
		} while (picked == null);
		
		return picked;
	}

}

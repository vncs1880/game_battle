/**
 * 
 */
package org.game_battle.view;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JList;
import javax.swing.JOptionPane;

import org.game_battle.model.Implementation.Country;
import org.game_battle.model.Implementation.MapInterface;
import org.game_battle.model.Contract.*;

/**
 * @author vncs
 *
 */
public class UI implements Strategy {

	/**
	 * @param args
	 */
	public int execute(int a, int b)
	{
		
		return 0;
	}
	public static void main(String[] args) {
		/*
		 * //Options for the combo box dialog String[] choices = {"Monday", "Tuesday"
		 * ,"Wednesday", "Thursday", "Friday"}; //Input dialog with a combo box String
		 * picked = (String)JOptionPane.showInputDialog(null, "Pick a Day:" ,
		 * "ComboBox Dialog", JOptionPane.QUESTION_MESSAGE , null, choices, choices[0]);
		 */
		MapInterface m = new MapInterface();
		List<Country> countries = m.getCountries();

	//	System.out.println(getObjs("getobjs", countries.toArray()));
		
	//	Country picked = selectCountry("Select one (MANDATORY)", "select country", countries);

	//	System.out.println(picked);
		
	//	System.out.println(askNumber("Select one (MANDATORY)", "pick a number", 1, 12));
	//	System.out.println(askNumber("Select one (MANDATORY)", "pick a number", 20, 55));
		
//		System.out.println(isUserOk("Yes/No?", "test"));
		
	//	System.out.println(askText("",""));

	}
	
	public Collection getObjs(String prompt, Object[] objs) {
		JList list = new JList(objs);
		JOptionPane.showMessageDialog(null, list, prompt, JOptionPane.PLAIN_MESSAGE);
		return list.getSelectedValuesList();
	}
	

	public String askText(String msg, String title) {
		String text = null;
		do {
	        //JFrame frame = new JFrame();            

	        text = JOptionPane.showInputDialog(null, msg, title, JOptionPane.QUESTION_MESSAGE);
		} while (text == null); 
		return text;
	}



	public boolean isUserOk(String title, String prompt) {
		int picked = JOptionPane.showConfirmDialog(null, prompt, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		return (picked==JOptionPane.OK_OPTION)?true:false;
	}

	public int askNumber(String title,  String prompt, int min, int max ,int numOfCountries, boolean flag) {
		//System.out.println("I ma in here");
		List<Integer> collect = IntStream.rangeClosed(min, max).boxed().collect(Collectors.toList());
		Integer[] options = (Integer[]) collect.toArray((new Integer[collect.size()]));
		Integer picked = null;
		do {
			picked = (Integer) JOptionPane.showInputDialog(null, prompt
					, title, JOptionPane.QUESTION_MESSAGE
					, null, options, options[0]);
		} while (picked == null);
		
		return picked;
	}
	
	public int test()
	{
		System.out.println("I m here");
		return 0;
		
	}

	public  Country selectCountry(String title, String prompt, List<Country> countries) {
		//MapInterface m = new MapInterface();
		//String[] y = x.toArray(new String[0]);
		Country[] countries_array = countries.toArray(new Country[0]);
		for(Country str:countries_array) {
			System.out.println("#########################"+str.toString());
			
		}
		
		
		Country picked;
		do {
	        //JFrame frame = new JFrame();  frame.toFront();          

			picked = (Country) JOptionPane.showInputDialog(null, prompt
					, title, JOptionPane.QUESTION_MESSAGE
					, null, countries_array, countries_array[0]);
		} while (picked == null);
		
		return picked;
	}
	
	
	@Override
	public int setArmies(int NumOfArmies) {
		// TODO Auto-generated method stub
		return NumOfArmies;
	}
	
	
	@Override
	public Country[] doAttack(Country offendingCountry, Country deffendingCountry, int attackerDiceRoll,
			int defenderDiceRoll) {
		// TODO Auto-generated method stub
		Country winner = (attackerDiceRoll > defenderDiceRoll) ? offendingCountry : deffendingCountry;
		Country loser = (attackerDiceRoll < defenderDiceRoll) ? offendingCountry : deffendingCountry;
		Country[] cn = new Country[2];
		cn[0] = winner;
		cn[1] = loser;
		return cn;
	}
	
}
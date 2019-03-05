/**
 * 
 */
package org.game_battle.gameplay;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author vncs
 *
 */
public class UI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * //Options for the combo box dialog String[] choices = {"Monday", "Tuesday"
		 * ,"Wednesday", "Thursday", "Friday"}; //Input dialog with a combo box String
		 * picked = (String)JOptionPane.showInputDialog(null, "Pick a Day:" ,
		 * "ComboBox Dialog", JOptionPane.QUESTION_MESSAGE , null, choices, choices[0]);
		 */
		MapInterface m = new MapInterface();
		List<Country> countries = m.getCountries();

		Country picked = selectCountry("Select one (MANDATORY)", "select country", countries);

		System.out.println(picked);

		System.out.println(askNumber("Select one (MANDATORY)", "pick a number", 1, 12));
		System.out.println(askNumber("Select one (MANDATORY)", "pick a number", 20, 55));

		System.out.println(isUserOk("Yes/No?", "test"));

		System.out.println(askText("", ""));

	}

	public static String askText(String msg, String title) {
		String text = null;
		do {
			// JFrame frame = new JFrame();

			text = JOptionPane.showInputDialog(null, msg, title, JOptionPane.QUESTION_MESSAGE);
		} while (text == null);
		return text;
	}

	public static boolean isUserOk(String title, String prompt) {
		int picked = JOptionPane.showConfirmDialog(null, prompt, title, JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		return (picked == JOptionPane.OK_OPTION) ? true : false;
	}

	public static int askNumber(String title, String prompt, int min, int max) {
		List<Integer> collect = IntStream.rangeClosed(min, max).boxed().collect(Collectors.toList());
		Integer[] options = (Integer[]) collect.toArray((new Integer[collect.size()]));
		Integer picked = null;
		do {
			picked = (Integer) JOptionPane.showInputDialog(null, prompt, title, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
		} while (picked == null);

		return picked;
	}

	public static Country selectCountry(String title, String prompt, List<Country> countries) {
		// MapInterface m = new MapInterface();
		// String[] y = x.toArray(new String[0]);
		Country[] countries_array = countries.toArray(new Country[0]);

		Country picked;
		do {
			// JFrame frame = new JFrame(); frame.toFront();

			picked = (Country) JOptionPane.showInputDialog(null, prompt, title, JOptionPane.QUESTION_MESSAGE, null,
					countries_array, countries_array[0]);
		} while (picked == null);

		return picked;
	}

}
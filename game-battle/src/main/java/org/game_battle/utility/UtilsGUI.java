/**
 * 
 */
package org.game_battle.utility;

import java.util.Collection;
import java.util.List;

import javax.swing.JList;
import javax.swing.JOptionPane;

import org.game_battle.model.Implementation.Country;
import org.game_battle.model.Implementation.MapInterface;

/**
 * @author vncs
 *
 */
public class UtilsGUI {

	/**
	 * 
	 */
	public UtilsGUI() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UtilsGUI gui = new UtilsGUI();
		MapInterface m = new MapInterface();
		List<Country> countries = m.getCountries();

		System.out.println(gui.getObjs("getobjs", countries.toArray()));
		
		System.out.println(gui.selectObj("Select one (MANDATORY)", "select obj", countries));

	}
	
	private Object selectObj(String title, String prompt, List objs) {
		Object picked;
		do {
			picked = JOptionPane.showInputDialog(null, prompt, title, JOptionPane.QUESTION_MESSAGE , null, objs.toArray(), objs.get(0));
		} while (picked == null);
		return picked;
	}

	public Collection getObjs(String prompt, Object[] objs) {
		JList list = new JList(objs);
		JOptionPane.showMessageDialog(null, list, prompt, JOptionPane.PLAIN_MESSAGE);
		return list.getSelectedValuesList();
	}
	

	public String askText(String msg, String title) {
		String text = null;
		do {
			text = JOptionPane.showInputDialog(null, msg, title, JOptionPane.QUESTION_MESSAGE);
		} while (text == null); 
		return text;
	}

}

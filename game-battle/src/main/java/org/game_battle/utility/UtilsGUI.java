/**
 * 
 */
package org.game_battle.utility;

import java.awt.GridLayout;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game_battle.TournamentMatch;
import org.game_battle.model.Implementation.Country;
import org.game_battle.model.Implementation.MapInterface;

/**
 * @author vncs
 *
 */
public class UtilsGUI {
	private static final Logger LOG = LogManager.getLogger(UtilsGUI.class);
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
		MapInterface m = new MapInterface("resource/file.map");
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

	public HashMap initTournamentForm() {
		/*
		 * String[] items = {"One", "Two", "Three", "Four", "Five"}; JComboBox<String>
		 * combo = new JComboBox<>(items); JTextField field1 = new
		 * JTextField("1234.56"); JTextField field2 = new JTextField("9876.54");
		 */
        JPanel panel1 = new JPanel(new GridLayout(0, 1));
        ((GridLayout) panel1.getLayout()).setVgap(3);
        //panel.add(new JLabel("Maps:"));panel.add(combo);
        String[] maps = {"Map 1", "Map 2", "Map 3","Map 4","Map 5"};
        //panel1.add(new JLabel("Maps:"));			 
        JList<Object> mapslist = new JList<Object>(maps);
        mapslist.setLayoutOrientation(JList.VERTICAL_WRAP);
        mapslist.setVisibleRowCount(-1);
		panel1.add(mapslist);
        String[] strategies = {"Aggresive", "Benevolent", "Random","Cheater"};
        //panel1.add(new JLabel("Strategies(min 2):"));
        JList<Object> strategieslist = new JList<Object>(strategies);
        strategieslist.setLayoutOrientation(JList.VERTICAL_WRAP);
        strategieslist.setVisibleRowCount(-1);
		panel1.add(strategieslist);
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Number of games:"));	 
        JSpinner gamesnumber = new JSpinner(new SpinnerNumberModel(5,1,5,1));
		panel.add(gamesnumber);
        panel.add(new JLabel("Max turns:"));		 
        JSpinner turnsnumber = new JSpinner(new SpinnerNumberModel(30,10,50,1));
		panel.add(turnsnumber);
        //panel.add(new JLabel("Field 1:"));			 panel.add(field1);
        panel1.add(panel);
        int result = JOptionPane.showConfirmDialog(null, panel1, "Configure TOURNAMENT",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        HashMap<Object, Object> res = new HashMap<>();
        if (result == JOptionPane.OK_OPTION) {
        	res.put("mapslist", mapslist.getSelectedValuesList());
        	res.put("strategieslist", strategieslist.getSelectedValuesList());
        	res.put("gamesnumber", gamesnumber.getValue());
        	res.put("turnsnumber", turnsnumber.getValue());
        	LOG.info(res);
        	return res;
        } else {
            return res;
        }
		
	}

}

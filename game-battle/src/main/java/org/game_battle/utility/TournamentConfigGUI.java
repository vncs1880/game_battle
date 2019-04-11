package org.game_battle.utility;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.Color;

public class TournamentConfigGUI extends JDialog {
	private final Logger LOG = LogManager.getLogger(TournamentConfigGUI.class);
	private final JPanel contentPanel = new JPanel();
	JList mapslist = new JList();
	JList strategieslist = new JList();
	JSpinner gamesnumber = new JSpinner();
	JSpinner turnsnumber = new JSpinner();

	public static void main(String[] args) {
		try {
			TournamentConfigGUI dialog = new TournamentConfigGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			//dialog.setModal(true);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TournamentConfigGUI() {
		setTitle("Tournament config");
		setModal(true);
		setBounds(100, 100, 235, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {200, 0};
		gbl_contentPanel.rowHeights = new int[] {50, 30, 20};
		gbl_contentPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 1.0, 0.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 0;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				
				mapslist.setVisibleRowCount(4);
				mapslist.setLayoutOrientation(JList.VERTICAL_WRAP);
				//list.setBorder(new EmptyBorder(0,0, 3, 3));
				mapslist.setModel(new AbstractListModel() {
					String[] values = new String[] { "file.map", "file2.map",
							/*
							 * "InvalidMapConnection.map", "InvalidMapFormat.map","InvalidMapFormat.txt",
							 */"NEW_FILE.map"/* ,"Newfile2.map" */,"newMap.map"/*
														 * "oogiuogodf", "dfgdsh", "jgfjdfjghg", "shfhs", "adhdhsgj",
														 * "dgjsgf", "sjsgfjfjhgkj", "fsfsfs"
														 */};
					public int getSize() {
						return values.length;
					}
					public Object getElementAt(int index) {
						return values[index];
					}
				});
				scrollPane.setViewportView(mapslist);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Select maps");
				scrollPane.setColumnHeaderView(lblNewLabel_2);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 1;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				
				strategieslist.setLayoutOrientation(JList.HORIZONTAL_WRAP);
				strategieslist.setModel(new AbstractListModel() {
					String[] values = new String[] {"Aggresive", "Benevolent", "Random", "Cheater"};
					public int getSize() {
						return values.length;
					}
					public Object getElementAt(int index) {
						return values[index];
					}
				});
				scrollPane.setViewportView(strategieslist);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Select strategies");
				scrollPane.setColumnHeaderView(lblNewLabel_3);
			}
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.anchor = GridBagConstraints.SOUTH;
			gbc_panel.fill = GridBagConstraints.HORIZONTAL;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 2;
			contentPanel.add(panel, gbc_panel);
			panel.setLayout(new GridLayout(1, 2, 5, 5));
			{
				JLabel lblNewLabel = new JLabel("Games");
				panel.add(lblNewLabel);
			}
			{
				
				gamesnumber.setModel(new SpinnerNumberModel(5, 1, 5, 1));
				panel.add(gamesnumber);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Turns");
				panel.add(lblNewLabel_1);
			}
			{
				
				turnsnumber.setModel(new SpinnerNumberModel(30, 10, 50, 1));
				panel.add(turnsnumber);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public HashMap getConfig() {
		HashMap<Object, Object> res = new HashMap<>();
		res.put("mapslist", mapslist.getSelectedValuesList());
		res.put("strategieslist", strategieslist.getSelectedValuesList());
		res.put("gamesnumber", gamesnumber.getValue());
		res.put("turnsnumber", turnsnumber.getValue());
		LOG.debug("Tournament config: "+res);
		return res;
	}

}

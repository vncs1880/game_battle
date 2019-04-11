package org.game_battle.utility;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.game_battle.GamePlay;
import org.game_battle.Tournament;
import org.game_battle.TournamentMatch;

import java.awt.GridLayout;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class Build3Starter extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ButtonGroup group;
	private JRadioButton rdbtnSinglePlayer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Build3Starter dialog = new Build3Starter();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Build3Starter() {
		setTitle("Select RISK mode");
		setBounds(100, 100, 235, 156);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		group = new ButtonGroup();
		{
			rdbtnSinglePlayer = new JRadioButton("Single Player");
			rdbtnSinglePlayer.setSelected(true);
			contentPanel.add(rdbtnSinglePlayer);
			group.add(rdbtnSinglePlayer);
		}
		{
			JRadioButton rdbtnNewRadioButton = new JRadioButton("Tournament");
			contentPanel.add(rdbtnNewRadioButton);
			group.add(rdbtnNewRadioButton);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Start selected mode");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (rdbtnSinglePlayer.isSelected()) {
							GamePlay game = new GamePlay();
							game.startMatch();
						} else {
							TournamentConfigGUI dialog = new TournamentConfigGUI();
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
							HashMap config = new HashMap<>();
							config =  dialog.getConfig();
							
							Tournament tournament = new Tournament(config);
							int gamesNumber = tournament.getGamesNumber();
							int mapsNumber = tournament.getMapsNumber();
							tournament.setTournamentPanel(new TournamentMatch[gamesNumber][mapsNumber]);
						}
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

}

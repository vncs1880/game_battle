package org.game_battle.utility;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import org.game_battle.TournamentMatch;

import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TournamentOutput extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TournamentOutput dialog = new TournamentOutput(1,1);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param colsnum 
	 * @param rowsnum 
	 */
	public TournamentOutput(int rowsnum, int colsnum) {
		setTitle("Tournament Report");
		//setModal(true);
		setBounds(100, 100, 450, 225);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(1, 0, 0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{	//table.setTableHeader();
				table = new JTable(rowsnum,colsnum);
				scrollPane.setViewportView(table);
				table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		        table.setFillsViewportHeight(true);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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

	public void setModel(TournamentMatch[][] tournamentPanel) {
		// TODO Auto-generated method stub
		
	}

}

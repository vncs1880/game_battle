package org.game_battle.gameplay;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class GameFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameFrame frame = new GameFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Graph") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("Asia");
						node_1.add(new DefaultMutableTreeNode("India"));
						node_1.add(new DefaultMutableTreeNode("Japan"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("America");
						node_1.add(new DefaultMutableTreeNode("Brazil"));
						node_1.add(new DefaultMutableTreeNode("Mexico"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Oceania");
						node_1.add(new DefaultMutableTreeNode("Australia"));
					add(node_1);
				}
			}
		));
		contentPane.add(tree, BorderLayout.WEST);
	}

}

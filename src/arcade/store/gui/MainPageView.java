package arcade.store.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.*;

import arcade.store.control.Control;

public class MainPageView {
	
	private static final int GAME_NAME_COLUMN = 0;

	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="55,18"
	private JPanel jContentPane = null;
	private JList genreList = null;
	private JLabel storeBrowseLabel = null;
	private JScrollPane jScrollPane = null;
	private JTable gameListTable = null;
	private Control controller;

	
	public MainPageView(Control control) {
		controller = control;
		JFrame frame = getJFrame();
		frame.setVisible(true);
	}
	
	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(863, 412));
			jFrame.setContentPane(getJContentPane());
		}
		return jFrame;
	}

	/**
	 * This method initializes jContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			BorderLayout borderLayout = new BorderLayout();
			borderLayout.setHgap(10);
			borderLayout.setVgap(10);
			storeBrowseLabel = new JLabel();
			storeBrowseLabel.setText("Main Game Store: Browse, Demo, and Purchase Your Games Here!");
			jContentPane = new JPanel();
			jContentPane.setLayout(borderLayout);
			jContentPane.add(getGenreList(), BorderLayout.WEST);
			jContentPane.add(storeBrowseLabel, BorderLayout.NORTH);
			jContentPane.add(getJScrollPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes genreList	
	 * 	
	 * @return javax.swing.JList	
	 */
	public JList getGenreList() {
		if (genreList == null) {
			genreList = new JList();
			genreList.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					controller.filter((String)genreList.getSelectedValue());
				}
			});
		}
		return genreList;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getGameListTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes gameListTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getGameListTable() {
		if (gameListTable == null) {
			String[][] data = {{"GalaxyInvaders", "25.99", "Shooter"}, {"ZombieLand", "25.99", "Action"}};
			String[] col = {"Game Name", "Price", "Genre"};
			TableModel dataModel = new DefaultTableModel(data, col); 
			gameListTable = new JTable(dataModel);
			gameListTable.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					int row = getGameListTable().getSelectedRow();
					String identifier = (String)getGameListTable().getValueAt(row, GAME_NAME_COLUMN);
					controller.openGamePage(identifier);
				}
			});
		}
		
		return gameListTable;
	}
	
	public void showMessageBox(String messageText) {
		
	}

}

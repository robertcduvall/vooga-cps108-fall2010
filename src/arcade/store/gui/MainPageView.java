package arcade.store.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import arcade.core.Tab;
import arcade.core.GameSelection.buttonActionListener;
import arcade.store.StoreModel;
import arcade.store.control.Control;

public class MainPageView extends Tab {

	private static final String ARCADE_STORE_GREETING = "Main Game Store: Browse, Demo, and Purchase Your Games Here!";
	private static final String CREDDIT_STRING = " Creddits";
	private static final int GAME_NAME_COLUMN = 0;

	private JFrame jFrame = null; // @jve:decl-index=0:visual-constraint="55,18"
	private JPanel jContentPane = null;
	private JList genreList = null;
	private JLabel storeBrowseLabel = null;
	private JScrollPane jScrollPane = null;
	private JTable gameListTable = null;
	private JPanel gameList = null;
	private Control controller; // @jve:decl-index=0:

	public MainPageView(Control control) {
		controller = control;
		JFrame frame = getJFrame();
		frame.setVisible(true);
	}

	public MainPageView() {
		setName("Browse Catalogue");
		controller = new Control();
		controller.setModel(new StoreModel(controller));
		controller.setView(this);
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
			jFrame.setContentPane((JPanel) getContent());
		}
		return jFrame;
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	public JComponent getContent() {
		if (jContentPane == null) {
			BorderLayout borderLayout = new BorderLayout();
			borderLayout.setHgap(10);
			borderLayout.setVgap(10);
			storeBrowseLabel = new JLabel();
			storeBrowseLabel.setText(ARCADE_STORE_GREETING);
			jContentPane = new JPanel();
			jContentPane.setLayout(borderLayout);
			jContentPane.add(getGenreList(), BorderLayout.WEST);
			jContentPane.add(getJScrollPane(), BorderLayout.CENTER);
			jContentPane.setVisible(true);
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
					controller.filter((String) genreList.getSelectedValue());
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
			jScrollPane.setViewportView(getGameList());
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
			gameListTable = new JTable();
			gameListTable.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					int row = getGameListTable().getSelectedRow();
					String identifier = (String) getGameListTable().getValueAt(
							row, GAME_NAME_COLUMN);
					controller.openGamePage(identifier);
				}
			});
		}

		return gameListTable;
	}

	public JPanel getGameList() {
		if (gameList == null) {
			gameList = new JPanel(new GridLayout(0, 4));
			gameList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
		return gameList;

	}

	public void addGameToList(String name, String price, String genre,
			ImageIcon image) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel title = new JLabel(name);
		panel.setName(name);

		JLabel icon = new JLabel(image);
		JLabel genreLabel = new JLabel(genre);
		JLabel priceLabel = new JLabel(price + CREDDIT_STRING);
		icon.setSize(150, 150);

		panel.add(title);
		panel.add(icon);
		panel.add(genreLabel);
		panel.add(priceLabel);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.validate();
		panel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String identifier = e.getComponent().getName();
				controller.openGamePage(identifier);
			}
		});
		getGameList().add(panel);
	}

	public void showMessageBox(String messageText) {

	}
}

package arcade.store.gui.tabs;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import arcade.core.Tab;
import arcade.core.mvc.IController;
import arcade.core.mvc.IViewer;
import arcade.store.StoreModel;
import arcade.store.control.MainController;
import arcade.store.control.MainPageController;

public class MainPageTab extends  JPanel implements Tab, IViewer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String ARCADE_STORE_GREETING = "Main Game Store: Browse, Demo, and Purchase Your Games Here!";

	private static final String NAME = "Main Page";
	private JPanel jContentPane = null;
	private JList genreList = null;
	private JLabel storeBrowseLabel = null;
	private JScrollPane jScrollPane = null;
	private JPanel gameListPanel = null;

	private MainPageController controller; // @jve:decl-index=0:

	public MainPageTab() {
		setName("Browse Catalogue");
		controller = new MainPageController();
		controller.addViewer(this);
		
	}
	
	@Override
	public String getName()
	{
		return NAME;
	}
	
	@Override
	public void setController(IController control) {
		controller = (MainPageController) control;
	}
	
	@Override
	public IController getController()
	{
		return controller;
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
	public JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getGameList());
		}
		return jScrollPane;
	}


	public JPanel getGameList() {
		if (gameListPanel == null) {
			gameListPanel = new JPanel(new GridLayout(0, 4));
			gameListPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
		return gameListPanel;

	}

	@Override
	public void refresh() {
		this.repaint();
	}

}

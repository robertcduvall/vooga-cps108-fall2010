package arcade.store.gui.tabs;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import arcade.core.mvc.IController;
import arcade.store.control.MainPageController;
import arcade.store.gui.StoreTab;

public class MainPageTab extends StoreTab {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String ARCADE_STORE_GREETING = "Main Game Store: Browse, Demo, and Purchase Your Games Here!";

	private static final String NAME = "Main Page";
	private static final String FILE_PATH = "arcade.store.resources.MainPageController";

	private MainPageController controller; // @jve:decl-index=0:
	private JList genreList;
	private JScrollPane jScrollPane;
	private JPanel gameListPanel;

	public MainPageTab() {
		setName("Browse Catalogue");

		initializeMainPageTab();

		add(getStoreBrowseLabel());
		add(getGenreList(), BorderLayout.WEST);
		add(getJScrollPane(), BorderLayout.CENTER);
		setVisible(true);

		refresh();
	}

	private void initializeMainPageTab() {
		controller = new MainPageController(FILE_PATH);
		controller.addViewer(this);

		genreList = new JList();
		genreList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				controller.processEvent("filter");
			}
		});

		gameListPanel = new JPanel(new GridLayout(0, 4));
		gameListPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(getGameList());

		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setHgap(10);
		borderLayout.setVgap(10);
		setLayout(borderLayout);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setController(IController control) {
		controller = (MainPageController) control;
	}

	@Override
	public IController getController() {
		return controller;
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	@Override
	public JComponent getContent() {
		return this;
	}

	/**
	 * This method initializes genreList
	 * 
	 * @return javax.swing.JList
	 */
	private JList getGenreList() {
		return genreList;
	}

	/**
	 * 
	 * @param list
	 */
	public void setGenreList(String[] list) {
		genreList.setListData(list);
	}

	/**
	 * 
	 * @return
	 */
	public Object getSelectedGenre() {
		return genreList.getSelectedValue();
	}

	/**
	 * 
	 * @return
	 */
	private JLabel getStoreBrowseLabel() {
		JLabel storeBrowseLabel = new JLabel();
		storeBrowseLabel.setText(ARCADE_STORE_GREETING);
		return storeBrowseLabel;
	}

	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	public JScrollPane getJScrollPane() {
		return jScrollPane;
	}

	/**
	 * 
	 * @return
	 */
	public JPanel getGameList() {
		return gameListPanel;
	}

	@Override
	public void refresh() {
		this.repaint();
	}

}

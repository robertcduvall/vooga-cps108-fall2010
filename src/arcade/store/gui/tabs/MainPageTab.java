package arcade.store.gui.tabs;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import arcade.core.mvc.IController;
import arcade.store.control.MainPageController;
import arcade.store.gui.StoreTab;

public class MainPageTab extends StoreTab {

	/**
	 * MainPageTab is a StoreTab and it is the initial view for the store. It
	 * has a list of games in a scroll pane that the user can navigate and a
	 * list of genres that the user can click to filter the games by genre.
	 * 
	 * @author: Drew Sternesky, Jimmy Mu, Marcus Molchany
	 * @date: 12-16-10
	 * @description:
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String NAME = "Main Page";
	private static final String FILE_PATH = "arcade.store.resources.MainPageController";
	private static final String MAIN_PAGE_TAB_FILE_PATH = "arcade.store.gui.resources.storeTabProperties";

	private MainPageController controller; // @jve:decl-index=0:
	private JList genreList;
	private JScrollPane jScrollPane;
	private JPanel gameListPanel;

	public MainPageTab() {
		setResourceBundleFilePath(MAIN_PAGE_TAB_FILE_PATH);
		setName(getString("mainPageTabString"));

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

		gameListPanel = new JPanel(new GridLayout(
				Integer.parseInt(getString("gameListPanelRowsString")),
				Integer.parseInt(getString("gameListPanelColsString"))));
		gameListPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(getGameList());

		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setHgap(Integer
				.parseInt(getString("borderLayoutHgapString")));
		borderLayout.setVgap(Integer
				.parseInt(getString("borderLayoutVgapString")));
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

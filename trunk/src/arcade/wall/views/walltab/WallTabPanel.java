package arcade.wall.views.walltab;

import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import arcade.lobby.model.ProfileSet;
import arcade.store.model.StoreModel;
import arcade.wall.controllers.WallTabController;

/**
 * WallTabPanel is the tab in arcade that deals with the display of the Wall GUI elements and data on screen. 
 * @author John, Cam, Bhawana
 *
 */
@SuppressWarnings("serial")
public class WallTabPanel extends JPanel {

	public static WallTabController myController;
	private JPanel myPanel;
	private FeedbackPanel myFeedbackPanel;
	private DisplayPanel myDisplayPanel;
	private MessagesPanel myMessagesPanel;
	public static final String[] myGameChoices = formGameList();
	public static ResourceBundle myResources = ResourceBundle.getBundle("arcade.wall.resources.walltab");

	public WallTabPanel(WallTabController controller) {
		myController = controller;
		myPanel = constructMainPanel();
	}

	/**
	 * Constructs the MainPanel - this panel holds all other panels in the WallTab.
	 */
	private JPanel constructMainPanel() {
		JPanel mainPanel = new JPanel();
		myFeedbackPanel = new FeedbackPanel();
		myDisplayPanel = new DisplayPanel();
		myMessagesPanel = new MessagesPanel();
		mainPanel.setLayout(new GridLayout(1,3,5,5));
		mainPanel.add(myFeedbackPanel);
		mainPanel.add(myDisplayPanel);
		mainPanel.add(myMessagesPanel);
		return mainPanel;
	}

	/**
	 * Constructs a WallBorder with the title of "string".
	 */
	public static Border constructWallBorder(String string) {
		return BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(string),
				BorderFactory.createEmptyBorder(5,5,5,5));
	}

	/**
	 * Forms the List of purchased Game names that will populate the Wall GameComboBox.
	 */
	private static String[] formGameList() {
		return StoreModel.getUserOwnedGamesAsStrings((ProfileSet.getCurrentProfile().getUserId()));
	}

	public void setMainPanelText() {
		myPanel.setBorder(constructWallBorder(ProfileSet.getCurrentProfile().getFirstName()+"'s Wall"));
	}

	public JPanel getPanel() {
		return this.myPanel;
	}
	
	public FeedbackPanel getFeedbackPanel() {
		return this.myFeedbackPanel;
	}
	
	public DisplayPanel getDisplayPanel() {
		return this.myDisplayPanel;
	}
	
	public MessagesPanel getMessagesPanel() {
		return this.myMessagesPanel;
	}
}
package arcade.wall.view;

import java.awt.GridLayout;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import arcade.wall.GameComboBoxListener;
import arcade.wall.controller.WallTabController;
import arcade.wall.model.Comment;
import arcade.wall.view.ratings.RadioPanel;

/**
 * WallView is the Wall entity that deals with the display of GUI elements and data on screen. 
 * @author John, Cam, Bhawana
 *
 */
@SuppressWarnings("serial")
public class WallTabView extends JPanel {
	
	private WallTabController myController;
	private JPanel myPanel;
	private RadioPanel myRatingPanel;
	private JPanel commentsPanel;
	//	private IconPanel myRatingPanel;
	public static final String[] myGameChoices = formGameList();

	public WallTabView() {
		myPanel = constructJPanel();
	}

	/**
	 * Constructs the WallTab JPanel.
	 */
	private JPanel constructJPanel() {
		JPanel returnPanel = new JPanel();
		JPanel entryPanel = new JPanel();
		JPanel displayPanel = new JPanel();
		
		myRatingPanel = new RadioPanel(5);
		ResourceBundle ratingLabelBundle = ResourceBundle.getBundle("arcade.wall.view.tierLabels");
		for (String s: ratingLabelBundle.keySet()) {
			myRatingPanel.addComment(Integer.parseInt(s), ratingLabelBundle.getString(s));
		}
		myRatingPanel.setVertical();
		
		commentsPanel = new JPanel();

		JButton reviewButton = new JButton("Review");
		JComboBox gameComboBox = new JComboBox(myGameChoices);
		gameComboBox.setSelectedIndex(0);
		JTextField commentEntryField = new JTextField(17);
		JLabel commentsLabel = new JLabel("Comments for " + myGameChoices[gameComboBox.getSelectedIndex()] + ":");

		gameComboBox.addActionListener(new GameComboBoxListener(myController, commentsLabel, gameComboBox, commentEntryField));
		//reviewButton.addActionListener(new ReviewButtonListener(myController, gameComboBox,
		//		commentEntryField, ProfileSet.currentProfile.getUserName(), myRatingPanel));

		entryPanel.add(gameComboBox);
		entryPanel.add(commentEntryField);
		entryPanel.add(myRatingPanel);
		entryPanel.add(reviewButton);
		displayPanel.add(commentsLabel);
		//displayPanel.add(myCommentBox);
		displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));

		returnPanel.setLayout(new GridLayout(2,2,5,5));
		returnPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		returnPanel.add(entryPanel);
		returnPanel.add(displayPanel);
		displayPanel.add(commentsPanel);
		return returnPanel;
	}
	
	public void updateCommentsPanel(List<Comment> gameComments){
		BoxLayout layout = new BoxLayout(commentsPanel,BoxLayout.Y_AXIS);
		JTextArea commentsArea = new JTextArea();
		String displayString = "";
		for(Comment comment: gameComments){  
			String starString = "";
			if (comment.getRating().equals("0"))
				starString = ":("; //TODO Is this needed?
			for (int i = 0; i < Integer.parseInt(comment.getRating()); i++) {
				starString += "*";
			}
			displayString += " >> ''" + comment.getCommentString() + "''  " + starString + 
			" " +  "---" + comment.getUserName() + "\n";           
		}
		commentsArea.setText(displayString);
		commentsPanel.add(commentsArea);
		commentsPanel.setLayout(layout);
	}

	private static String[] formGameList() {
		//TODO replace this code with code that reads the GameInfo table in the database and constructs
		//the array with that information
		String[] gameList = { "Grandius", "Zombieland", "Jumper", 
				"Doodlejump", "Galaxy Invaders", "Cyberion", 
				"Tron", "MarioClone", "TronLegacy" };
		return gameList;
	}

	public JPanel getPanel() {
		return this.myPanel;
	}

	public void setController(WallTabController controller) {
		myController = controller;
	}
}
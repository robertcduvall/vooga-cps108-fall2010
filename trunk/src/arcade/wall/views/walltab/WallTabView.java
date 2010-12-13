package arcade.wall.views.walltab;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
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

import arcade.wall.controllers.WallTabController;
import arcade.wall.models.Comment;
import arcade.wall.views.ratings.RadioPanel;

/**
 * WallTabView is the Wall entity that deals with the display of the Wall Arcade Tab GUI elements and data on screen. 
 * @author John, Cam, Bhawana
 *
 */
@SuppressWarnings("serial")
public class WallTabView extends JPanel {
	
	private WallTabController myController;
	
	//GUI Elements
	private JPanel myPanel;
	private RadioPanel myRatingPanel;
	private JTextArea myCommentsArea;
	private JButton myReviewButton;
	private JComboBox myGameComboBox;
	private JLabel myCommentsLabel;
	private JLabel myAverageRatingLabel;
	private JTextField myCommentEntryField;
	
	public static final String[] myGameChoices = formGameList();

	public WallTabView(WallTabController controller) {
		this.myController = controller;
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
		ResourceBundle ratingLabelBundle = ResourceBundle.getBundle("arcade.wall.views.tierLabels");
		for (String s: ratingLabelBundle.keySet()) {
			myRatingPanel.addComment(Integer.parseInt(s), ratingLabelBundle.getString(s));
		}
		myRatingPanel.setVertical();

		myReviewButton = new JButton("Review");
		myGameComboBox = new JComboBox(myGameChoices);
		myGameComboBox.setSelectedIndex(0);
		myCommentEntryField = new JTextField(17);
		String selectedGame = myGameChoices[myGameComboBox.getSelectedIndex()];
		myCommentsLabel = new JLabel("Comments for " + selectedGame + ":");
		myAverageRatingLabel = new JLabel("Average Rating for " + selectedGame + ": "+ 
				myController.getRating(selectedGame));
		myCommentsArea = new JTextArea();
		
		entryPanel.add(myGameComboBox);
		entryPanel.add(myCommentEntryField);
		entryPanel.add(myRatingPanel);
		entryPanel.add(myReviewButton);

		displayPanel.add(myAverageRatingLabel);
		displayPanel.add(myCommentsLabel);
		displayPanel.add(myCommentsArea);
		displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));

		returnPanel.setLayout(new GridLayout(2,2,5,5));
		returnPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		returnPanel.add(entryPanel);
		returnPanel.add(displayPanel);
		return returnPanel;
	}
	
	/**
	 * Refreshes the CommentsArea to display the comments for the selected game.
	 */
	public void refreshCommentsArea(List<Comment> gameComments){
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
		myCommentsArea.setText(displayString);
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

	public String getSelectedGame() {
		return myGameChoices[myGameComboBox.getSelectedIndex()];
	}

	public void setCommentsLabel(String string) {
		myCommentsLabel.setText(string);
	}

	public void setAverageRatingLabel(String string) {
		myAverageRatingLabel.setText(string);
	}

	public void setEntryText(String string) {
		myCommentEntryField.setText(string);
	}

	public void addGameComboBoxListener(
			ActionListener gameComboBoxListener) {
		myGameComboBox.addActionListener(gameComboBoxListener);
	}

	public String getEntryText() {
		return myCommentEntryField.getText();
	}

	public String getSelectedRating() {
		return myRatingPanel.getSelectedValue();
	}

	public void addReviewButtonListener(
			ActionListener reviewButtonListener) {
		myReviewButton.addActionListener(reviewButtonListener);
	}
}
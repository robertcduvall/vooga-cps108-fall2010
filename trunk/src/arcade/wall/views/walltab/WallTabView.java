package arcade.wall.views.walltab;

import java.awt.BorderLayout;
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

import arcade.lobby.model.ProfileSet;
import arcade.wall.controllers.WallTabController;
import arcade.wall.models.data.comment.Comment;
import arcade.wall.views.ratings.IconPanel;

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
	private IconPanel myRatingPanel;
	private JTextArea myCommentsArea;
	private JButton myReviewButton;
	private JComboBox myGameComboBox;
	private JLabel mySelectGameLabel;
	private JLabel myEnterCommentLabel;
	private JLabel myCommentsDisplayLabel;
	private JLabel myAverageRatingDisplayLabel;
	private JTextField myCommentEntryField;
	private JLabel myTopRatedGamesLabel;
	
	public static final String[] myGameChoices = formGameList();

	public WallTabView(WallTabController controller) {
		this.myController = controller;
		myPanel = constructJPanel();
	}

	/**
	 * Constructs the WallTab JPanel.
	 */
	private JPanel constructJPanel() {
		//Construct GUI elements
		myReviewButton = new JButton("Review");
		myGameComboBox = new JComboBox(myGameChoices);
		myGameComboBox.setSelectedIndex(0);
		String selectedGame = myGameChoices[myGameComboBox.getSelectedIndex()];
		myCommentEntryField = new JTextField();
		mySelectGameLabel = new JLabel("Select game:");
		myEnterCommentLabel = new JLabel("Enter a comment here:");
		myAverageRatingDisplayLabel = new JLabel();
		setAverageRatingLabel(selectedGame);
		myCommentsDisplayLabel = new JLabel("Comments for " + selectedGame + ":");
		myCommentsArea = new JTextArea();
		myCommentsArea.setEditable(false);
		myTopRatedGamesLabel = new JLabel();
		updateTopRatedGamesLabel();
		
		//Construct Panels
		constructRatingPanel();
		JPanel displayPanel = constructDisplayPanel();
		JPanel reviewPanel = constructReviewPanel();

		JPanel returnPanel = new JPanel();
		returnPanel.setLayout(new GridLayout(1,2,5,5));
		returnPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		returnPanel.add(reviewPanel);
		returnPanel.add(displayPanel);
		return returnPanel;
	}

	private void constructRatingPanel() {
		myRatingPanel = new IconPanel(5);
		ResourceBundle ratingLabelBundle = ResourceBundle.getBundle("arcade.wall.views.tierLabels");
		for (String s: ratingLabelBundle.keySet()) {
			myRatingPanel.addComment(Integer.parseInt(s), ratingLabelBundle.getString(s));
		}
		myRatingPanel.setVertical();
	}
	
	private JPanel constructDisplayPanel() {
		JPanel displayPanel = new JPanel();
		displayPanel.add(myCommentsDisplayLabel);
		displayPanel.add(myCommentsArea);
		displayPanel.add(myTopRatedGamesLabel);
		displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
		return displayPanel;
	}

	private JPanel constructReviewPanel() {
		JPanel reviewPanel = new JPanel();
		reviewPanel.setLayout(new BoxLayout(reviewPanel, BoxLayout.Y_AXIS));
		reviewPanel.add(mySelectGameLabel);
		reviewPanel.add(myGameComboBox);
		reviewPanel.add(myEnterCommentLabel);
		reviewPanel.add(myCommentEntryField);
		reviewPanel.add(myAverageRatingDisplayLabel);
		reviewPanel.add(myRatingPanel);
		reviewPanel.add(myReviewButton);
		return reviewPanel;
	}

	public void updateTopRatedGamesLabel() {
		List<String> gameRankList = myController.getGameRankList();
		myTopRatedGamesLabel.setText("<html>" + 
				"<font color=red> Top Rated Game: " + gameRankList.get(0) + "</font> <br/>" +
				"2nd Place Game: " + gameRankList.get(1) + "<br/>" +
				"3rd Place Game: " + gameRankList.get(2)
				+ "</html>");
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
			displayString += " >> ''" + comment.getString() + "''  " + starString + 
			" " +  "---" + ProfileSet.getProfile(Integer.parseInt(comment.getUserId())).getFirstName() + "\n";           
		}
		myCommentsArea.setText(displayString);
	}

	public JPanel getPanel() {
		return this.myPanel;
	}

	public String getSelectedGame() {
		return myGameChoices[myGameComboBox.getSelectedIndex()];
	}

	public void setCommentsLabel(String string) {
		myCommentsDisplayLabel.setText(string);
	}

	public void setEntryText(String string) {
		myCommentEntryField.setText(string);
	}
	
	public void setAverageRatingLabel(String selectedGame) {
		myAverageRatingDisplayLabel.setText("Average Rating for " + selectedGame + ": "+ 
				myController.getRating(selectedGame));
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
	
	private static String[] formGameList() {
		//TODO replace this code with code that reads the GameInfo table in the database and constructs
		//the array with that information
		String[] gameList = { "Grandius", "Zombieland", "Jumper", 
				"Doodlejump", "Galaxy Invaders", "Cyberion", 
				"Tron", "MarioClone", "TronLegacy" };
		return gameList;
	}

}
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

import arcade.lobby.model.ProfileSet;
import arcade.wall.controllers.WallTabController;
import arcade.wall.models.data.comment.Comment;
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
	private JButton mySubmitButton,
					mySendMessageButton;
	private JComboBox myGameComboBox;
	private JLabel mySelectGameLabel,	
				   myEnterCommentLabel,
				   myGameHeaderLabel,
				   myTopRatedGamesLabel,
				   myEnterReceiverLabel,
				   myEnterMessageLabel,
				   myReceivedMessagesLabel;
	private JTextArea myCommentsArea,
					  myEnterMessageArea,
					  myReceivedMessagesArea;
	private JTextField myCommentEntryField,
					   myEnterReceiverField;
	public static final String[] myGameChoices = formGameList();

	public WallTabView(WallTabController controller) {
		this.myController = controller;
		myPanel = constructJPanel();
	}

	/**
	 * Constructs the WallTab JPanel.
	 */
	private JPanel constructJPanel() {
		constructGUIElements();
		return constructReturnPanel();
	}

	private void constructGUIElements() {
		constructJLabels();
		constructJComboBoxes();
		constructJTextFields();
		constructJButtons();
		constructJTextAreas();
		setGameHeaderLabel(myGameChoices[myGameComboBox.getSelectedIndex()]);
	}

	private JPanel constructReturnPanel() {
		JPanel returnPanel = new JPanel();
		
		constructRatingPanel();
		JPanel displayPanel = constructDisplayPanel();
		JPanel reviewPanel = constructReviewPanel();
		JPanel messagesPanel = constructMessagesPanel();
		
		returnPanel.setLayout(new GridLayout(1,3,5,5));
		returnPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		returnPanel.add(reviewPanel);
		returnPanel.add(displayPanel);
		returnPanel.add(messagesPanel);
		return returnPanel;
	}

	private void constructJTextFields() {
		myCommentEntryField = new JTextField();
	}

	private void constructJLabels() {
		mySelectGameLabel = new JLabel("Select game:");
		myGameHeaderLabel = new JLabel();
		myTopRatedGamesLabel = new JLabel();
		updateTopRatedGamesLabel();
		myEnterReceiverLabel = new JLabel("Enter Receiver:");
		myEnterMessageLabel = new JLabel("Enter message:");
		myReceivedMessagesLabel = new JLabel("Your received messages:");
		myEnterCommentLabel = new JLabel("Enter a comment here:");
	}
	
	private void constructJTextAreas() {
		myCommentsArea = new JTextArea();
		myCommentsArea.setEditable(false);
		myEnterReceiverField = new JTextField();
		myEnterMessageArea = new JTextArea();
		myReceivedMessagesArea = new JTextArea();
	}
	
	private void constructJButtons() {
		mySubmitButton = new JButton("Submit");
		mySendMessageButton = new JButton("Send");
	}
	
	private void constructJComboBoxes() {
		myGameComboBox = new JComboBox(myGameChoices);
		myGameComboBox.setSelectedIndex(0);
	}
	
	private JPanel constructMessagesPanel() {
		JPanel messagesPanel = new JPanel();
		JPanel sendAMessagePanel = new JPanel();
		JPanel receivedMessagesDisplayPanel = new JPanel();
		sendAMessagePanel.setLayout(new BoxLayout(sendAMessagePanel, BoxLayout.Y_AXIS));
		sendAMessagePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Send"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		sendAMessagePanel.add(myEnterReceiverLabel);
		sendAMessagePanel.add(myEnterReceiverField);
		sendAMessagePanel.add(myEnterMessageLabel);
		sendAMessagePanel.add(myEnterMessageArea);
		sendAMessagePanel.add(mySendMessageButton);
		receivedMessagesDisplayPanel.setLayout(new BoxLayout(receivedMessagesDisplayPanel, BoxLayout.Y_AXIS));
		receivedMessagesDisplayPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Receive"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		receivedMessagesDisplayPanel.add(myReceivedMessagesLabel);
		receivedMessagesDisplayPanel.add(myReceivedMessagesArea);
		messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.Y_AXIS));
		messagesPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Messages"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		messagesPanel.add(sendAMessagePanel);
		messagesPanel.add(receivedMessagesDisplayPanel);
		return messagesPanel;
	}

	private void constructRatingPanel() {
		myRatingPanel = new RadioPanel(5);
		ResourceBundle ratingLabelBundle = ResourceBundle.getBundle("arcade.wall.views.tierLabels");
		for (String s: ratingLabelBundle.keySet()) {
			myRatingPanel.addComment(Integer.parseInt(s), ratingLabelBundle.getString(s));
		}
		myRatingPanel.setVertical();
		myRatingPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Select a Rating"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
	}
	
	private JPanel constructDisplayPanel() {
		JPanel displayPanel = new JPanel();
		displayPanel.add(myGameHeaderLabel);
		myCommentsArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Comments"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		displayPanel.add(myCommentsArea);
		displayPanel.add(myTopRatedGamesLabel);
		displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
		displayPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Game Feedback"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		return displayPanel;
	}

	private JPanel constructReviewPanel() {
		JPanel reviewPanel = new JPanel();
		reviewPanel.setLayout(new BoxLayout(reviewPanel, BoxLayout.Y_AXIS));
		reviewPanel.add(mySelectGameLabel);
		reviewPanel.add(myGameComboBox);
		reviewPanel.add(myEnterCommentLabel);
		reviewPanel.add(myCommentEntryField);
		reviewPanel.add(myRatingPanel);
		reviewPanel.add(mySubmitButton);
		reviewPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Submit a Comment"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		return reviewPanel;
	}
	
	/**
	 * Adds the ReviewButtonListener to the WallTabView.
	 */
	public void addReviewButtonListener(
			ActionListener reviewButtonListener) {
		mySubmitButton.addActionListener(reviewButtonListener);
	}
	
	/**
	 * Adds the GameComboBoxListener to the WallTabView.
	 */
	public void addGameComboBoxListener(
			ActionListener gameComboBoxListener) {
		myGameComboBox.addActionListener(gameComboBoxListener);
	}
	
	/**
	 * Adds the SendMessageButtonListener to the WallTabView.
	 */
	public void addSendMessageButtonListener(
			ActionListener sendMessageButtonListener) {
		mySendMessageButton.addActionListener(sendMessageButtonListener);
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

	public void setEntryText(String string) {
		myCommentEntryField.setText(string);
	}
	
	public void setGameHeaderLabel(String selectedGame) {
		this.myGameHeaderLabel.setText("<html>" + 
				"<font color=blue>" + selectedGame + "</font> || Average Rating: " +
				+ myController.getRating(selectedGame) + "</html>");
	}

	public String getEntryText() {
		return myCommentEntryField.getText();
	}

	public String getSelectedRating() {
		return myRatingPanel.getSelectedValue();
	}
	
	private static String[] formGameList() {
		//TODO replace this code with code that reads the GameInfo table in the database and constructs
		//the array with that information
		String[] gameList = { "Grandius", "Zombieland", "Jumper", 
				"Doodlejump", "Galaxy Invaders", "Cyberion", 
				"Tron", "MarioClone", "TronLegacy" };
		return gameList;
	}

	public String getReceiver() {
		return myEnterReceiverField.getText();
	}

	public String getMessageContent() {
		return myEnterMessageArea.getText();
	}

	public void setReceiverText(String string) {
		myEnterReceiverField.setText(string);
	}

	public void setMessageContentText(String string) {
		myEnterMessageArea.setText(string);
	}
}
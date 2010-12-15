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
	
	private ResourceBundle myResources = ResourceBundle.getBundle("arcade.wall.resources.walltab");


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
		mySelectGameLabel = new JLabel(myResources.getString("selectGameLabel"));
		myGameHeaderLabel = new JLabel();
		myTopRatedGamesLabel = new JLabel();
		updateTopRatedGamesLabel();
		myEnterReceiverLabel = new JLabel("enterReceiverLabel");
		myEnterMessageLabel = new JLabel("enterMessageLabel");
		myReceivedMessagesLabel = new JLabel("receivedMessagesLabel");
		myEnterCommentLabel = new JLabel("enterCommentLabel");
	}
	
	private void constructJTextAreas() {
		myCommentsArea = new JTextArea();
		myCommentsArea.setEditable(false);
		myEnterReceiverField = new JTextField();
		myEnterMessageArea = new JTextArea();
		myReceivedMessagesArea = new JTextArea();
	}
	
	private void constructJButtons() {
		mySubmitButton = new JButton("submitButton");
		mySendMessageButton = new JButton("sendMessageButton");
	}
	
	private void constructJComboBoxes() {
		myGameComboBox = new JComboBox(myGameChoices);
		myGameComboBox.setSelectedIndex(0);
	}
	
	private JPanel constructMessagesPanel() {
		JPanel messagesPanel = new JPanel();
		JPanel sendMessagesPanel = new JPanel();
		JPanel receivedMessagesPanel = new JPanel();
		sendMessagesPanel.setLayout(new BoxLayout(sendMessagesPanel, BoxLayout.Y_AXIS));
		sendMessagesPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("sendMessagesPanelBorder"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		sendMessagesPanel.add(myEnterReceiverLabel);
		sendMessagesPanel.add(myEnterReceiverField);
		sendMessagesPanel.add(myEnterMessageLabel);
		sendMessagesPanel.add(myEnterMessageArea);
		sendMessagesPanel.add(mySendMessageButton);
		receivedMessagesPanel.setLayout(new BoxLayout(receivedMessagesPanel, BoxLayout.Y_AXIS));
		receivedMessagesPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("receivedMessagesPanelBorder"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		receivedMessagesPanel.add(myReceivedMessagesLabel);
		receivedMessagesPanel.add(myReceivedMessagesArea);
		messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.Y_AXIS));
		messagesPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("messagesPanelBorder"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		messagesPanel.add(sendMessagesPanel);
		messagesPanel.add(receivedMessagesPanel);
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
                BorderFactory.createTitledBorder("ratingPanelBorder"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
	}
	
	private JPanel constructDisplayPanel() {
		JPanel displayPanel = new JPanel();
		displayPanel.add(myGameHeaderLabel);
		myCommentsArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("commentsAreaBorder"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		displayPanel.add(myCommentsArea);
		displayPanel.add(myTopRatedGamesLabel);
		displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
		displayPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("displayPanelBorder"),
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
                BorderFactory.createTitledBorder("reviewPanelBorder"),
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
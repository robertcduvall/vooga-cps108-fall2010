package arcade.wall.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import arcade.wall.GameComboBoxListener;
import arcade.wall.ReviewButtonListener;
import arcade.wall.controller.WallController;
import arcade.wall.view.ratings.IconPanel;
import arcade.wall.view.ratings.RadioPanel;

/**
 * WallView is the Wall entity that deals with the display of GUI elements and data on screen. 
 * @author John, Cam, Bhawana
 *
 */
public class WallView {

//	RatingPanel myRatingPanel;
//	RadioPanel myRatingPanel;

	private WallController myController;
	private String myGamerName;
	private JTextArea myCommentBox;
	private JFrame myFrame;
//	private RatingPanel myRatingPanel;
//	private RadioPanel myRatingPanel;
	private IconPanel myRatingPanel;

	public static final String[] myGameChoices = { "Grandius", "Zombieland", "Jumper", 
		"Doodlejump", "Galaxy Invaders", "Cyberion", 
		"Tron", "MarioClone", "TronLegacy" };

	public WallView(WallController controller, String userName) {
		myController = controller;
		myGamerName = userName;
		constructJFrame(userName);
	}
	
	/**
	 * Constructs the Wall JFrame and titles it using the given UserName.
	 * @param userName
	 */
	private void constructJFrame(String userName) {
		myFrame = new JFrame(userName + "'s Wall");
		myFrame.setResizable(false);
		myFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {System.exit(0);}
		});
		myCommentBox = constructCommentsArea();
		JPanel panel = constructJPanel();
		myFrame.getContentPane().add(panel, BorderLayout.CENTER);
		myFrame.getContentPane().add(myCommentBox, BorderLayout.SOUTH);
	}

	/**
	 * Constructs the JPanel to be placed in the JFrame.
	 */
	private JPanel constructJPanel() {
		JPanel returnPanel = new JPanel();
		JPanel entryPanel = new JPanel();
		JPanel displayPanel = new JPanel();
//		myRatingPanel = new RatingPanel(5);
//		myRatingPanel = new RadioPanel(5);
		myRatingPanel = new IconPanel(5);

		myRatingPanel.addComment(1, "CS6 tier");
		myRatingPanel.addComment(2, "Meh tier");
		myRatingPanel.addComment(3, "1337 tier");
		myRatingPanel.addComment(4, "God tier");
		myRatingPanel.addComment(5, "RCD tier");
		myRatingPanel.setVertical();

		JButton reviewButton = new JButton("Review");
		JComboBox gameComboBox = new JComboBox(myGameChoices);
		gameComboBox.setSelectedIndex(0);
		JTextField commentEntryField = new JTextField(17);
		JLabel commentsLabel = new JLabel("Comments for " + myGameChoices[gameComboBox.getSelectedIndex()] + ":");


		gameComboBox.addActionListener(new GameComboBoxListener(myController, commentsLabel, gameComboBox, commentEntryField));
		reviewButton.addActionListener(new ReviewButtonListener(myController, gameComboBox,
				commentEntryField, myGamerName, myRatingPanel));

		entryPanel.add(gameComboBox);
		entryPanel.add(commentEntryField);
		entryPanel.add(myRatingPanel);
		entryPanel.add(reviewButton);
		displayPanel.add(commentsLabel);

		returnPanel.setLayout(new GridLayout(2,2,5,5));
		returnPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		returnPanel.add(entryPanel);
		returnPanel.add(displayPanel);
		return returnPanel;
	}

	/**
	 * Constructs the Comment Display Area.
	 */
	private JTextArea constructCommentsArea(){
		JTextArea comments = new JTextArea(10,5);
		comments.setLineWrap(true);
		comments.setEditable(false);
		return comments;
		//JScrollPane scrollPane = new JScrollPane(comments);	
		//return scrollPane;
	}

	/**
	 * Prepares the JFrame for display and displays it.
	 */
	public void setFrameVisible() {
		myFrame.pack();
		myFrame.setVisible(true);
	}
	
	public JFrame getFrame() {
		return myFrame;
	}
	
	public JTextArea getCommentsArea() {
		return this.myCommentBox;
	}
}
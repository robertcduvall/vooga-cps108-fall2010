package arcade.wall.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import arcade.wall.GameComboBoxListener;
import arcade.wall.ReviewButtonListener;
import arcade.wall.controller.WallController;

public class WallView {
	WallController myController;
	String myGamerName;
	JTextArea myCommentBox;
	JFrame myFrame;
	RatingPanel myRatingPanel;
	
	public static final String[] choices = { "Grandius", "Zombieland", "Jumper", 
			"Doodlejump", "Galaxy Invaders", "Cyberion", 
			"Tron", "MarioClone", "TronLegacy" };

	public WallView(String gamerName, WallController controller) {
		myController = controller;
		myGamerName = gamerName;
		createJFrame(gamerName);
	}
	
	private void createJFrame(String gamerName) {
		myFrame = new JFrame(gamerName + "'s Wall");
		myFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {System.exit(0);}
		});
		myCommentBox = createCommentsArea();
		JPanel panel = createJPanel();
		myFrame.getContentPane().add(panel, BorderLayout.CENTER);
		myFrame.getContentPane().add(myCommentBox, BorderLayout.SOUTH);
//		myFrame.pack();
//		myFrame.setVisible(true);
	}

	private JPanel createJPanel() {
		JPanel returnPanel = new JPanel();
		
		JPanel entryPanel = new JPanel();
		JPanel displayPanel = new JPanel();
		myRatingPanel = new RatingPanel(5);
		
		JButton reviewButton = new JButton("Review");
		JComboBox gameChoices = new JComboBox(choices);
		gameChoices.setSelectedIndex(0);
		JTextField commentEntryField = new JTextField(17);
		JLabel commentsLabel = new JLabel("Comments for " + choices[gameChoices.getSelectedIndex()] + ":");
		
		//Add action listeners to ComboBox and Button
		gameChoices.addActionListener(new GameComboBoxListener(myController, commentsLabel, gameChoices, commentEntryField));
		reviewButton.addActionListener(new ReviewButtonListener(myController, gameChoices,
									   commentEntryField, myGamerName, myRatingPanel));
		
		entryPanel.add(gameChoices);
		entryPanel.add(commentEntryField);
		entryPanel.add(myRatingPanel);
		entryPanel.add(reviewButton);
		//entryPanel.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		displayPanel.add(commentsLabel);
		
		returnPanel.setLayout(new GridLayout(2,2,5,5));
		//returnPanel.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		returnPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		returnPanel.add(entryPanel);
		returnPanel.add(displayPanel);
		//returnPanel.add(ratingPanel);
		return returnPanel;
	}

	private JTextArea createCommentsArea(){
		JTextArea comments = new JTextArea(5,5);
		comments.setWrapStyleWord(true);
		comments.setEditable(false);
		return comments;
		//JScrollPane scrollPane = new JScrollPane(comments);	
		//return scrollPane;
	}
	
	public JTextArea getCommentsArea() {
		return this.myCommentBox;
	}

	public void setVisible() {
		myFrame.pack();
		myFrame.setVisible(true);
		
	}
}
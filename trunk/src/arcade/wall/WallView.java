package arcade.wall;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import arcade.wall.WallController;
import arcade.wall.gui.RatingPanel;

public class WallView {
	WallController myController;
	String myGamerName;
	JComponent myCommentBox;
	
	static final String[] choices = { "Grandius", "Zombieland", "Jumper", 
			"Doodlejump", "Galaxy Invaders", "Cyberion", 
			"Tron", "MarioClone" };

	public WallView(String gamerName, WallController controller) {
		myController = controller;
		myGamerName = gamerName;
		createJFrame(gamerName);
	}
	
	private void createJFrame(String gamerName) {
		JFrame frame = new JFrame(gamerName + "'s Wall");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {System.exit(0);}
		});
		myCommentBox = createCommentsArea();
		JPanel panel = createJPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.getContentPane().add(myCommentBox, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}

	private JPanel createJPanel() {
		JPanel returnPanel = new JPanel();
		
		JPanel entryPanel = new JPanel();
		JPanel displayPanel = new JPanel();
		RatingPanel ratingPanel = new RatingPanel(5);
		
		JButton reviewButton = new JButton("Review");
		JComboBox gameChoices = new JComboBox(choices);
		gameChoices.setSelectedIndex(0);
		JTextField commentEntryField = new JTextField(17);
		JLabel commentsLabel = new JLabel("Comments for " + choices[gameChoices.getSelectedIndex()] + ":");
		
		//Add action listeners to ComboBox and Button
		gameChoices.addActionListener(new GameComboBoxListener(commentsLabel, gameChoices));
		reviewButton.addActionListener(new ReviewButtonListener(myController, gameChoices, myCommentBox,
									   commentEntryField, myGamerName));
		
		entryPanel.add(gameChoices);
		entryPanel.add(commentEntryField);
		entryPanel.add(reviewButton);
		//entryPanel.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		displayPanel.add(commentsLabel);
		
		returnPanel.setLayout(new GridLayout(2,2,5,5));
		//returnPanel.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		returnPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		returnPanel.add(entryPanel);
		returnPanel.add(displayPanel);
		returnPanel.add(ratingPanel);
		return returnPanel;
	}

	private JComponent createCommentsArea(){
		JTextArea comments = new JTextArea(5,5);
		comments.setEditable(false);
		return comments;
		//JScrollPane scrollPane = new JScrollPane(comments);	
		//return scrollPane;
	}
}
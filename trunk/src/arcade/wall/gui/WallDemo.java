package arcade.wall.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import arcade.wall.ReviewAction;

public class WallDemo extends JPanel {
	static JFrame frame;

	JPanel entryPanel;
	JPanel displayPanel;

	JButton reviewButton;
	static JLabel commentsLabel;

	//TODO - made a bunch of things public / static so as to use them in ReviewAction class
	//Best way to handle this???
	static JTextField commentEntryField;
	public static JComboBox gameChoices;
	
	public static final String myName = "gamer13";
	public static final String[] choices = { "Grandius", "Zombieland", "Jumper", 
			"Doodlejump", "Galaxy Invaders", "Cyberion", 
			"Tron", "MarioClone" };

	public WallDemo() {
		entryPanel = new JPanel();
		displayPanel = new JPanel();

		reviewButton = new JButton("Review");
		reviewButton.addActionListener(new ReviewAction());
//		reviewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				commentsLabel.setText("<html>" + 
//									  commentsLabel.getText() +  
//									  "<br/>" + 
//									  commentEntryField.getText() + "  ---" + myName +
//									  "</html>");
//			}
//		});
		commentEntryField = new JTextField(17);
		gameChoices = new JComboBox();
		gameChoices = new JComboBox(choices);
		gameChoices.setSelectedIndex(0);
		commentsLabel = new JLabel();
//		commentsLabel = new JLabel("Comments for " + choices[gameChoices.getSelectedIndex()] + ":");
		gameChoices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			    if ("comboBoxChanged".equals(event.getActionCommand())) {
			        commentsLabel.setText("Comments for " + choices[gameChoices.getSelectedIndex()] + ":");
			    }
			}
		});
		
		entryPanel.add(gameChoices);
		entryPanel.add(commentEntryField);
		entryPanel.add(reviewButton);
		//entryPanel.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		displayPanel.add(commentsLabel);

		this.setLayout(new GridLayout(2,2,5,5));
		//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		this.add(entryPanel);
		this.add(displayPanel);
	}
	

	public static JLabel getCommentsLabel() {
		return commentsLabel;
	}

	public static JTextField getCommentEntryField() {
		return commentEntryField;
	}


	public static void main(String s[]) {
		frame = new JFrame("WallDemo");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {System.exit(0);}
		});

		frame.getContentPane().add(new WallDemo(), BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
}

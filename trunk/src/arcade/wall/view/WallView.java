package arcade.wall.view;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import arcade.wall.GameComboBoxListener;
import arcade.wall.controller.WallController;
import arcade.wall.model.Comment;
import arcade.wall.view.ratings.RadioPanel;

/**
 * WallView is the Wall entity that deals with the display of GUI elements and data on screen. 
 * @author John, Cam, Bhawana
 *
 */
@SuppressWarnings("serial")
public class WallView extends JPanel {

	private WallController myController;
	private JPanel myPanel;

	private RadioPanel myRatingPanel;
	private JPanel commentsPanel;
	//	private IconPanel myRatingPanel;
	public static final String[] myGameChoices = formGameList();

	public WallView() {
		myPanel = constructJPanel();
	}

	/**
	 * Constructs the JPanel to be placed in the JFrame.
	 */
	private JPanel constructJPanel() {
		JPanel returnPanel = new JPanel();
		JPanel entryPanel = new JPanel();
		JPanel displayPanel = new JPanel();
		myRatingPanel = new RadioPanel(5);
		//		myRatingPanel = new IconPanel(5);

		//TODO clean up this code (loop)
		myRatingPanel.addComment(1, "CS6 tier");
		myRatingPanel.addComment(2, "Meh tier");
		myRatingPanel.addComment(3, "1337 tier");
		myRatingPanel.addComment(4, "God tier");
		myRatingPanel.addComment(5, "RCD tier");
		myRatingPanel.setVertical();
		//myCommentBox = constructCommentsArea();
		
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
		for(Comment comment: gameComments){             
			String display = comment.getCommentString() + " -- "+ comment.getUserName();
			JLabel gap = new JLabel(" ");
			//JLabel label = new JLabel(display);
			JTextArea label = new JTextArea(display);
			commentsPanel.add(gap);
			commentsPanel.add(label);             
		}
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

	public void setController(WallController controller) {
		myController = controller;
	}
}
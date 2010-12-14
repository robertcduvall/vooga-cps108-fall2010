package arcade.wall.widget;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import arcade.wall.views.ratings.RadioPanel;

/**
 * Utility that allows users to comment on and rate items (games) while they are running 
 * (playing) them in the Arcade.
 * @author John, Bhawana, Cam
 *
 */
@SuppressWarnings("serial")
public class WallWidgetView extends JPanel{
	private WallWidgetController myController;
	
	private JPanel myPanel;
	private JComboBox myGameComboBox;	
	private JTextArea myCommentEntryField;
	private RadioPanel myRadioPanel;
	private JButton myReviewButton;
	public static final String[] myGameChoices = formGameList();

	public WallWidgetView(WallWidgetController controller) {
		myController = controller;
		myPanel = constructJPanel();
	}

	/**
	 * Constructs the JPanel representing WallWidget.
	 */
	private JPanel constructJPanel() {
		JPanel reviewPanel = new JPanel();

		myGameComboBox = new JComboBox(myGameChoices);
		myGameComboBox.setSelectedIndex(0);
		reviewPanel.add(myGameComboBox);

		myCommentEntryField = new JTextArea(4,10);
		myCommentEntryField.setLineWrap(true);
		reviewPanel.add(myCommentEntryField);
		
		JPanel ratingPanel = new JPanel();
		myRadioPanel = new RadioPanel(5);
		myRadioPanel.setHorizontal();	
		ratingPanel.add(new JLabel("Poor"));
		ratingPanel.add(myRadioPanel);
		ratingPanel.add(new JLabel("Excellent"));
		ratingPanel.setLayout(new FlowLayout());
		reviewPanel.add(ratingPanel);

		myReviewButton = new JButton("Submit");
		reviewPanel.add(myReviewButton);
		
		reviewPanel.setLayout(new BoxLayout(reviewPanel, BoxLayout.Y_AXIS));
		reviewPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		return reviewPanel;
	}

	private static String[] formGameList() {
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

	public void addGameComboBoxListener(ActionListener gameComboBoxListener) {
		myGameComboBox.addActionListener(gameComboBoxListener);
	}

	public String getEntryText() {
		return myCommentEntryField.getText();
	}

	public String getSelectedRating() {
		return myRadioPanel.getSelectedValue();
	}

	public void addReviewButtonListener(ActionListener reviewButtonListener) {
		myReviewButton.addActionListener(reviewButtonListener);
	}
}
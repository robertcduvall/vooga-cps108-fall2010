package arcade.wall.views.walltab;

import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import arcade.wall.views.ratings.RadioPanel;

@SuppressWarnings("serial")
public class ReviewPanel extends JPanel {
	
	private RadioPanel myRatingPanel;
	
	private JButton mySubmitButton;
	public static JComboBox gameComboBox;
	private JLabel mySelectGameLabel, myEnterCommentLabel;
	private JTextField myCommentEntryField;
	
	public ReviewPanel() {
		gameComboBox = new JComboBox(WallTabView.myGameChoices);
		gameComboBox.setSelectedIndex(0);
		mySelectGameLabel = new JLabel(WallTabView.myResources.getString("selectGameLabel"));
		myEnterCommentLabel = new JLabel(WallTabView.myResources.getString("enterCommentLabel"));
		myCommentEntryField = new JTextField();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(mySelectGameLabel);
		this.add(gameComboBox);
		this.add(myEnterCommentLabel);
		this.add(myCommentEntryField);
		constructRatingPanel();
		this.add(myRatingPanel);
		mySubmitButton = new JButton(WallTabView.myResources.getString("submitButton"));
		this.add(mySubmitButton);
		this.setBorder(WallTabView.constructWallBorder(WallTabView.myResources.getString("reviewPanelBorder")));
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
		gameComboBox.addActionListener(gameComboBoxListener);
	}
	
	public String getEntryText() {
		return myCommentEntryField.getText();
	}
	
	public String getSelectedGame() {
		return WallTabView.myGameChoices[gameComboBox.getSelectedIndex()];
	}
	
	public String getSelectedRating() {
		return myRatingPanel.getSelectedValue();
	}
	
	public void setEntryText(String string) {
		myCommentEntryField.setText(string);
	}
	
	/**
	 * Constructs the RatingPanel - this panel holds elements related to the Wall rating and comment submission system.
	 */
	private void constructRatingPanel() {
		myRatingPanel = new RadioPanel(5);
		ResourceBundle ratingLabelBundle = ResourceBundle.getBundle("arcade.wall.views.tierLabels");
		for (String s: ratingLabelBundle.keySet()) {
			myRatingPanel.addComment(Integer.parseInt(s), ratingLabelBundle.getString(s));
		}
		myRatingPanel.setVertical();
		myRatingPanel.setBorder(WallTabView.constructWallBorder(WallTabView.myResources.getString("ratingPanelBorder")));
	}
}

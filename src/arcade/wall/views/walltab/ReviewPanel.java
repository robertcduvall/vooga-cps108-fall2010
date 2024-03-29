package arcade.wall.views.walltab;

import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import arcade.wall.views.ratings.IconPanel;
import arcade.wall.views.ratings.RadioPanel;

/**
 * Represents the JPanel used to submit reviews.
 * @author John, Cam, Bhawana
 *
 */
@SuppressWarnings("serial")
public class ReviewPanel extends JPanel {
	
	private JTextField myReviewArea;
	private IconPanel myRatingPanel;
	private JButton myReviewButton;
	
	public ReviewPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		myReviewArea = new JTextField();
		constructRatingPanel();
		myReviewButton = new JButton(WallTabPanel.myResources.getString("reviewButton"));
		this.add(myReviewArea);
		this.add(myRatingPanel);
		this.add(myReviewButton);
		this.setBorder(WallTabPanel.constructWallBorder(WallTabPanel.myResources.getString("reviewPanelBorder")));
	}
	
	/**
	 * Adds the ReviewButtonListener to the ReviewPanel.
	 */
	public void addReviewButtonListener(
			ActionListener reviewButtonListener) {
		myReviewButton.addActionListener(reviewButtonListener);
	}
	
	public String getSelectedRating() {
		return myRatingPanel.getSelectedValue();
	}
	
	public String getEntryText() {
		return myReviewArea.getText();
	}
	
	/**
	 * Constructs the RatingPanel - this panel holds elements related to the Wall rating and comment submission system.
	 */
	private void constructRatingPanel() {
		myRatingPanel = new IconPanel(5);
		ResourceBundle ratingLabelBundle = ResourceBundle.getBundle("arcade.wall.views.tierLabels");
		for (String s: ratingLabelBundle.keySet()) {
			myRatingPanel.addComment(Integer.parseInt(s), ratingLabelBundle.getString(s));
		}
		myRatingPanel.setVertical();
		myRatingPanel.setBorder(WallTabPanel.constructWallBorder(WallTabPanel.myResources.getString("ratingPanelBorder")));
	}
}

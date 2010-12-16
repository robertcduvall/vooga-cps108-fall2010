package arcade.wall.views.wallwidget;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import arcade.wall.controllers.WallWidgetController;
import arcade.wall.views.ratings.IconPanel;

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
	private JTextArea myCommentEntryField;
	private IconPanel myRatingPanel;
	private JButton myReviewButton;
	private JLabel myGameLabel;

	public WallWidgetView(WallWidgetController controller) {
		myController = controller;
		myPanel = constructJPanel();
	}

	/**
	 * Constructs the JPanel representing WallWidget.
	 */
	private JPanel constructJPanel() {
		JPanel reviewPanel = new JPanel();
		myGameLabel = new JLabel("Having fun? Review the game!");
		
		reviewPanel.add(myGameLabel);

		myCommentEntryField = new JTextArea(4,10);
		myCommentEntryField.setLineWrap(true);
		reviewPanel.add(myCommentEntryField);
		
		JPanel ratingPanel = new JPanel();
		myRatingPanel = new IconPanel(5);
		myRatingPanel.setHorizontal();	
		ratingPanel.add(new JLabel("Poor"));
		ratingPanel.add(myRatingPanel);
		ratingPanel.add(new JLabel("Excellent"));
		ratingPanel.setLayout(new FlowLayout());
		reviewPanel.add(ratingPanel);

		myReviewButton = new JButton("Submit");
		reviewPanel.add(myReviewButton);
		
		reviewPanel.setLayout(new BoxLayout(reviewPanel, BoxLayout.Y_AXIS));
		reviewPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		return reviewPanel;
	}


	public JPanel getPanel() {
		return this.myPanel;
	}

	public String getEntryText() {
		return myCommentEntryField.getText();
	}

	public String getSelectedRating() {
		return myRatingPanel.getSelectedValue();
	}

	public void addReviewButtonListener(ActionListener reviewButtonListener) {
		myReviewButton.addActionListener(reviewButtonListener);
	}

	public void setEntryText(String string) {
		myCommentEntryField.setText(string);
		
	}
}
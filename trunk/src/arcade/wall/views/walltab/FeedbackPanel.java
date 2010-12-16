package arcade.wall.views.walltab;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FeedbackPanel extends JPanel {
	public static JComboBox myGameComboBox;
	private CommentPanel myCommentPanel;
	private ReviewPanel myReviewPanel;
	private JLabel mySelectGameLabel;
	
	public FeedbackPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		mySelectGameLabel = new JLabel(WallTabPanel.myResources.getString("selectGameLabel"));
		myGameComboBox = new JComboBox(WallTabPanel.myGameChoices);
		myGameComboBox.setSelectedIndex(0);
		myCommentPanel = new CommentPanel();
		myReviewPanel = new ReviewPanel();
		
		this.add(mySelectGameLabel);
		this.add(myGameComboBox);
		this.add(myCommentPanel);
		this.add(myReviewPanel);
	}
	
	/**
	 * Adds the GameComboBoxListener to the ReviewPanel.
	 */
	public void addGameComboBoxListener(
			ActionListener gameComboBoxListener) {
		myGameComboBox.addActionListener(gameComboBoxListener);
	}
	
	public String getSelectedGame() {
		return WallTabPanel.myGameChoices[myGameComboBox.getSelectedIndex()];
	}
	
	public CommentPanel getCommentPanel() {
		return myCommentPanel;
	}
	
	public ReviewPanel getReviewPanel() {
		return myReviewPanel;
	}
}

package arcade.wall.views.walltab;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FeedbackPanel extends JPanel {
	public static JComboBox gameComboBox;
	private CommentPanel myCommentPanel;
	private ReviewPanel myReviewPanel;
	private JLabel mySelectGameLabel;
	
	public FeedbackPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		mySelectGameLabel = new JLabel(WallTabPanel.myResources.getString("selectGameLabel"));
		gameComboBox = new JComboBox(WallTabPanel.myGameChoices);
		gameComboBox.setSelectedIndex(0);
		myCommentPanel = new CommentPanel();
		myReviewPanel = new ReviewPanel();
		
		this.add(mySelectGameLabel);
		this.add(gameComboBox);
		this.add(myCommentPanel);
		this.add(myReviewPanel);
	}
	
	/**
	 * Adds the GameComboBoxListener to the ReviewPanel.
	 */
	public void addGameComboBoxListener(
			ActionListener gameComboBoxListener) {
		gameComboBox.addActionListener(gameComboBoxListener);
	}
	
	public String getSelectedGame() {
		return WallTabPanel.myGameChoices[gameComboBox.getSelectedIndex()];
	}
	
	public CommentPanel getCommentPanel() {
		return myCommentPanel;
	}
	
	public ReviewPanel getReviewPanel() {
		return myReviewPanel;
	}
}

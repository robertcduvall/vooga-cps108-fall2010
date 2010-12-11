package arcade.wall.views.walltab;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import arcade.lobby.model.ProfileSet;
import arcade.wall.controllers.WallTabController;
import arcade.wall.models.Comment;
import arcade.wall.views.ratings.RadioPanel;

public class WallTabReviewButtonGroup {
	
	private WallTabController myController;
	private JComboBox myComboBox;
	private JTextField myTextField;
	private String myGamerName;
	private RadioPanel myRatingPanel;
	
	public WallTabReviewButtonGroup(WallTabController controller, JComboBox gameComboBox, JTextField commentEntryField, 
			RadioPanel ratingPanel) {
		myController = controller;
		myComboBox = gameComboBox;
		myTextField = commentEntryField;
		//myGamerName = ProfileSet.currentProfile.getUserName();
		myGamerName = "John";
		myRatingPanel = ratingPanel;
	}
	
	public void update() {
		String selectedGameName = WallTabView.myGameChoices[myComboBox.getSelectedIndex()];
		Comment submittedComment = new Comment(selectedGameName, myGamerName, 
											   myTextField.getText(), myRatingPanel.getSelectedValue());
		if (!myController.commentIsValid(submittedComment)) { //Comment was conflicting
			if (myController.showCommentDialog() == JOptionPane.YES_OPTION) { //Only add if user says Yes
				myController.addComment(submittedComment);
				//Update all comment ratings with the GameName and UserName in question.
				myController.updateCommentRatings(selectedGameName, myGamerName, myRatingPanel.getSelectedValue());
			}
		} else {
			myController.addComment(submittedComment);
		}
		//myController.updateCommentsArea(selectedGameName);
		myController.updateComments(selectedGameName);
		myTextField.setText("");
	}

}

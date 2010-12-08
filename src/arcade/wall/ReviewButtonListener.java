package arcade.wall;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import arcade.wall.controller.WallController;
import arcade.wall.model.Comment;
import arcade.wall.view.RatingPanel;
import arcade.wall.view.WallView;

public class ReviewButtonListener implements ActionListener {

	private WallController myController;
	private JComboBox myComboBox;
	private JTextField myTextField;
	private String myGamerName;
	private RatingPanel myRatingPanel;

	public ReviewButtonListener(WallController controller, JComboBox comboBox,
			JTextField textField, String gamerName, RatingPanel ratingPanel) {
		myController = controller;
		myComboBox = comboBox;
		myTextField = textField;
		myGamerName = gamerName;
		myRatingPanel = ratingPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String selectedGameName = WallView.myGameChoices[myComboBox.getSelectedIndex()];
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
		myController.updateCommentsArea(selectedGameName);
		myTextField.setText("");
	}

}

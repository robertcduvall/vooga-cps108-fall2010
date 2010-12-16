package arcade.wall.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import arcade.lobby.model.ProfileSet;
import arcade.wall.models.WallModel;
import arcade.wall.models.data.review.Review;
import arcade.wall.views.wallwidget.WallWidgetView;

/**
 * 
 * @author Cameron McCallie
 * @author John Kline  
 * @author Bhawana Singh
 */
public class WallWidgetController {
	WallTabController myController;
	WallModel myModel; 
	WallWidgetView myView;
	public String myTitle;

	public WallWidgetController() {
		myModel = new WallModel();
		myView = new WallWidgetView(this);	
		myView.addReviewButtonListener(new SubmitReviewButtonListener());
	}

	public WallWidgetView getView() {
		return myView;
	}
	
	public void setTitle(String title){
		myTitle = title;
	}
	
	public String getTitle(){
		return myTitle;
	}
	
	/**
	 * Displays a dialog box asking the user if they want to overwrite their previous rating given to a game.
	 * @return 
	 * 		The user's selection
	 */
	public int showCommentDialog() {
		Object[] options = {"Yes",
		"No"};
		int n = JOptionPane.showOptionDialog(myView.getPanel(),
				"Your new rating for this game is different from your last rating.\n"
				+ "Do you still want to change your rating for this game?",
				"Conflicting Ratings",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[1]);
		return n;
	}
	
	class SubmitReviewButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	String selectedGameName = myTitle;
			Review submittedReview = new Review(""+myModel.getNewReviewID(), ""+ProfileSet.getCurrentProfile().getUserId(), selectedGameName, 
					   myView.getEntryText(), myView.getSelectedRating());
    		if (myModel.reviewIsConflicting(submittedReview)) {
    			if (showCommentDialog() == JOptionPane.YES_OPTION) {
    				myModel.addReview(submittedReview, true);
    			}
    		} else {
    			myModel.addReview(submittedReview, false);
    		}
        }
	}
	

	
}

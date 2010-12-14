package arcade.wall.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import arcade.wall.models.WallModel;
import arcade.wall.models.data.comment.Comment;
import arcade.wall.views.walltab.WallTabView;

/**
 * The WallTabController is the link between WallTabView and WallTabModel.
 * @author John, Cam, Bhawana
 *
 */
public class WallTabController {
	WallModel model; //TODO make a WallModelInterface that can be used to interchange WallModels in controller constructor
	WallTabView view; //TODO allow a controller to accept multiple views

	public WallTabController() {
		model = new WallModel();
		view = new WallTabView(this);
		refreshComments(WallTabView.myGameChoices[0]);
		
		//Add listeners to the view.
		view.addGameComboBoxListener(new GameComboBoxListener());
		view.addReviewButtonListener(new ReviewButtonListener());
	}
	
	/**
	 * Refreshes the WallTab Comments Area to display the comments for the given game.
	 */
	public void refreshComments(String selectedGameName) {
		view.refreshCommentsArea(model.getGameComments(selectedGameName));
	}

	/**
	 * Displays a dialog box asking the user if they want to overwrite their previous rating given to a game.
	 * @return 
	 * 		The user's selection
	 */
	public int showCommentDialog() {
		Object[] options = {"Yes",
				"No"};
		int n = JOptionPane.showOptionDialog(view.getPanel(),
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
	
	public WallTabView getView() {
		return view;
	}

	public double getRating(String gameName) {
		return model.getAverageRating(gameName);
	}
	
	class GameComboBoxListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if ("comboBoxChanged".equals(e.getActionCommand())) {
            	String selectedGame = view.getSelectedGame();
                view.setCommentsLabel("Comments for " + selectedGame + ":");
                view.setAverageRatingLabel(selectedGame);
                refreshComments(selectedGame);
                view.setEntryText("");
    	    }
        }
    }
	
	class ReviewButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	String selectedGameName = view.getSelectedGame();
        	//TODO change to make use of ProfileSet.currentProfile
    		Comment submittedComment = new Comment(""+model.getNewCommentID(), selectedGameName, "1", 
    											   view.getEntryText(), view.getSelectedRating());
    		//TODO is this the way we actually want to determine if a Comment is valid or not?
    		if (model.commentIsConflicting(submittedComment)) {
    			if (showCommentDialog() == JOptionPane.YES_OPTION) {
    				model.updateCommentRatings(selectedGameName, "1", view.getSelectedRating());
    				model.addComment(submittedComment);
    			}
    		} else {
    			model.addComment(submittedComment);
    		}
    		view.setAverageRatingLabel(selectedGameName);
    		refreshComments(selectedGameName);
    		view.updateTopRatedGamesLabel();
    		view.setEntryText("");
        }
    }

	public List<String> getGameRankList() {
		return model.getGameRankList();
	}
}
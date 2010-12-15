package arcade.wall.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import arcade.lobby.model.Profile;
import arcade.wall.models.WallModel;
import arcade.wall.models.data.comment.Comment;
import arcade.wall.models.data.message.Message;
import arcade.wall.views.walltab.WallTabView;

/**
 * The WallTabController is the link between WallTabView and WallTabModel.
 * @author John, Cam, Bhawana
 *
 */
public class WallTabController {
	WallModel myModel; //TODO make a WallModelInterface that can be used to interchange WallModels in controller constructor
	WallTabView myView; //TODO allow a controller to accept multiple views
	Profile myProfile;

	public WallTabController() {
		myModel = new WallModel();
		myView = new WallTabView(this);
		refreshComments(WallTabView.myGameChoices[0]);
		
		//Add listeners to the view.
		myView.addGameComboBoxListener(new GameComboBoxListener());
		myView.addReviewButtonListener(new ReviewButtonListener());
		myView.addSendMessageButtonListener(new SendMessageButtonListener());
	}
	
	/**
	 * Refreshes the WallTab Comments Area to display the comments for the given game.
	 */
	public void refreshComments(String selectedGameName) {
		myView.refreshCommentsArea(myModel.getGameComments(selectedGameName));
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
	
	public WallTabView getView() {
		return myView;
	}

	public double getRating(String gameName) {
		return myModel.getAverageRating(gameName);
	}
	
	class GameComboBoxListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if ("comboBoxChanged".equals(e.getActionCommand())) {
            	String selectedGame = myView.getSelectedGame();
                myView.setGameHeaderLabel(selectedGame);
                refreshComments(selectedGame);
                myView.setEntryText("");
    	    }
        }
    }
	
	class ReviewButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	String selectedGameName = myView.getSelectedGame();
        	//TODO change to make use of ProfileSet.currentProfile
    		Comment submittedComment = new Comment(""+myModel.getNewCommentID(), selectedGameName, "1", 
    											   myView.getEntryText(), myView.getSelectedRating());
    		//TODO is this the way we actually want to determine if a Comment is valid or not?
    		if (myModel.commentIsConflicting(submittedComment)) {
    			if (showCommentDialog() == JOptionPane.YES_OPTION) {
    				myModel.updateCommentRatings(selectedGameName, "1", myView.getSelectedRating());
    				myModel.addComment(submittedComment);
    			}
    		} else {
    			myModel.addComment(submittedComment);
    		}
    		myView.setGameHeaderLabel(selectedGameName);
    		refreshComments(selectedGameName);
    		myView.updateTopRatedGamesLabel();
    		myView.setEntryText("");
        }
    }

	class SendMessageButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Message message = new Message(""+myModel.getNewMessageID(), "1", 
					myView.getReceiverText(), myView.getMessageContentText());
			myModel.addMessage(message);
			myView.setReceiverText("");
			myView.setMessageContentText("");
		}
	}
	
	class ComposeMessageButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//TODO: Make the JFrame appear
		}
	}
	
	public List<String> getGameRankList() {
		return myModel.getGameRankList();
	}

	public void setProfile(Profile profile) {
		myProfile = profile;
		myView.setProfile(profile);
	}
}
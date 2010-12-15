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
		myView.getReviewPanel().addGameComboBoxListener(new GameComboBoxListener());
		myView.getReviewPanel().addReviewButtonListener(new ReviewButtonListener());
		myView.getMessagesPanel().addSendMessageButtonListener(new SendMessageButtonListener());
		myView.getMessagesPanel().addComposeMessageButtonListener(new ComposeMessageButtonListener());
		myView.getMessagesPanel().addCloseButtonListener(new CloseButtonListener());
	}

	/**
	 * Refreshes the WallTab Comments Area to display the comments for the given game.
	 */
	public void refreshComments(String selectedGameName) {
		myView.getDisplayPanel().refreshCommentsArea(myModel.getGameComments(selectedGameName));
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

	/**
	 * Displays a dialog box asking the user if they definitely want to send the message.
	 * @return 
	 * 		The user's selection
	 */
	public int showSendMessageDialog() {
		Object[] options = {"Yes","No"};
		int n = JOptionPane.showOptionDialog(myView.getMessagesPanel().getComposeMessageFrame(),"Are you sure you want to send this message?",
				"Confirm",
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
				String selectedGame = myView.getReviewPanel().getSelectedGame();
				myView.getDisplayPanel().setGameHeaderLabel(selectedGame);
				refreshComments(selectedGame);
				myView.getReviewPanel().setEntryText("");
			}
		}
	}

	class ReviewButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	String selectedGameName = myView.getReviewPanel().getSelectedGame();
        	
    		Comment submittedComment = new Comment(""+myModel.getNewCommentID(), selectedGameName, ""+myProfile.getUserId(), 
    											   myView.getReviewPanel().getEntryText(), myView.getReviewPanel().getSelectedRating());
    		//TODO is this the way we actually want to determine if a Comment is valid or not?
    		if (myModel.commentIsConflicting(submittedComment)) {
    			if (showCommentDialog() == JOptionPane.YES_OPTION) {
    				myModel.updateCommentRatings(selectedGameName, ""+myProfile.getUserId(), myView.getReviewPanel().getSelectedRating());
    				myModel.addComment(submittedComment);
    			}
    		} else {
    			myModel.addComment(submittedComment);
    		}
    		myView.getDisplayPanel().setGameHeaderLabel(selectedGameName);
    		refreshComments(selectedGameName);
    		myView.getDisplayPanel().updateTopRatedGamesLabel();
    		myView.getReviewPanel().setEntryText("");
        }
    }

	class SendMessageButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (showSendMessageDialog() == JOptionPane.YES_OPTION){ 
				Message message = new Message(""+myModel.getNewMessageID(), "1", 
						myView.getMessagesPanel().getReceiverText(), myView.getMessagesPanel().getMessageContentText());
				myModel.addMessage(message);
				myView.getMessagesPanel().setReceiverText("");
				myView.getMessagesPanel().setMessageContentText("");
			}
		}
	}

	class ComposeMessageButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			myView.getMessagesPanel().getComposeMessageFrame().setVisible(true);
		}
	}
	
	class CloseButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			myView.getMessagesPanel().getComposeMessageFrame().dispose();
		}
	}

	public List<String> getGameRankList() {
		return myModel.getGameRankList();
	}

	public void setProfile() {
		myView.setProfile();
	}
}
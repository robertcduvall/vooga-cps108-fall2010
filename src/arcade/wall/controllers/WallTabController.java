package arcade.wall.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import arcade.lobby.model.ProfileSet;
import arcade.wall.models.WallModel;
import arcade.wall.models.data.comment.Comment;
import arcade.wall.models.data.message.Message;
import arcade.wall.models.data.review.Review;
import arcade.wall.views.walltab.WallTabPanel;

/**
 * The WallTabController is the link between WallTabView and WallTabModel.
 * @author Cameron McCallie
 * @author John Kline  
 * @author Bhawana Singh
 */
public class WallTabController {
	WallModel myModel; //TODO make a WallModelInterface that can be used to interchange WallModels in controller constructor
	WallTabPanel myView; //TODO allow a controller to accept multiple views
//	Profile myProfile;

	public WallTabController() {
		myModel = new WallModel();
		myView = new WallTabPanel(this);
		myView.getDisplayPanel().refreshCommentsArea(myModel.getGameComments(WallTabPanel.myGameChoices[0]));

		//Add listeners to the view.
		myView.getFeedbackPanel().addGameComboBoxListener(new GameComboBoxListener());
		myView.getFeedbackPanel().getCommentPanel().addCommentButtonListener(new CommentButtonListener());
		myView.getFeedbackPanel().getReviewPanel().addReviewButtonListener(new ReviewButtonListener());
		myView.getMessagesPanel().addSendMessageButtonListener(new SendMessageButtonListener());
		myView.getMessagesPanel().addComposeMessageButtonListener(new ComposeMessageButtonListener());
		myView.getMessagesPanel().addCloseButtonListener(new CloseButtonListener());
		myView.getMessagesPanel().addRefreshInboxButtonListener(new RefreshInboxButtonListener());
	}

	/**
	 * Displays a dialog box asking the user if they want to overwrite their previous review written to a game.
	 * @return 
	 * 		The user's selection
	 */
	public int showExistingReviewDialog() {
		Object[] options = {"Yes",
		"No"};
		int n = JOptionPane.showOptionDialog(myView.getPanel(),
				"You have already submitted a review for this game.\n"
				+ "Do you want to overwrite your previous review?",
				"Review Already Exists",
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
	
	public WallTabPanel getView() {
		return myView;
	}

	public double getRating(String gameName) {
		return myModel.getAverageRating(gameName);
	}

	class GameComboBoxListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if ("comboBoxChanged".equals(e.getActionCommand())) {
				String selectedGame = myView.getFeedbackPanel().getSelectedGame();
				myView.getDisplayPanel().setGameHeaderLabel(selectedGame);
				myView.getDisplayPanel().refreshCommentsArea(myModel.getGameComments(selectedGame));
				myView.getFeedbackPanel().getCommentPanel().setEntryText("");
			}
		}
	}

	class CommentButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	String selectedGameName = myView.getFeedbackPanel().getSelectedGame();
        	
    		Comment submittedComment = new Comment(""+myModel.getNewCommentID(), selectedGameName, ""+ProfileSet.getCurrentProfile().getUserId(), 
    											   myView.getFeedbackPanel().getCommentPanel().getEntryText());
    		myModel.addComment(submittedComment);
    		//myView.getDisplayPanel().setGameHeaderLabel(selectedGameName);
    		myView.getDisplayPanel().refreshCommentsArea(myModel.getGameComments(selectedGameName));
    		myView.getDisplayPanel().updateTopRatedGamesLabel();
    		myView.getFeedbackPanel().getCommentPanel().setEntryText("");
        }
    }
	
	class ReviewButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedGameName = myView.getFeedbackPanel().getSelectedGame();
			System.out.println(ProfileSet.getCurrentProfile().getUserName()+
					"is reviewing "+ selectedGameName);
			Review submittedReview = new Review(""+myModel.getNewReviewID(), ""+ProfileSet.getCurrentProfile().getUserId(), selectedGameName, 
					   myView.getFeedbackPanel().getReviewPanel().getEntryText(), myView.getFeedbackPanel().getReviewPanel().getSelectedRating());
			if (myModel.reviewIsConflicting(submittedReview)) {
    			if (showExistingReviewDialog() == JOptionPane.YES_OPTION) {
    				myModel.addReview(submittedReview);
    			}
    		} else {
    			myModel.addReview(submittedReview);
    		}
		}
	}

	class SendMessageButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (showSendMessageDialog() == JOptionPane.YES_OPTION){ 
				Message message = new Message(""+myModel.getNewMessageID(), ProfileSet.getCurrentProfile().getUserName(), 
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
	
	class RefreshInboxButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			List<Message> messageList = myModel.getMessageSet().getMessagesByField("Receiver", ""+ProfileSet.getCurrentProfile().getUserName());
			myView.getMessagesPanel().refreshInbox(messageList);
			myView.getMessagesPanel().validate();
		}
	}

	public List<String> getGameRankList() {
		return myModel.getGameRankList();
	}

	public void refreshMainPanelText() {
		myView.setMainPanelText();
	}
}
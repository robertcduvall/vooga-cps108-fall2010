package arcade.wall.models;

import java.util.List;

import arcade.wall.models.data.comment.Comment;
import arcade.wall.models.data.comment.CommentSet;
import arcade.wall.models.data.gamereport.GameReport;
import arcade.wall.models.data.gamereport.GameReportSet;
import arcade.wall.models.data.message.Message;
import arcade.wall.models.data.message.MessageSet;
import arcade.wall.models.data.review.Review;
import arcade.wall.models.data.review.ReviewSet;

/**
 * WallModel is the Wall entity that deals with the handling of Comment data, which is stored in an online database.
 * @author John, Cam, Bhawana
 *
 */
public class WallModel implements IWallModel {
	private String myHost;
	private String myDbName;
	private String myDbUserName;
	private String myDbPassword;
	private CommentSet myCommentSet;
	private GameReportSet myGameReportSet;
	private MessageSet myMessageSet;
	private ReviewSet myReviewSet;

	public WallModel() {
		myHost = "voogaarcade.db.7093929.hostedresource.com";
		myDbName = "voogaarcade";
		myDbUserName = myDbName;
		myDbPassword = "Vooga108";
		myCommentSet = new CommentSet(myHost, myDbName, "Comments", myDbUserName, myDbPassword);
		myGameReportSet = new GameReportSet(myHost, myDbName, "GameReports", myDbUserName, myDbPassword);
		myMessageSet = new MessageSet(myHost, myDbName, "Messages", myDbUserName, myDbPassword);
	}

	/**
	 * Adds a Comment to the Model's CommentSet.
	 * @param comment
	 * 		The Comment to add
	 */
	public void addComment(Comment comment){
		myCommentSet.addComment(comment);
		myGameReportSet.incrementComments(comment.getGameInfoTitle());
	}

	public void addGameReport(GameReport gameReport) {
		myGameReportSet.addGameReport(gameReport);
	}

	public void addMessage(Message message) {
		myMessageSet.addMessage(message);
	}
	
	public void addReview(Review review) {
		double newAverageRating = myReviewSet.getAverageRating(review.getGameInfoTitle());
		myGameReportSet.updateAverageRating(review.getGameInfoTitle(), newAverageRating);
	}

	/**
	 * Checks the ReviewSet to see if the given Review is valid; that is, has the current user already 
	 * submitted a Review about this game?
	 * @param review
	 * 		The review to check
	 * @return 
	 * 		Whether the review is valid or not
	 */
	public boolean reviewIsConflicting(Review review) {
		return myReviewSet.reviewIsConflicting(review);
	}

	public void updateReview(String selectedGameName,
			String myUserId, String selectedValue) {
		myCommentSet.updateCommentRatings(selectedGameName,
				myUserId, selectedValue);
	}

	/**
	 * Returns the Model's CommentSet.
	 */
	public CommentSet getCommentSet() {
		return this.myCommentSet;
	}

	public GameReportSet getGameReportSet() {
		return this.myGameReportSet;
	}

	public int getNewCommentID() {
		return this.myCommentSet.currentID;
	}
	
	public int getNewReviewID() {
		return this.myReviewSet.currentID;
	}

	public int getNewGameReportID() {
		return this.myGameReportSet.currentID;
	}

	public int getNewMessageID() {
		return this.myMessageSet.currentID;
	}

	public List<Comment> getGameComments(String gameName) {
		return myCommentSet.getCommentsByField("GameInfo_Title", gameName);
	}

	/**
	 * Returns all Comments related to a game.
	 * @param userId - user whose comments we are interested in
	 * @return a List of all the comments entered by the specified user
	 */
	public List<Comment> getUserComments(String userId) {
		return myCommentSet.getCommentsByField("User_Id", userId);
	}

	/**
	 * Returns the average rating for the specified game.
	 * @param gameName - game whose rating is needed
	 * @return average rating for the specified game
	 */
	public double getAverageRating(String gameName) {
		return myGameReportSet.getAverageRating(gameName);
	}

	public List<String> getGameRankList() {
		return myGameReportSet.getGameRankList();
	}
	
}

package arcade.wall.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import arcade.wall.models.data.comment.Comment;
import arcade.wall.models.data.comment.CommentSet;
import arcade.wall.models.data.gamereport.GameReport;
import arcade.wall.models.data.gamereport.GameReportSet;
import arcade.wall.models.data.message.Message;
import arcade.wall.models.data.message.MessageSet;

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
		double newAverageRating = myCommentSet.getAverageRating(comment.getGameTitle());
		myGameReportSet.updateAverageRating(comment.getGameTitle(), newAverageRating);
		myGameReportSet.incrementComments(comment.getGameTitle());
	}

	public void addGameReport(GameReport gameReport) {
		myGameReportSet.addGameReport(gameReport);
	}

	public void addMessage(Message message) {
		myMessageSet.addMessage(message);
	}

	/**
	 * Checks the CommentSet to see if the given Comment is valid; that is, did the user who made the Comment already
	 * rate the game in question, and did they rate it differently this time? If so, there is a "rating conflict" and
	 * the Comment is "invalid".
	 * @param comment
	 * 		The comment to check
	 * @return 
	 * 		Whether the comment is valid or not
	 */
	public boolean commentIsConflicting(Comment comment) {
		return myCommentSet.commentIsConflicting(comment);
	}

	public void updateCommentRatings(String selectedGameName,
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

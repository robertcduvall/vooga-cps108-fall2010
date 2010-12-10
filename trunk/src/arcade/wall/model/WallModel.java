package arcade.wall.model;

import java.util.ArrayList;
import java.util.List;

/**
 * WallModel is the Wall entity that deals with the handling of Comment data, which is stored in an online database.
 * @author John, Cam, Bhawana
 *
 */
public class WallModel {
	private String myHost;
	private String myDbName;
	private String myDbUserName;
	private String myDbPassword;
	private CommentSet myCommentSet;

	public WallModel() {
		myHost = "voogaarcade.db.7093929.hostedresource.com";
		myDbName = "voogaarcade";
		myDbUserName = myDbName;
		myDbPassword = "Vooga108";
		myCommentSet = new CommentSet(myHost, myDbName, "Comments", myDbUserName, myDbPassword);
	}

	/**
	 * Adds a Comment to the Model's CommentSet.
	 * @param comment
	 * 		The Comment to add
	 */
	public void addComment(Comment comment){
		myCommentSet.addComment(comment);
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
	public boolean commentIsValid(Comment comment) {
		boolean noConflict = true;
		for (Comment c: myCommentSet) {
			if (c.getGameName().equals(comment.getGameName()) && 
					c.getUserName().equals(comment.getUserName()) && 
					!c.getRating().equals(comment.getRating())) { //User's ratings are conflicting.
				noConflict = false;
			}
		}
		return noConflict;
	}

	/**
	 * Updates the ratings given by a particular user to a particular game.
	 * @param selectedGameName
	 * 		The game to match
	 * @param myGamerName
	 * 		The username to match
	 * @param selectedValue
	 * 		The new rating to overwrite with
	 */
	public void updateCommentRatings(String selectedGameName,
			String myGamerName, String selectedValue) {
		for (Comment c: myCommentSet) {
			if (c.getUserName().equals(myGamerName) && c.getGameName().equals(selectedGameName)) {
				c.setRating(selectedValue);
				myCommentSet.updateCommentRating(c, selectedValue);
			}
		}
	}
	
	public CommentSet getCommentSet() {
		return this.myCommentSet;
	}
	
	
	/**
	 * Returns all Comments related to a game.
	 * @param gameName - game for which we want the comments
	 * @return a List of all the comments for the specified game
	 */
	public List<Comment> getGameComments(String gameName) {
		List<Comment> gameComments = new ArrayList<Comment>();
		for(Comment comment: myCommentSet){
			if (comment.getGameName().equals(gameName)){
				gameComments.add(comment);
			}
		}
		return gameComments;
	}
	
	/**
	 * Returns all Comments related to a game.
	 * @param userName - user whose comments we are interested in
	 * @return a List of all the comments entered by the specified user
	 */
	public List<Comment> getUserComments(String userName) {
		List<Comment> userComments = new ArrayList<Comment>();
		for(Comment comment: myCommentSet){
			if (comment.getUserName().equals(userName)){
				userComments.add(comment);
			}
		}
		return userComments;
	}
	
	/**
	 * Returns the average rating for the specified game.
	 * @param gameName - game whose rating is needed
	 * @return average rating for the specified game
	 */
	public double getGameRating(String gameName) {
		double rating = 0;
		List<Comment> gameComments = getGameComments(gameName);
		for(Comment comment: gameComments){
			rating += Integer.parseInt(comment.getRating());
		}
		rating /= gameComments.size();
		return rating;
	}
}

package arcade.wall.models;

import java.util.List;

/**
 * WallModel is the Wall entity that deals with the handling of Comment data, which is stored in an online database.
 * @author John, Cam, Bhawana
 *
 */
public class WallTabModel implements IWallModel {
	private String myHost;
	private String myDbName;
	private String myDbUserName;
	private String myDbPassword;
	private CommentSet myCommentSet;

	public WallTabModel() {
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
	
	public int getNewID() {
		return this.myCommentSet.currentID;
	}
	
	public List<Comment> getGameComments(String gameName) {
		return myCommentSet.getGameComments(gameName);
	}
	
	/**
	 * Returns all Comments related to a game.
	 * @param userName - user whose comments we are interested in
	 * @return a List of all the comments entered by the specified user
	 */
	public List<Comment> getUserComments(String userName) {
		return myCommentSet.getUserComments(userName);
	}
	
	/**
	 * Returns the average rating for the specified game.
	 * @param gameName - game whose rating is needed
	 * @return average rating for the specified game
	 */
	public double getAverageRating(String gameName) {
		return myCommentSet.getAverageRating(gameName);
//		double rating = 0;
//		
//		List<Comment> gameComments = getGameComments(gameName);
//		for(Comment comment: gameComments){
//			rating += Integer.parseInt(comment.getRating());
//		}
//		rating /= gameComments.size();
//		return rating;
	}
}

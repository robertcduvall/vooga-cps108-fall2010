package arcade.wall.models;

/**
 * A Comment represents a VOOGA Game Comment - it is about a particular game, it is made by a particular user,
 * it contains a "Comment String" of text, and it is linked to the rating a user has given the game.
 * @author John
 *
 */
public class Comment {
	private String myId;
	private String myGameName;
	private String myUserName;
	private String myCommentString;
	private String myRating;
	
	public Comment(String id, String gameName, String userName, String commentString, String rating) {
		myId = id;
		myGameName = gameName;
		myUserName = userName;
		myCommentString = commentString;
		myRating = rating;
	}
	
	public String getId() {
		return myId;
	}
	
	public String getGameName() {
		return myGameName;
	}
	
	public String getUserName() {
		return myUserName;
	}
	
	public String getCommentString() {
		return myCommentString;
	}
	
	//TODO change rating to an int
	public String getRating() {
		return myRating;
	}

	public void setId(String id) {
		myId = id;
	}
	
	public void setGameName(String string) {
		this.myGameName = string;
	}
	
	public void setUserName(String string) {
		this.myUserName = string;
	}
	
	public void setCommentString(String string) {
		this.myCommentString = string;
	}

	public void setRating(String selectedValue) {
		this.myRating = selectedValue;
	}
	
//	/**
//	 * Returns true if the two comments have the same rating, are about the same game, and are by the same user.
//	 */
//	public boolean equals(Comment comment) {
//		return (this.getGameName().equals(comment.getGameName()) &&
//				this.getUserName().equals(comment.getUserName()) &&
//				this.getRating().equals(comment.getRating()));
//	}
}

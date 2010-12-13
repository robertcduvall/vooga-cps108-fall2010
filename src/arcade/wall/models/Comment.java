package arcade.wall.models;

/**
 * A Comment represents a VOOGA Game Comment - it is about a particular game, it is made by a particular user,
 * it contains a "Comment String" of text, and it is linked to the rating a user has given the game.
 * @author John
 *
 */
public class Comment {
	private String myId;
	private String myGameTitle;
	private String myUserId;
	private String myString;
	private String myRating;
	
	public Comment(String id, String gameName, String userId, String commentString, String rating) {
		myId = id;
		myGameTitle = gameName;
		myUserId = userId;
		myString = commentString;
		myRating = rating;
	}
	
	public String getId() {
		return myId;
	}
	
	public String getGameTitle() {
		return myGameTitle;
	}
	
	public String getUserId() {
		return myUserId;
	}
	
	public String getString() {
		return myString;
	}
	
	//TODO change rating to an int
	public String getRating() {
		return myRating;
	}

	public void setId(String id) {
		myId = id;
	}
	
	public void setGameTitle(String string) {
		this.myGameTitle = string;
	}
	
	public void setUserId(String string) {
		this.myUserId = string;
	}
	
	public void setString(String string) {
		this.myString = string;
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

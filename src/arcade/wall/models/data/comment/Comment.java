package arcade.wall.models.data.comment;

import arcade.wall.models.data.Datum;

/**
 * A Comment represents a VOOGA Game Comment - it is about a particular game, it is made by a particular user,
 * it contains a "Comment String" of text, and it is linked to the rating a user has given the game.
 * @author John
 *
 */
public class Comment extends Datum {
	private String myGameInfoTitle;
	private String myUserId;
	private String myString;
	private String myRating;
	
	public Comment(String id, String gameName, String userId, String commentString, String rating) {
		super(id);
		myGameInfoTitle = gameName;
		myUserId = userId;
		myString = commentString;
		myRating = rating;
	}
	
	public String getGameTitle() {
		return myGameInfoTitle;
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
	
	public void setGameTitle(String string) {
		this.myGameInfoTitle = string;
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
}

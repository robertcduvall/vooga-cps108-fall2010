package arcade.wall.models.data.comment;

import arcade.wall.models.data.Datum;

/**
 * A Comment represents a VOOGA Game Comment - it is about a particular game, it is made by a particular user, and
 * it contains a "Comment String" of text.
 * @author John
 *
 */
public class Comment extends Datum {
	private String myGameInfoTitle;
	private String myUserId;
	private String myString;
	
	public Comment(String id, String gameName, String userId, String commentString) {
		super(id);
		myGameInfoTitle = gameName;
		myUserId = userId;
		myString = commentString;
	}
	
	public String getGameInfoTitle() {
		return myGameInfoTitle;
	}
	
	public String getUserId() {
		return myUserId;
	}
	
	public String getString() {
		return myString;
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
}

package arcade.wall.models.data.review;

import arcade.wall.models.data.Datum;

/**
 * Represents a user's review of a game. A user can only submit a single review about each game.
 * @author John
 *
 */
public class Review extends Datum {

	//TODO make this an int? and change call in PurchaseItemController
	private String myUserId;
	private String myGameInfoTitle;
	private String myContent;
	private String myRating;
	
	public Review(String id, String userId, String gameInfoTitle, String content, String rating) {
		super(id);
		this.myUserId = userId;
		this.myGameInfoTitle = gameInfoTitle;
		this.myContent = content;
		this.myRating = rating;
	}
	
	public String getUserId() {
		return myUserId;
	}
	
	public String getGameInfoTitle() {
		return myGameInfoTitle;
	}
	
	public String getContent() {
		return myContent;
	}
	
	public String getRating() {
		return myRating;
	}
	
}

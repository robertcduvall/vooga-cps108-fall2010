package arcade.wall.model;

public class Comment {
	private String myGameName;
	private String myUserName;
	private String myCommentString;
	private String myRating;
	
	public Comment(String gameName, String userName, String commentString, String rating) {
		myGameName = gameName;
		myUserName = userName;
		myCommentString = commentString;
		myRating = rating;
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
	
	public String getRating() {
		return myRating;
	}

	public void setCommentString(String string) {
		this.myCommentString = string;
	}

	public void setRating(String selectedValue) {
		this.myRating = selectedValue;
	}
}

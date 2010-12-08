package arcade.wall.model;


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

	public void addComment(String game, String user, String comment, String rating){
		Comment newComment = new Comment(game, user, comment, rating);
		myCommentSet.addComment(newComment);
	}
	
	public boolean commentIsValid(String game, String user, String comment, String rating) {
		boolean canAddComment = true;
		for (Comment c: myCommentSet) {
			if (c.getUserName().equals(user) && !c.getRating().equals(rating)) { //User's ratings are conflicting.
				canAddComment = false;
			}
		}
		return canAddComment;
	}

	public CommentSet getCommentSet() {
		return this.myCommentSet;
	}

	public void updateCommentRatings(String selectedGameName,
			String myGamerName, String selectedValue) {
		for (Comment c: myCommentSet) {
			if (c.getUserName().equals(myGamerName) && c.getGameName().equals(selectedGameName)) {
				c.setRating(selectedValue);
				//This doesn't work yet...need supporting method in MySqlAdapter?
				//myCommentSet.updateCommentRating(c);
			}
		}
		
	}	
}

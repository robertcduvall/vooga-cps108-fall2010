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
	
	public CommentSet getCommentSet() {
		return this.myCommentSet;
	}	
}

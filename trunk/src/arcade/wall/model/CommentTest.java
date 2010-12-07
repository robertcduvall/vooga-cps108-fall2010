package arcade.wall.model;

public class CommentTest {

	public static void main(String[] args) {
		String host = "voogaarcade.db.7093929.hostedresource.com";
		String dbName = "voogaarcade";
		String user = dbName;
		String pass = "Vooga108";
		CommentSet cs = new CommentSet(host, dbName, "Comments", user, pass);
		Comment comment = new Comment(user, "WOWEE!");
		cs.addComment(comment);
		for (Comment c : cs) {
			System.out.println(String.format("User: %s, Comment: %s",
					c.getUserName(),c.getCommentString()));
		}

	}

}

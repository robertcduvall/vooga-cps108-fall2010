package arcade.wall;

public class WallController {
	WallModel model = new WallModel();
	
	public String addComment(String game, String comment, String user){
		model.addComment(game, comment, user);
		//TODO - modify so that it returns all the comments for this game from the database
		//in View, display all these comments
		return comment;		
	}

}

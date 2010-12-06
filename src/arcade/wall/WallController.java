package arcade.wall;

import java.util.List;

public class WallController {
	WallModel model = new WallModel();
	WallView view = new WallView("Gamer13", this);
	
	public List<String> addComment(String game, String comment, String user){
		List<String> comments = model.addComment(game, comment, user);
		return comments;		
	}

}

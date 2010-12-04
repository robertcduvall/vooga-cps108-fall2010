package arcade.wall;

import vooga.engine.resource.DBHandler;
import vooga.engine.resource.Entry;

public class WallModel {

	public void addComment(String game, String comment, String user){
		DBHandler.open("src/arcade/wall/WallDB.sqlite");
		DBHandler.setTable("GameReviews");
		DBHandler.addRow(new Entry<String>("Game", game),
				 		 new Entry<String>("Comments", comment),
				 		 new Entry<String>("Username", user));
	}
}

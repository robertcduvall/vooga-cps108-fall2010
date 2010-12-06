package arcade.wall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vooga.engine.resource.DBHandler;
import vooga.engine.resource.Entry;

public class WallModel {
	private final static String database = "src/arcade/wall/WallDB.sqlite";
	private final static String table = "GameReviews";
	private static final String gamesColumn = "Game";
	private static final String commentsColumn = "Comments";
	private static final String usernameColumn = "Username";
	
	public List<String> addComment(String game, String comment, String user){
		DBHandler.open(database);
		DBHandler.setTable(table);
		DBHandler.addRow(new Entry<String>(gamesColumn, game),
				 		 new Entry<String>(commentsColumn, comment),
				 		 new Entry<String>(usernameColumn, user));
		
		List<String> comments = getComments().get(game);
		return comments;
	}

//	public WallModel() {
//		DBHandler.open("src/arcade/wall/WallDB.sqlite");
//		DBHandler.createTable("GameReviews", "Game", "Comments", "Rating", "Username");
//		DBHandler.setTable("GameReviews");
//		
//	}
	
//	public void addComment(String game, String comment, String user){
//		DBHandler.open("src/arcade/wall/WallDB.sqlite");
//		DBHandler.setTable("GameReviews");
//		DBHandler.addRow(new Entry<String>("Game", game),
//				 		 new Entry<String>("Comments", comment),
//				 		 new Entry<String>("Username", user));
//	}
	private Map<String,ArrayList<String>> getComments() {
		List<String> comments = DBHandler.getColumn(commentsColumn,new String());
		List<String> games = DBHandler.getColumn(gamesColumn,new String());
		Map<String,ArrayList<String>> gameComments = new HashMap<String,ArrayList<String>>();
		for(int i=0;i<comments.size();i++){
			if(!gameComments.containsKey(games.get(i))){
				gameComments.put(games.get(i),new ArrayList<String>());
			}
			gameComments.get(games.get(i)).add(comments.get(i));
		}
		return gameComments;
	}
	
}

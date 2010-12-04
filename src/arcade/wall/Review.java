package arcade.wall;

import vooga.engine.resource.DBHandler;

public class Review {

	public Review() {
		DBHandler.open("src/arcade/wall/WallDB.sqlite");
		DBHandler.createTable("GameReviews", "Game", "Comments", "Rating", "Username");
		DBHandler.setTable("GameReviews");
	}
	
	public static void main(String[] args){
		Review review = new Review();
	}
	
}

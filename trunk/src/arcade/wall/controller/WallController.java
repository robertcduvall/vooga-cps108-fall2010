package arcade.wall.controller;

import arcade.wall.model.Comment;
import arcade.wall.model.WallModel;
import arcade.wall.view.WallView;

public class WallController {
	WallModel model;
	WallView view;
	
	public WallController() {
		model = new WallModel();
		view = new WallView("Gamer13", this);
		updateCommentsArea(WallView.choices[0]);
		view.setVisible();
	}
	
	public void addComment(String game, String user, String comment, String rating){
		model.addComment(game, user, comment, rating);		
	}

	public void updateCommentsArea(String selectedGameName) {
		String input = "";
		for (Comment comment: model.getCommentSet()) {
			String starString = "";
			if (comment.getRating().equals("0"))
				starString = ":(";
			for (int i = 0; i < Integer.parseInt(comment.getRating()); i++) {
				starString += "*";
			}
			if (comment.getGameName().equals(selectedGameName))
				input += comment.getCommentString() + "  " + starString + " " +  "---" + comment.getUserName() + "\n";
		}
		view.getCommentsArea().setText(input);
	}

}

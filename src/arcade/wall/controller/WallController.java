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
	}
	
	public void addComment(String game, String user, String comment){
		model.addComment(game, user, comment);		
	}

	public void updateCommentsArea() {
		String input = "";
		for (Comment comment: model.getCommentSet()) {
			input += comment.getCommentString() + "---" + comment.getUserName() + "\n";
		}
		view.getCommentsArea().setText(input);
	}

}

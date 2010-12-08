package arcade.wall.controller;

import javax.swing.JOptionPane;

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
		view.setFrameVisible();
	}

	public void addComment(String game, String user, String comment, String rating){
		model.addComment(game, user, comment, rating);		
	}
	
	public boolean commentIsValid(String game, String user, String comment, String rating) {
		return model.commentIsValid(game, user, comment, rating);
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
				input += " >> " + comment.getCommentString() + "  " + starString + 
				" " +  "---" + comment.getUserName() + "\n";
		}
		view.getCommentsArea().setText(input);
	}

	public int showCommentDialog() {
		Object[] options = {"Yes",
				"No"};
		int n = JOptionPane.showOptionDialog(view.getFrame(),
				"Your new rating for this game is different from your last rating.\n"
				+ "Do you still want to change your rating for this game?",
				"Conflicting Ratings",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[1]);
		return n;
	}

	public void updateCommentRatings(String selectedGameName,
			String myGamerName, String selectedValue) {
		model.updateCommentRatings(selectedGameName,
				myGamerName, selectedValue);
		
	}
}
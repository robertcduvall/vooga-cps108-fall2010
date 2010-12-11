package arcade.wall.controller;

import javax.swing.JOptionPane;

import arcade.wall.model.Comment;
import arcade.wall.model.WallTabModel;
import arcade.wall.view.WallTabView;

/**
 * The WallController is the link between WallView and WallModel.
 * @author John, Cam, Bhawana
 *
 */
public class WallTabController {
	WallTabModel model; //TODO make a WallModelInterface that can be used to interchange WallModels in controller constructor
	WallTabView view; //TODO allow a controller to accept multiple views

	public WallTabController() {
		model = new WallTabModel();
		view = new WallTabView();
		updateComments(WallTabView.myGameChoices[0]);
		view.setController(this);
	}
	
	/**
	 * Adds a Comment to the Model.
	 * @param comment
	 * 		The comment to add
	 */
	public void addComment(Comment comment){
		model.addComment(comment);		
	}
	
	//TODO is this the way we actually want to determine if a Comment is valid or not?
	/**
	 * Checks the Model to see if the given Comment is valid.
	 * @param comment
	 * 		The comment to check
	 * @return 
	 * 		Whether the comment is valid or not
	 */
	public boolean commentIsValid(Comment comment) {
		return model.commentIsValid(comment);
	}

	//TODO make this method return the output so the View is what updates the TextField
//	/**
//	 * Updates the "Comments Area" to display the comments given to the selected game.
//	 * @param selectedGameName
//	 * 		The game to display comments for.
//	 */
//	public void updateCommentsArea(String selectedGameName) {
//		String input = "";
//		for (Comment comment: model.getCommentSet()) {
//			String starString = "";
//			if (comment.getRating().equals("0"))
//				starString = ":("; //TODO Is this needed?
//			for (int i = 0; i < Integer.parseInt(comment.getRating()); i++) {
//				starString += "*";
//			}
//			if (comment.getGameName().equals(selectedGameName))
//				input += " >> ''" + comment.getCommentString() + "''  " + starString + 
//				" " +  "---" + comment.getUserName() + "\n";
//		}
//		view.getCommentsArea().setText(input);
//	}
	
	//TODO
	//Used by ComboBoxListener and ReviewButtonListener
	public void updateComments(String selectedGameName) {
		view.updateCommentsPanel(model.getGameComments(selectedGameName));
	}

	/**
	 * Displays a dialog box asking the user if they want to overwrite their previous rating given to a game.
	 * @return 
	 * 		The user's selection
	 */
	public int showCommentDialog() {
		Object[] options = {"Yes",
				"No"};
		int n = JOptionPane.showOptionDialog(view.getPanel(),
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

	/**
	 * Uses the Model to update game ratings.
	 * @param selectedGameName
	 * 		The game to match
	 * @param myGamerName
	 * 		The username to match
	 * @param selectedValue
	 * 		The new rating to overwrite with
	 */
	public void updateCommentRatings(String selectedGameName,
			String myGamerName, String selectedValue) {
		model.updateCommentRatings(selectedGameName,
				myGamerName, selectedValue);
	}
	
	public WallTabView getView() {
		return view;
	}
}
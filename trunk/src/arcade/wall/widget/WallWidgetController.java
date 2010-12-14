package arcade.wall.widget;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import arcade.wall.controllers.WallTabController;
import arcade.wall.models.WallModel;
import arcade.wall.models.data.comment.Comment;

public class WallWidgetController {
	WallTabController controller;
	WallModel model; 
	WallWidgetView view;

	public WallWidgetController() {
		model = new WallModel();
		view = new WallWidgetView(this);	
		view.addReviewButtonListener(new ReviewButtonListener());
	}

	public WallWidgetView getView() {
		return view;
	}
	
	class ReviewButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	String selectedGameName = view.getSelectedGame();
    		Comment submittedComment = new Comment(""+model.getNewCommentID(), selectedGameName, "42", 
    											   view.getEntryText(), view.getSelectedRating());
    		model.addComment(submittedComment);
    		JOptionPane.showMessageDialog(view.getPanel(),
    				"Your comments were successfully added.\n","",
    				JOptionPane.PLAIN_MESSAGE );
        }
	}
	
	class GameComboBoxListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
            if ("comboBoxChanged".equals(e.getActionCommand())) {
                view.setEntryText("");
    	    }
        }
	}
	
}

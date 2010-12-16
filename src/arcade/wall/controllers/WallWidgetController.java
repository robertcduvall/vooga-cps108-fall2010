package arcade.wall.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import arcade.lobby.model.ProfileSet;
import arcade.wall.models.WallModel;
import arcade.wall.models.data.comment.Comment;
import arcade.wall.views.wallwidget.WallWidgetView;

/**
 * 
 * @author Cameron McCallie
 * @author John Kline  
 * @author Bhawana Singh
 */
public class WallWidgetController {
	WallTabController controller;
	WallModel model; 
	WallWidgetView view;
	public String myTitle;

	public WallWidgetController() {
		model = new WallModel();
		view = new WallWidgetView(this);	
		view.addReviewButtonListener(new ReviewButtonListener());
	}

	public WallWidgetView getView() {
		return view;
	}
	
	public void setTitle(String title){
		myTitle = title;
	}
	
	public String getTitle(){
		return myTitle;
	}
	
	//not being used
	class ReviewButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	String selectedGameName = view.getSelectedGame();
    		Comment submittedComment = new Comment(""+model.getNewCommentID(), selectedGameName, ""+ProfileSet.getCurrentProfile().getUserId(), 
    											   view.getEntryText());
    		model.addComment(submittedComment);
    		JOptionPane.showMessageDialog(view.getPanel(),
    				"Your comments were successfully added.\n","",
    				JOptionPane.PLAIN_MESSAGE );
        }
	}
	

	
}

package arcade.wall;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vooga.engine.resource.DBHandler;
import vooga.engine.resource.Entry;

import arcade.wall.gui.WallDemo;

public class ReviewAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO - Does this need to be done always?
		// Best way to avoid this?
		DBHandler.open("src/arcade/wall/WallDB.sqlite");
		DBHandler.setTable("GameReviews");
		String gameName = WallDemo.choices[WallDemo.gameChoices.getSelectedIndex()];
		String userName = WallDemo.myName;
		int rating = 0;
		String comment = WallDemo.getCommentEntryField().getText();
		DBHandler.addRow(new Entry<String>("Game", gameName),
						 new Entry<String>("Comments", comment),
						 new Entry<Integer>("Rating", rating),
						 new Entry<String>("Username", userName));
		WallDemo.getCommentsLabel().setText("<html>" + 
				  "Comments for " +  gameName + ": " +
				  "<br/>" + 
				  comment + "  ---" + userName +
				  "</html>");
		
	}

}

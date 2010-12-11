package arcade.wall.views.walltab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import arcade.wall.models.Comment;

public class ReviewButtonListener implements ActionListener {

	private WallTabReviewButtonGroup myWallTabReviewButtonGroup;
	
	public ReviewButtonListener(WallTabReviewButtonGroup wallTabReviewButtonGroup) {
		myWallTabReviewButtonGroup = wallTabReviewButtonGroup;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		myWallTabReviewButtonGroup.update();
	}

}

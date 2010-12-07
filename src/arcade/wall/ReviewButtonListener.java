package arcade.wall;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import arcade.wall.controller.WallController;
import arcade.wall.view.RatingPanel;
import arcade.wall.view.WallView;

public class ReviewButtonListener implements ActionListener {

	private WallController myController;
	private JComboBox myComboBox;
	private JTextField myTextField;
	private String myGamerName;
	private RatingPanel myRatingPanel;

	public ReviewButtonListener(WallController controller, JComboBox comboBox,
			JTextField textField, String gamerName, RatingPanel ratingPanel) {
		myController = controller;
		myComboBox = comboBox;
		myTextField = textField;
		myGamerName = gamerName;
		myRatingPanel = ratingPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String selectedGameName = WallView.choices[myComboBox.getSelectedIndex()];
		myController.addComment(selectedGameName,
				myGamerName, myTextField.getText(), myRatingPanel.getSelectedValue());
		myController.updateCommentsArea(selectedGameName);
	}

}

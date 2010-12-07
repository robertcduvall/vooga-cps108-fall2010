package arcade.wall;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

import arcade.wall.controller.WallController;
import arcade.wall.view.WallView;

public class ReviewButtonListener implements ActionListener {

	private WallController myController;
	private JComboBox myComboBox;
	private JTextField myTextField;
	private String myGamerName;

	public ReviewButtonListener(WallController controller, JComboBox comboBox,
			JTextField textField, String gamerName) {
		myController = controller;
		myComboBox = comboBox;
		myTextField = textField;
		myGamerName = gamerName;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String selectedGameName = WallView.choices[myComboBox.getSelectedIndex()];
		myController.addComment(selectedGameName,
				myGamerName, myTextField.getText());
		myController.updateCommentsArea(selectedGameName);
	}

}

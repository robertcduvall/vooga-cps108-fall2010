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
	private JComponent myLabel;
	private JTextField myTextField;
	private String myGamerName;

	public ReviewButtonListener(WallController controller, JComboBox comboBox, 
								JComponent commentBox, JTextField textField, String gamerName) {
		myController = controller;
		myComboBox = comboBox;
		myLabel = commentBox;
		myTextField = textField;
		myGamerName = gamerName;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		myController.addComment(WallView.choices[myComboBox.getSelectedIndex()],
				myGamerName, myTextField.getText());
		myController.updateCommentsArea();
	}

}

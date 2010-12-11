package arcade.wall;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import arcade.wall.controller.WallController;
import arcade.wall.view.WallView;

public class GameComboBoxListener implements ActionListener {
	private WallController myController;
	private JLabel myLabel;
	private JComboBox myComboBox;
	private JTextField myTextField;
	
	public GameComboBoxListener(WallController controller, JLabel label, JComboBox comboBox, JTextField textField) {
		myController = controller;
		myLabel = label;
		myComboBox = comboBox;
		myTextField = textField;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("comboBoxChanged".equals(e.getActionCommand())) {
			String selectedGameName = WallView.myGameChoices[myComboBox.getSelectedIndex()];
	        myLabel.setText("Comments for " + selectedGameName + ":");
	        //myController.updateCommentsArea(selectedGameName);
	        myController.updateComments(selectedGameName);
	        myTextField.setText("");
	    }
	}
}

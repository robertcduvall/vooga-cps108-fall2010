package arcade.wall;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import arcade.wall.controller.WallTabController;
import arcade.wall.view.WallTabView;

public class GameComboBoxListener implements ActionListener {
	private WallTabController myController;
	private JLabel myLabel;
	private JComboBox myComboBox;
	private JTextField myTextField;
	
	//TODO pass in an object that holds the label combobox and textfield (View components), then actionPerformed
	//can delegate this work. This object should know what the controller is, so this view-object can call the
	//controller, so the only thing that needs to be passed in is this view-object
	public GameComboBoxListener(WallTabController controller, JLabel label, JComboBox comboBox, JTextField textField) {
		myController = controller;
		myLabel = label;
		myComboBox = comboBox;
		myTextField = textField;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("comboBoxChanged".equals(e.getActionCommand())) {
			String selectedGameName = WallTabView.myGameChoices[myComboBox.getSelectedIndex()];
	        myLabel.setText("Comments for " + selectedGameName + ":");
	        //myController.updateCommentsArea(selectedGameName);
	        myController.updateComments(selectedGameName);
	        myTextField.setText("");
	    }
	}
}

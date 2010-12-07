package arcade.wall;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import arcade.wall.controller.WallController;
import arcade.wall.view.WallView;

public class GameComboBoxListener implements ActionListener {
	private WallController myController;
	private JLabel myLabel;
	private JComboBox myComboBox;
	
	public GameComboBoxListener(WallController controller, JLabel label, JComboBox comboBox) {
		myController = controller;
		myLabel = label;
		myComboBox = comboBox;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("comboBoxChanged".equals(e.getActionCommand())) {
			String selectedGameName = WallView.choices[myComboBox.getSelectedIndex()];
	        myLabel.setText("Comments for " + selectedGameName + ":");
	        myController.updateCommentsArea(selectedGameName);
	    }
		
	}

}

package arcade.wall;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;

public class GameComboBoxListener implements ActionListener {

	private JLabel myLabel;
	private JComboBox myComboBox;
	
	public GameComboBoxListener(JLabel label, JComboBox comboBox) {
		myLabel = label;
		myComboBox = comboBox;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("comboBoxChanged".equals(e.getActionCommand())) {
	        myLabel.setText("Comments for " + WallView.choices[myComboBox.getSelectedIndex()] + ":");
	    }
	}

}

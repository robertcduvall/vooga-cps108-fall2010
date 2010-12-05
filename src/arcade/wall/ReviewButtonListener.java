package arcade.wall;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ReviewButtonListener implements ActionListener {

	private WallController myController;
	private JComboBox myComboBox;
	private JLabel myLabel;
	private JTextField myTextField;
	private String myGamerName;

	public ReviewButtonListener(WallController controller, JComboBox comboBox, 
								JLabel label, JTextField textField, String gamerName) {
		myController = controller;
		myComboBox = comboBox;
		myLabel = label;
		myTextField = textField;
		myGamerName = gamerName;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		myController.addComment(WallView.choices[myComboBox.getSelectedIndex()],
				myTextField.getText(), myGamerName);
		myLabel.setText("<html>" + 
				myLabel.getText() +  
				"<br/>" + 
				myTextField.getText() + "  ---" + myGamerName +
		"</html>");

	}

}

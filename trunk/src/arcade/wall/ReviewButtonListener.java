package arcade.wall;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
		List<String> comments = myController.addComment(WallView.choices[myComboBox.getSelectedIndex()],
				myTextField.getText(), myGamerName);
		String gameComments = "";
		for(String comment : comments){
			gameComments = gameComments + "\n" + comment;
		}
		//TODO - how to make every game comment display on a new line??
		myLabel.setText("<html>" + 
				myLabel.getText() +  
				"<br/>" + 
				gameComments +
				//myTextField.getText() + "  ---" + myGamerName +
		"</html>");

	}

}

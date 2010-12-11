package arcade.wall.views.walltab;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import arcade.wall.controllers.WallTabController;

public class WallTabComboBoxGroup {

	private WallTabController myController;
	private JLabel myCommentsLabel;
	private JComboBox myGameComboBox;
	private JTextField myCommentEntryField;
	
	public WallTabComboBoxGroup(WallTabController controller, JLabel commentsLabel, JComboBox gameComboBox, JTextField commentEntryField) {
		this.myController = controller;
		this.myCommentsLabel = commentsLabel;
		this.myGameComboBox = gameComboBox;
		this.myCommentEntryField = commentEntryField;
	}

	public void update() {
		String selectedGameName = WallTabView.myGameChoices[myGameComboBox.getSelectedIndex()];
        myCommentsLabel.setText("Comments for " + selectedGameName + ":");
        myController.updateComments(selectedGameName);
        myCommentEntryField.setText("");
	}
}

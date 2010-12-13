package arcade.wall.retired;
//package arcade.wall.views.walltab;
//
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JLabel;
//import javax.swing.JTextField;
//
//import arcade.wall.controllers.WallTabController;
//
//public class WallTabComboBoxGroup {
//
//	private WallTabController myController;
//	private JLabel myCommentsLabel;
//	private JButton myRatingButton;
//	private JComboBox myGameComboBox;
//	private JTextField myCommentEntryField;
//	
//	public WallTabComboBoxGroup(WallTabController controller, JLabel commentsLabel, JButton ratingButton, JComboBox gameComboBox, JTextField commentEntryField) {
//		this.myController = controller;
//		this.myCommentsLabel = commentsLabel;
//		this.myRatingButton = ratingButton;
//		this.myGameComboBox = gameComboBox;
//		this.myCommentEntryField = commentEntryField;
//	}
//
//	public void update() {
//		String selectedGameName = WallTabView.myGameChoices[myGameComboBox.getSelectedIndex()];
//        myCommentsLabel.setText("Comments for " + selectedGameName + ":");
//        myRatingButton.setText("Average Rating for " + selectedGameName + ":"+myController.getRating(selectedGameName));
//        myController.updateComments(selectedGameName);
//        myCommentEntryField.setText("");
//	}
//}

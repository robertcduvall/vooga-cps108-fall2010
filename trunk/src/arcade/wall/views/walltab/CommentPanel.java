package arcade.wall.views.walltab;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CommentPanel extends JPanel {

	private JLabel myEnterCommentLabel;
	private JTextField myCommentEntryField;
	private JButton myCommentButton;
	
	public CommentPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		myEnterCommentLabel = new JLabel(WallTabPanel.myResources.getString("enterCommentLabel"));
		myCommentEntryField = new JTextField();
		myCommentButton = new JButton("Comment");
		this.add(myEnterCommentLabel);
		this.add(myCommentEntryField);
		this.add(myCommentButton);
		this.setBorder(WallTabPanel.constructWallBorder(WallTabPanel.myResources.getString("commentPanelBorder")));
	}
	
	public String getEntryText() {
		return myCommentEntryField.getText();
	}
	
	public void setEntryText(String string) {
		myCommentEntryField.setText(string);
	}
}

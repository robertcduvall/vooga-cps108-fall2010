package arcade.wall.views.walltab;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MessagesPanel extends JPanel {
	private JButton 
	mySendMessageButton,
	myCloseButton,
	myComposeMessageButton,
	myInboxButton;
	private JComboBox myFriendsComboBox;
	private JFrame myComposeMessageFrame;
	private JLabel
	myEnterMessageLabel,
	mySendToNewUserLabel,
	mySendToFriendLabel;
	private JTextField
	myEnterReceiverField,
	myEnterMessageField,
	mySendToNewUserField;

	public MessagesPanel() {
		String[] test = {"yo"};
		myFriendsComboBox = new JComboBox(test);
		mySendMessageButton = new JButton(WallTabView.myResources.getString("sendMessageButton"));
		myCloseButton = new JButton(WallTabView.myResources.getString("closeButton"));
		myInboxButton = new JButton(WallTabView.myResources.getString("inboxButton"));
		myComposeMessageButton = new JButton(WallTabView.myResources.getString("composeMessageButton"));
		myEnterMessageLabel = new JLabel(WallTabView.myResources.getString("enterMessageLabel"));
		mySendToNewUserLabel = new JLabel(WallTabView.myResources.getString("sendToNewUserLabel"));
		mySendToFriendLabel = new JLabel(WallTabView.myResources.getString("sendToFriendLabel"));
		mySendToNewUserField = new JTextField();
		myEnterReceiverField = new JTextField();
		myEnterMessageField = new JTextField();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(WallTabView.constructWallBorder(WallTabView.myResources.getString("messagesPanelBorder")));
		this.add(myComposeMessageButton);
		this.add(myInboxButton);
		constructComposeMessageFrame();
	}

	public void setReceiverText(String string) {
		myEnterReceiverField.setText(string);
	}

	public void setMessageContentText(String string) {
		myEnterMessageField.setText(string);
	}

	public String getReceiverText() {
		return myEnterReceiverField.getText();
	}

	public String getMessageContentText() {
		return myEnterMessageField.getText();
	}

	/**
	 * Adds the SendMessageButtonListener to the WallTabView.
	 */
	public void addSendMessageButtonListener(
			ActionListener sendMessageButtonListener) {
		mySendMessageButton.addActionListener(sendMessageButtonListener);
	}

	/**
	 * Adds the ComposeMessageButtonListener to the WallTabView.
	 */
	public void addComposeMessageButtonListener(
			ActionListener composeMessageButtonListener){
		myComposeMessageButton.addActionListener(composeMessageButtonListener);
	}

	/**
	 * Adds the CloseButtonListener to the WallTabView.
	 */
	public void addCloseButtonListener(
			ActionListener closeButtonListener){
		myCloseButton.addActionListener(closeButtonListener);
	}

	/**
	 * Constructs the ComposeMessage JFrame
	 */
	private void constructComposeMessageFrame(){
		myComposeMessageFrame = new JFrame();
		JPanel composePanel = new JPanel();
		JPanel buttonPanel = new JPanel();

		composePanel.setLayout(new BoxLayout(composePanel, BoxLayout.Y_AXIS));
		composePanel.setBorder(WallTabView.constructWallBorder(WallTabView.myResources.getString("composeMessagePanelBorder")));
		composePanel.add(mySendToFriendLabel);
		composePanel.add(myFriendsComboBox);
		composePanel.add(mySendToNewUserLabel);
		composePanel.add(mySendToNewUserField);
		composePanel.add(myEnterMessageLabel);
		composePanel.add(myEnterMessageField);

		buttonPanel.add(mySendMessageButton);
		buttonPanel.add(myCloseButton);
		buttonPanel.setLayout(new FlowLayout());

		composePanel.add(buttonPanel);

		myComposeMessageFrame.add(composePanel);
		myComposeMessageFrame.pack();
	}

	public JFrame getComposeMessageFrame(){
		return myComposeMessageFrame;
	}
}

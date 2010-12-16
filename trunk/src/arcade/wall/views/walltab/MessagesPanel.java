package arcade.wall.views.walltab;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import arcade.wall.models.data.message.Message;

/**
 * This class allows users to refresh their inbox to check for new messages,
 * as well as compose messages to other users.
 * 
 * 
 * @author John Kline, Bhawana Singh, Cameron McCallie
 *
 */

@SuppressWarnings("serial")
public class MessagesPanel extends JPanel {
	private JButton 
	mySendMessageButton,
	myCloseButton,
	myComposeMessageButton,
	myRefreshInboxButton;
	private JComboBox myFriendsComboBox;
	private JFrame myComposeMessageFrame;
	private JLabel
	myEnterMessageLabel,
	mySendToNewReceiverLabel,
	mySendToFriendLabel;
	private JTextField
	myEnterReceiverField,
	myEnterMessageField;
	private JTable myInboxTable;
	private JScrollPane myInboxScrollPane;

	public MessagesPanel() {
		String[] test = {"yo"};
		myFriendsComboBox = new JComboBox(test);
		mySendMessageButton = new JButton(WallTabPanel.myResources.getString("sendMessageButton"));
		myCloseButton = new JButton(WallTabPanel.myResources.getString("closeButton"));
		myComposeMessageButton = new JButton(WallTabPanel.myResources.getString("composeMessageButton"));
		myRefreshInboxButton = new JButton(WallTabPanel.myResources.getString("refreshInboxButton"));
		myEnterMessageLabel = new JLabel(WallTabPanel.myResources.getString("enterMessageLabel"));
		mySendToNewReceiverLabel = new JLabel(WallTabPanel.myResources.getString("sendToNewReceiverLabel"));
		mySendToFriendLabel = new JLabel(WallTabPanel.myResources.getString("sendToFriendLabel"));
		myEnterReceiverField = new JTextField();
		myEnterMessageField = new JTextField();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(WallTabPanel.constructWallBorder(WallTabPanel.myResources.getString("messagesPanelBorder")));
		this.add(myComposeMessageButton);
		this.add(myRefreshInboxButton);
		constructComposeMessageFrame();
		myInboxTable = new JTable();
		myInboxScrollPane = new JScrollPane(myInboxTable);
		this.add(myInboxScrollPane);
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
	 * Adds the SendMessageButtonListener to the MessagesPanel.
	 */
	public void addSendMessageButtonListener(
			ActionListener sendMessageButtonListener) {
		mySendMessageButton.addActionListener(sendMessageButtonListener);
	}

	/**
	 * Adds the ComposeMessageButtonListener to the MessagesPanel.
	 */
	public void addComposeMessageButtonListener(
			ActionListener composeMessageButtonListener){
		myComposeMessageButton.addActionListener(composeMessageButtonListener);
	}

	/**
	 * Adds the CloseButtonListener to the MessagesPanel.
	 */
	public void addCloseButtonListener(
			ActionListener closeButtonListener){
		myCloseButton.addActionListener(closeButtonListener);
	}
	
	/**
	 * Adds the RefreshInboxButtonListener to the MessagesPanel.
	 */
	public void addRefreshInboxButtonListener(
			ActionListener refreshInboxButtonListener){
		myRefreshInboxButton.addActionListener(refreshInboxButtonListener);
	}

	/**
	 * Constructs the ComposeMessage JFrame
	 */
	private void constructComposeMessageFrame(){
		myComposeMessageFrame = new JFrame();
		JPanel composePanel = new JPanel();
		JPanel buttonPanel = new JPanel();

		composePanel.setLayout(new BoxLayout(composePanel, BoxLayout.Y_AXIS));
		composePanel.setBorder(WallTabPanel.constructWallBorder(WallTabPanel.myResources.getString("composeMessagePanelBorder")));
		composePanel.add(mySendToFriendLabel);
		composePanel.add(myFriendsComboBox);
		composePanel.add(mySendToNewReceiverLabel);
		composePanel.add(myEnterReceiverField);
		composePanel.add(myEnterMessageLabel);
		composePanel.add(myEnterMessageField);

		buttonPanel.add(mySendMessageButton);
		buttonPanel.add(myCloseButton);
		buttonPanel.setLayout(new FlowLayout());

		composePanel.add(buttonPanel);

		myComposeMessageFrame.add(composePanel);
		myComposeMessageFrame.pack();
	}
	
	class InboxTableModel extends AbstractTableModel {
        private String[] myColumnNames;
        private Object[][] myData;

        public InboxTableModel(String[] columnNames, Object[][] data) {
        	myColumnNames = columnNames;
        	myData = data;
        }
        
        public int getColumnCount() {
            return myColumnNames.length;
        }

        public int getRowCount() {
            return myData.length;
        }

        public String getColumnName(int col) {
            return myColumnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return myData[row][col];
        }
	}

	public JFrame getComposeMessageFrame(){
		return myComposeMessageFrame;
	}

	public void refreshInbox(List<Message> messageList) {
		Object[][] data = new Object[messageList.size()][2];
		for (int i = 0; i < messageList.size(); i++) {
			Message m = messageList.get(i);
			data[i][0] = m.getSender();
			data[i][1] = m.getContent();
		}
		String[] columnNames = {"From", "Message"};
		remove(myInboxScrollPane);
		myInboxTable = new JTable(new InboxTableModel(columnNames, data));
		myInboxTable.setPreferredScrollableViewportSize(new Dimension(100, 100)); // This should match the panel it goes in
        myInboxTable.setFillsViewportHeight(true);
//        myInboxTable.setAutoResizeMode (JTable.AUTO_RESIZE_ALL_COLUMNS);
        myInboxScrollPane = new JScrollPane(myInboxTable);
//        scrollPane.setHorizontalScrollBar(new JScrollBar());
        add(myInboxScrollPane);
	}
}

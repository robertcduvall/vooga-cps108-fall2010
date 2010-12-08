//package arcade.wall.retired;
//
//import java.awt.BorderLayout;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//
//import javax.swing.BorderFactory;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//
//import arcade.wall.controller.WallController;
//
//public class WallDemo extends JPanel {
//	WallController controller;
//	static JFrame frame;
//
//	JPanel entryPanel;
//	JPanel displayPanel;
//
//	JButton reviewButton;
//	JLabel commentsLabel;
//
//	static JTextField commentEntryField;
//	JComboBox gameChoices;
//	
//	public static final String myName = "gamer13";
//	public static final String[] choices = { "Grandius", "Zombieland", "Jumper", 
//			"Doodlejump", "Galaxy Invaders", "Cyberion", 
//			"Tron", "MarioClone" };
//
//	public WallDemo() {
//		controller = new WallController();
//		entryPanel = new JPanel();
//		displayPanel = new JPanel();
//
//		reviewButton = new JButton("Review");
//		reviewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				controller.addComment(choices[gameChoices.getSelectedIndex()],
//									  commentEntryField.getText(), myName);
//				commentsLabel.setText("<html>" + 
//									  commentsLabel.getText() +  
//									  "<br/>" + 
//									  commentEntryField.getText() + "  ---" + myName +
//									  "</html>");
//			}
//		});
//		commentEntryField = new JTextField(17);
//		gameChoices = new JComboBox();
//		gameChoices = new JComboBox(choices);
//		gameChoices.setSelectedIndex(0);
//		commentsLabel = new JLabel("Comments for " + choices[gameChoices.getSelectedIndex()] + ":");
//		gameChoices.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent event) {
//			    if ("comboBoxChanged".equals(event.getActionCommand())) {
//			        commentsLabel.setText("Comments for " + choices[gameChoices.getSelectedIndex()] + ":");
//			    }
//			}
//		});
//		
//		entryPanel.add(gameChoices);
//		entryPanel.add(commentEntryField);
//		entryPanel.add(reviewButton);
//		//entryPanel.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//		displayPanel.add(commentsLabel);
//
//		this.setLayout(new GridLayout(2,2,5,5));
//		//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//		this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
//		this.add(entryPanel);
//		this.add(displayPanel);
//	}
//
//	public static JTextField getCommentEntryField() {
//		return commentEntryField;
//	}
//
//	public static void main(String s[]) {
//		frame = new JFrame("WallDemo");
//		frame.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {System.exit(0);}
//		});
//
//		frame.getContentPane().add(new WallDemo(), BorderLayout.CENTER);
//		frame.pack();
//		frame.setVisible(true);
//	}
//}

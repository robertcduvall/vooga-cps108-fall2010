package vooga.examples.networking.tictactoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import vooga.engine.networking.client.ChatConnection;

public class TicTacToeFrame extends JFrame implements Runnable{
	private JTextPane chats;
	private JTextField chat;
	private DefaultStyledDocument doc;
	private Style meStyle;
	private Style messageStyle;
	private Style opponentStyle;
	private ChatConnection connection;

	public TicTacToeFrame(ChatConnection connection){
		this.connection = connection;
		initStyles();
		initChatFields();
		initDimensions();
	    Thread listener = new Thread(this);
	    listener.start();
	}
	
	public void initChatFields(){
		chats = new JTextPane(doc);
		chats.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(chats);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		chat = new JTextField(1);
		chat.requestFocus();
		chat.addActionListener(new ChatHandler());
		getContentPane().add(chat, BorderLayout.SOUTH);
	}
	
	public void initDimensions(){
		ResourceBundle gameDimensions = ResourceBundle.getBundle("vooga/examples/networking/tictactoe/resources/config");
		setSize(Integer.parseInt(gameDimensions.getString("GAME_WIDTH")), Integer.parseInt(gameDimensions.getString("GAME_HEIGHT")) / 3);
		this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 + getWidth() / 2, 
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 + Integer.parseInt(gameDimensions.getString("GAME_HEIGHT")) / 2 - getHeight());
	}
	
	public void initStyles(){
		StyleContext sc = new StyleContext();
		doc = new DefaultStyledDocument(sc);
		
		Style style1 = sc.getStyle(StyleContext.DEFAULT_STYLE);
		meStyle = sc.addStyle("MeStyle", style1);
		StyleConstants.setFontFamily(meStyle, "arial");
		StyleConstants.setFontSize(meStyle, 12);
		StyleConstants.setBold(meStyle, true);
		StyleConstants.setForeground(meStyle, Color.RED);

		Style style2 = sc.getStyle(StyleContext.DEFAULT_STYLE);
		messageStyle = sc.addStyle("MessageStyle", style2);
		StyleConstants.setFontFamily(messageStyle, "serif");
		StyleConstants.setFontSize(messageStyle, 14);
		StyleConstants.setBold(messageStyle, false);
		StyleConstants.setForeground(messageStyle, Color.BLACK);
		
		Style style3 = sc.getStyle(StyleContext.DEFAULT_STYLE);
		opponentStyle = sc.addStyle("YouStyle", style3);
		StyleConstants.setFontFamily(opponentStyle, "arial");
		StyleConstants.setFontSize(opponentStyle, 12);
		StyleConstants.setBold(opponentStyle, true);
		StyleConstants.setForeground(opponentStyle, Color.BLUE);
	}

	@Override
	public void run(){
		while(true){
			String chat = connection.getChat();
			try {
				doc.insertString(doc.getLength(), "opponent: ", opponentStyle);
				doc.insertString(doc.getLength(), chat + "\n", messageStyle);
			} catch (BadLocationException e) {
				System.exit(1);
			}
			chats.setCaretPosition(chats.getDocument().getLength());
		}
	}

	private class ChatHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String text = chat.getText();
			if(text.length() > 0){
				try {
					doc.insertString(doc.getLength(), "me: ", meStyle);
					connection.sendChat(text);
					doc.insertString(doc.getLength(), text + "\n", messageStyle);
					chat.setText("");
					chats.setCaretPosition(chats.getDocument().getLength());
				}
				catch(Exception ex){
					System.out.println(ex);
					System.out.println("Unhandled chat exception");
					System.exit(1);
				}
			}
		}
	}

	public static void main(String[] args){
		ChatConnection connection = null;
		try{
			connection = new ChatConnection();
		}
		catch(Exception e){
			System.out.println("TicTacToe Error: "+ e.getMessage());
			System.exit(1);
		}
		TicTacToeFrame frame = new TicTacToeFrame(connection);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setUndecorated(true);
		frame.setVisible(true);
		TicTacToe.run();
	}
}

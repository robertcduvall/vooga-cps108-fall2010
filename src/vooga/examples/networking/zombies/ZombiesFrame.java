package vooga.examples.networking.zombies;

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

import vooga.engine.networking.client.ChatClientConnection;

/**
 * Class with the main method that runs the TicTacToe Game.  Runs the Vooga game with a chat box that can be used to chat between players in the Game.
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ZombiesFrame extends JFrame implements Runnable{
	private JTextPane chats;
	private JTextField chat;
	private DefaultStyledDocument doc;
	private Style meStyle;
	private Style messageStyle;
	private Style opponentStyle;
	private Style statusStyle;
	private ChatClientConnection connection;

	/**
	 * Create chat GUI and listen for incoming chats.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public ZombiesFrame(ChatClientConnection connection){
		this.connection = connection;
		initStyles();
		initChatFields();
		initDimensions();
	    Thread listener = new Thread(this);
	    listener.start();
	}
	
	/**
	 * Create the chats panel and the chat text field.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void initChatFields(){
		chats = new JTextPane(doc);
		chats.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(chats);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		chat = new JTextField(1);
		chat.requestFocus();
		chat.addActionListener(new ChatListener());
		getContentPane().add(chat, BorderLayout.SOUTH);
	}
	
	/**
	 * Size the chat field to be to the bottom right of the TicTacToe Game.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void initDimensions(){
		ResourceBundle gameDimensions = ResourceBundle.getBundle("vooga/examples/networking/tictactoe/resources/config");
		setSize(Integer.parseInt(gameDimensions.getString("GAME_WIDTH")), Integer.parseInt(gameDimensions.getString("GAME_HEIGHT")) / 3);
		this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 + getWidth() / 2, 
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 + Integer.parseInt(gameDimensions.getString("GAME_HEIGHT")) / 2 - getHeight());
	}
	
	/**
	 * Make the font styles for the 'me' chat label, the 'opponent' chat label, and the chats themselves.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
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
		opponentStyle = sc.addStyle("OpponentStyle", style3);
		StyleConstants.setFontFamily(opponentStyle, "arial");
		StyleConstants.setFontSize(opponentStyle, 12);
		StyleConstants.setBold(opponentStyle, true);
		StyleConstants.setForeground(opponentStyle, Color.BLUE);
		
		Style style4 = sc.getStyle(StyleContext.DEFAULT_STYLE);
		statusStyle = sc.addStyle("StatusStyle", style4);
		StyleConstants.setFontFamily(statusStyle, "serif");
		StyleConstants.setFontSize(statusStyle, 11);
		StyleConstants.setItalic(statusStyle, true);
		StyleConstants.setForeground(statusStyle, Color.DARK_GRAY);
	}

	/**
	 * Thread to listen for the incoming chats. When it receives a chat it prints it to the chat panel and then scrolls the panel to the bottom.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	@Override
	public void run(){
		while(true){
			String chat = connection.getChat();
			try {
				if(chat.startsWith("ADMIN:")){
					doc.insertString(doc.getLength(), chat.substring(6) + "\n", statusStyle);
				}
				else{
					String name = chat.substring(0, chat.indexOf(":") + 1);
					String message = chat.substring(chat.indexOf(":") + 1);
					doc.insertString(doc.getLength(), name, opponentStyle);
					doc.insertString(doc.getLength(), message + "\n", messageStyle);
				}
			} catch (BadLocationException e) {
				System.exit(1);
			}
			chats.setCaretPosition(chats.getDocument().getLength());
		}
	}

	/**
	 * Listener for when the user presses enter in the chat text field. When they do then print the text the chat panel, send the text over
	 * the ChatConnection, reset the text field, and scroll the chat panel to the bottom.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	private class ChatListener implements ActionListener {
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

	/**
	 * Creates an instance of ChatConnection, passes it to a TicTacToeFrame, and then runs the TicTacToe Game.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public static void main(String[] args){
		ChatClientConnection connection = null;
		try{
			connection = new ChatClientConnection("Zombies");
		}
		catch(Exception e){
			System.out.println("TicTacToe Error: "+ e.getMessage());
			System.exit(1);
		}
		ZombiesFrame frame = new ZombiesFrame(connection);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setUndecorated(true);
		frame.setVisible(true);
		Blah.run();
	}
}

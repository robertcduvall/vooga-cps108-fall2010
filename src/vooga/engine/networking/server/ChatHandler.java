package vooga.engine.networking.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vooga.engine.networking.GameSocket;

/**
 * Class that handles communicating through chats over the network.  
 * There is one instance of ChatHandler for each time the game is 
 * run and a static list of the current running ChatHandlers.  ChatHandler 
 * extends the Handler class so it also extends Thread and has a socket 
 * to send and receive chats through as well as a session ID to group 
 * ChatHandlers that are part of the same game session.
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
public class ChatHandler extends Handler{
	protected static List<ChatHandler> handlers = new ArrayList<ChatHandler>();
	private String gameName;

	/**
	 * Initializes ChatHandler with a socket to send and receive chats 
	 * through and the chat's game session ID.
	 * 
	 * @param socket the socket to send and receive chats through
	 * @param sessionID the ID of the chat's game session
	 * @param gameName the name of the game that the chat is a part of
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public ChatHandler(GameSocket socket, int sessionID, String gameName){
		super(socket, sessionID);
		this.gameName = gameName;
	}

	/**
	 * When the thread starts, add this handler to the game's list of ChatHandlers, then keep looking for messages
	 * to read from the socket and send out to all the players.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void run(){
		try {
			handlers.add(this);
			getSocket().send("ADMIN: Welcome to " + gameName + "!");
			broadcast("ADMIN: Player joined the game.", this);
			while (true) {
				String chat = socket.receive();
				if(chat == null){
					broadcast("ADMIN: Opponent left the chat", this);
					return;
				}
				broadcast("opponent: " + chat, this);
			}
		} 
		catch (IOException ex) {
			handlers.remove(this);
			socket.closeConnections();
		}
		finally {
			handlers.remove(this);
			socket.closeConnections();
		}
	}

	/**
	 * Broadcast (send) the chat to everyone connected to this handler's game session besIDes the sender.
	 * 
	 * @param chat the chat to send to the other players
	 * @param sender the handler who is sending the chat
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	protected static void broadcast (String chat, ChatHandler sender) {
		synchronized (handlers) {
			for(ChatHandler handler : handlers){
				if(handler.getSessionID() == sender.getSessionID() && sender != handler)
					handler.getSocket().send(chat);
			}
		}
	}
}
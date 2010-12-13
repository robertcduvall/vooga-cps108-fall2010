package vooga.engine.networking.client;

import java.io.*;
import java.net.*;

import vooga.engine.networking.GameSocket;
import vooga.engine.networking.server.VoogaServer;

/**
 * Class for connecting the client to the server chat socket.
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
public class ChatClientConnection extends ClientConnection {

	/**
	 * Calls GameSocket's constructor with the server's IP address and the port that holds the chat.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public ChatClientConnection(String name) throws UnknownHostException, IOException{
		super(name);
	}

	
	/**
	 * 
	 * This method only calls "getData" but has a more descriptive 
	 * and specific name to make code more readable and intuitive.
	 * 
	 * @return superclass ClientConnection method getData().
	 */
	public String getChat() {
		return getData();
	}

	/**
	 * Sends the chat over the network. Passes parameter to
	 * the "sendString" method of superclass.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void sendChat(String chat) {
		sendString(chat);
	}

	@Override
	protected String interpretData(String data) {
		return data;
	}
}

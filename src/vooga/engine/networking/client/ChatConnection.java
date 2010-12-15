package vooga.engine.networking.client;

import java.io.*;
import java.net.*;
import vooga.engine.networking.server.VoogaServer;

/**
 * Class for connecting the client to the server chat socket.
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
public class ChatConnection extends Connection {
	
	/**
	 * Calls GameSocket's constructor with the port that holds the chat.
	 * 
	 * @param the String name of the game
	 * @param the String username of the client 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	
	public ChatConnection(String gameName, String userName) throws UnknownHostException, IOException{
		super(VoogaServer.getChatPort(gameName));
		sendData(userName);
	}
	
	/**
	 * No interpretation or String-checking needed for chat.  Sends the raw string over the network.
	 * Could be extended to check for profanity.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	@Override
	public String interpretData(String data) {
		return data;
	}
}

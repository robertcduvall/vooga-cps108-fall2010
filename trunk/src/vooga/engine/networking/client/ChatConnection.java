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
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public ChatConnection(String name) throws UnknownHostException, IOException{
		super(VoogaServer.getChatPort(name));
	}
	
	/**
	 * For chat all we want to do is send the raw string over the network. No checking needed.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	@Override
	public String interpretData(String data) {
		return data;
	}
}

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
public class ChatConnection extends GameSocket {

	/**
	 * Calls GameSocket's constructor with the server's IP address and the port that holds the chat.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public ChatConnection(String name) throws UnknownHostException, IOException{
		super(new Socket("localhost", VoogaServer.getChatPort(name)));
	}

	/**
	 * Returns the latest chat from the socket.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public String getChat() {
		if (!isConnected()) 
			throw new NullPointerException("Attempted to read closed socket!");

		try {
			String sentData = receive();
			return sentData;
		}
		catch (IOException e) {
			System.out.println("I/O Error: " + e);
			System.exit(1);
			return "";
		}
	}

	/**
	 * Sends the chat over the network.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void sendChat(String chat) {
		send(chat);
	}
}

package vooga.engine.networking.client;

import java.io.IOException;
import java.net.UnknownHostException;
import vooga.engine.networking.server.VoogaServer;

/**
 * Basic class for the client to connect to the server and send/receive 
 * data. Works for basic games but can be extended to add more complicated 
 * functionality.  Override the interpretData() method to deal with 
 * receiving messages from the socket and the sendData() method 
 * for sending messages to the socket.
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
public class ClientConnection extends Connection{
	
	/**
	 * Calls GameSocket constructor with the port.  The port is based on the name of the game 
	 * since each game has a different port that it operates on.
	 * 
	 * @param name the name of the game you're trying to connect to and from
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public ClientConnection(String name, String userName) throws UnknownHostException, IOException{
		super(VoogaServer.getGamePort(name));
		sendData(userName);
	}

	/**
	 * If the data is null then return gameOver otherwise just return the data String.
	 * 
	 * @return data that was sent over the network
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public String interpretData(String data){
		if (data == null)
			return "gameOver";
		data = data.trim();
		return data;
	}
	
	/**
	 * Tell the server that the game is over.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void sendGameOver(){
		send("gameOver");
	}
}

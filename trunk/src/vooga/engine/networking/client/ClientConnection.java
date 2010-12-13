package vooga.engine.networking.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import vooga.engine.networking.GameSocket;
import vooga.engine.networking.server.VoogaServer;
import vooga.engine.resource.Resources;

/**
 * Basic class for connecting to the server from the client and sending 
 * and receiving data. Works for basic games but can be extended to add 
 * more complicated functionality.  Override the getData() method to 
 * deal with receiving messages from the socket and the sendData() method 
 * for sending messages to the socket.
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
public abstract class ClientConnection extends GameSocket{
	protected String hostServer = "localhost";
	
	/**
	 * Calls GameSocket constructor with the server's IP address and the 
	 * port.  The port is based on the name of the game since each game 
	 * has a different port that it operates on.
	 * 
	 * @param name the name of the game you're trying to connect to and from
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public ClientConnection(String name) throws UnknownHostException, IOException{
		//TODO: shouldnt call Resources to get the hostServer String.
		super(new Socket((Resources.getString("hostServer")), VoogaServer.getGamePort(name)));
	}

	/**
	 * Listen for incoming data for the game. If the data is null then return 
	 * gameOver otherwise just return the data String.
	 * 
	 * @return data that was sent over the network
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public String getData() {
		if (!isConnected()) 
			throw new NullPointerException("Attempted to read closed socket!");

		try {
			String sentData = receive();
			return interpretData(sentData);
		}
		catch (IOException e) {
			System.out.println("I/O Error: " + e);
			System.exit(1);
			return null;
		}
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	protected abstract String interpretData(String data);

	/**
	 * Send a Serializeable object over the network.  Calls the Serializeable 
	 * object's serialize method to convert it to a String to send to the socket.
	 * 
	 * @param data the Serializeable object sent over the network
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */

	/**
	 * Calls the method serialize on the parameter and
	 * calls "send" method of superclass to send to server.
	 * @param data of type Serializable to be sent to server.
	 */
	/*
	 * Options: have 2 methods called sendData: one that takes 
	 * type Serializable and one that takes Strings.  Or could name one
	 * sendSerializable and keep the other as send data.  Actually, will
	 * delete repetitive method sendData.
	 */
	public void sendSerializable(Serializeable data){
		send(data.serialize());
	}
	
	/**
	 * This is just for convenience.  Passes parameter to superClass
	 * "send" method.
	 * @param string
	 */
	public void sendString(String string) {
		send(string);
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

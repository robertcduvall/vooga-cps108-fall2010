package vooga.engine.networking.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import vooga.engine.networking.GameSocket;

public abstract class Connection extends GameSocket{
	/**
	 * Calls GameSocket's constructor with the server's IP address and the specified port.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public Connection(int port) throws UnknownHostException, IOException{
		super(new Socket("localhost", port));
	}
	
	/**
	 * Returns the latest piece of data from the socket.
	 * 
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
			return "";
		}
	}
	
	/**
	 * Overridden by subclasses to specify what to do with the String received from the socket.
	 * 
	 * @param data the String read from the socket
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public abstract String interpretData(String data);
	
	/**
	 * Send a Serializeable object over the network.  Calls the Serializeable 
	 * object's serialize method to convert it to a String to send to the socket.
	 * 
	 * @param data the Serializeable object sent over the network
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void sendData(Serializeable data){
		send(data.serialize());
	}


	/**
	 * Sends the String over the network.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void sendData(String data) {
		send(data);
	}

}

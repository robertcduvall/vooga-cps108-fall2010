package vooga.engine.networking.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import vooga.engine.networking.GameSocket;
import vooga.engine.resource.Resources;

public abstract class NetworkConnection extends GameSocket {
	
	public NetworkConnection() throws UnknownHostException, IOException{
		super(new Socket("localhost", Resources.getInt("portNum")));
	}
	

	public int getData() {
		if (!isConnected()) 
			throw new NullPointerException("Attempted to read closed socket!");

		try {
			String receivedData = receive();
			System.out.println("Received: " + receivedData);
			if (receivedData == null)
				return Resources.getInt("gameOver");
			receivedData = receivedData.trim();
			try {
				return new Integer(receivedData).intValue();
			}
			catch (NumberFormatException e) {
				return statusStringToInt(receivedData);
			}
		}
		catch (IOException e) {
			System.out.println("I/O Error: " + e);
			System.exit(1);
			return 0;
		}
	}

	private int statusStringToInt(String s) {
		return Resources.getInt(s.trim());
	}

	public void sendData(int col) {
		send(Integer.toString(col));
	}


	public void sendData(String toSendToServer) {
		send(toSendToServer);
	}

	public void sendIQUIT() {
		send("IQUIT");
	}
	
	public void sendGAMEOVER(){
		send("GAMEOVER");
	}
}


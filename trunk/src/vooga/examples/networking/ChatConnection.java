package vooga.examples.networking;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import vooga.engine.networking.GameSocket;
import vooga.engine.resource.Resources;

public class ChatConnection extends GameSocket {

	public ChatConnection() throws UnknownHostException, IOException {
		super(new Socket("localhost", 1234));
	}

	public void sendMessage(String col) {
		send(col);
	}

	public String getTheirMessage() {
		if (!isConnected())
			throw new NullPointerException("Attempted to read closed socket!");

		try {
			String sentData = receive();
			System.out.println("Received: " + sentData);
			if (sentData == null) {
				return "NothingRecieved :(";
			}

			return sentData;
		} catch (IOException e) {
			System.out.println("I/O Error: " + e);
			System.exit(1);
			return "errorz!!";
		}
	}

}

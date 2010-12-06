package vooga.examples.networking.tictactoe;

import java.io.*;
import java.net.*;

import vooga.engine.networking.client.ClientConnection;

public class TicTacToeConnection extends ClientConnection {

	public TicTacToeConnection() throws UnknownHostException, IOException {
		super();
	}

	public String getData() {
		if (!isConnected()) 
			throw new NullPointerException("Attempted to read closed socket!");

		try {
			String sentData = receive();
			System.out.println("Received: " + sentData);
			if (sentData == null)
				return "gameOver";
			sentData = sentData.trim();
			return sentData;
		}
		catch (IOException e) {
			System.out.println("I/O Error: " + e);
			System.exit(1);
			return null;
		}
	}

	public void sendIQUIT() {
		send("IQUIT");
	}

	public void sendGAMEOVER(){
		send("GAMEOVER");
	}
}

package vooga.engine.networking.client;

import java.io.*;
import java.net.*;

import vooga.engine.networking.GameSocket;

public class ChatConnection extends GameSocket {

	public ChatConnection() throws UnknownHostException, IOException{
		super(new Socket("localhost", 1235));
	}


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

	public void sendChat(String chat) {
		send(chat);
	}
}

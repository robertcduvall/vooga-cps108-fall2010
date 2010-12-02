package vooga.engine.networking.client;

import java.io.*;
import java.net.*;

import vooga.engine.networking.GameSocket;

public class TicTacToeConnection extends GameSocket {
	static final int PORTNUM = 1234;
	static final int ERROR = -1;
	static final int PLSWAIT = -2;
	static final int YOURTURN = -3;
	static final int THEIRTURN = -4;
	static final int THEYWON = -5;
	static final int THEYQUIT = -6;
	static final int THEYTIED = -7;
	static final int GAMEOVER = -8;
	
	public TicTacToeConnection() throws UnknownHostException, IOException{
		super(new Socket("localhost", PORTNUM));
	}
	

	public int getTheirMove() {
		if (!isConnected()) 
			throw new NullPointerException("Attempted to read closed socket!");

		try {
			String sentData = receive();
			System.out.println("Received: " + sentData);
			if (sentData == null)
				return GAMEOVER;
			sentData = sentData.trim();
			try {
				return (new Integer(sentData)).intValue();
			}
			catch (NumberFormatException e) {
				return getStatus(sentData);
			}
		}
		catch (IOException e) {
			System.out.println("I/O Error: " + e);
			System.exit(1);
			return 0;
		}
	}

	private int getStatus(String s) {
		s = s.trim();
		if (s.startsWith("PLSWAIT"))
			return PLSWAIT;
		if (s.startsWith("THEIRTURN"))
			return THEIRTURN;
		if (s.startsWith("YOURTURN"))
			return YOURTURN;
		if (s.startsWith("THEYWON"))
			return THEYWON;
		if (s.startsWith("THEYQUIT"))
			return THEYQUIT;
		if (s.startsWith("THEYTIED"))
			return THEYTIED;
		if (s.startsWith("GAMEOVER"))
			return GAMEOVER;
		
		System.out.println("received invalid status from server: " + s);
		return ERROR;
	}

	public void sendMove(int col) {
		send(Integer.toString(col));
	}

	public void sendIQUIT() {
		send("IQUIT");
	}

	public void sendIWON() {
		send("IWON");
	}

	public void sendITIED() {
		send("ITIED");
	}
}


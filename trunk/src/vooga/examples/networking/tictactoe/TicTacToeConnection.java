package vooga.examples.networking.tictactoe;

import java.io.*;
import java.net.*;

import vooga.engine.networking.GameSocket;
import vooga.engine.resource.Resources;

public class TicTacToeConnection extends GameSocket {

	public TicTacToeConnection() throws UnknownHostException, IOException{
		super(new Socket("localhost", Resources.getInt("portNum")));
	}


	public int getData() {
		if (!isConnected()) 
			throw new NullPointerException("Attempted to read closed socket!");

		try {
			String sentData = receive();
			System.out.println("Received: " + sentData);
			if (sentData == null)
				return Resources.getInt("gameOver");
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
		if (s.startsWith("wait"))
			return Resources.getInt("wait");
		if (s.startsWith("theirTurn"))
			return Resources.getInt("theirTurn");
		if (s.startsWith("yourTurn"))
			return Resources.getInt("yourTurn");
		if (s.startsWith("theyWon"))
			return Resources.getInt("theyWon");
		if (s.startsWith("theyQuit"))
			return Resources.getInt("theyQuit");
		if (s.startsWith("theyTied"))
			return Resources.getInt("theyTied");
		if (s.startsWith("gameOver"))
			return Resources.getInt("gameOver");

		System.out.println("received invalid status from server: " + s);
		return Resources.getInt("error");
	}

	public void sendMove(int col) {
		send(Integer.toString(col));
	}

	public void sendIQUIT() {
		send("IQUIT");
	}

	public void sendGAMEOVER(){
		send("GAMEOVER");
	}
}

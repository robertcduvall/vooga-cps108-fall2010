package vooga.examples.networking.tictactoe;

import java.io.*;
import java.net.*;

import vooga.engine.networking.GameSocket;
import vooga.engine.networking.client.NetworkConnection;
import vooga.engine.resource.Resources;

public class TicTacToeConnection extends NetworkConnection {
	
	public TicTacToeConnection() throws UnknownHostException, IOException {
		super();
	}
	

	/*
	private int statStringToInt(String s) {
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
	*/

	public void sendMove(int col) {
		sendData(col);
	}

	public void sendIQUIT() {
		send("IQUIT");
	}
	
	public void sendGAMEOVER(){
		send("GAMEOVER");
	}
}


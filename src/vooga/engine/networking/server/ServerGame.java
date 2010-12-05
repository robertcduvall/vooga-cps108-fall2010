package vooga.engine.networking.server;


import java.io.*;
import java.util.*;

import java.io.*;
import java.util.*;

public class ServerGame {
	public static final int ERROR = -1;
	public static final int GAMEOVER = -2;
	public static final int IQUIT = -3;
	public static final int YOURTURN = -4;
	public static final int SENTSTRING = -5;
	private TicTacToePlayer player1;
	private TicTacToePlayer player2;
	private List<Integer> p1MessageQueue;
	private List<Integer> p2MessageQueue;
	private String sentString;

	public ServerGame(TicTacToePlayer p1, TicTacToePlayer p2) {
		player1 = p1;
		player2 = p2;
		p1MessageQueue = new ArrayList<Integer>();
		p2MessageQueue = new ArrayList<Integer>();
	}

	public void playGame(TicTacToePlayer currentPlayer) {
		String message;
		boolean playGame = true;
		boolean theirTurn = false;
		try {
			if (currentPlayer == player2)
				theirTurn = true;
			else if (currentPlayer != player1){
				System.out.println("Illegal call to playGame!");
				return;
			}
			while (playGame){
				if (!theirTurn){
					currentPlayer.send("yourTurn");
					message = currentPlayer.receive();
					if(message == null){
						sendStatus(currentPlayer, IQUIT);
						playGame = false;
					}
					else{
						message = message.toUpperCase();
						message = message.trim();
						if (message.startsWith("IQUIT")){
							sendStatus(currentPlayer, IQUIT);
							playGame = false;
						}
						else if(message.startsWith("GAMEOVER")){
							sendStatus(currentPlayer, GAMEOVER);
							playGame = false;
						}
						else {
							sentString = message;
							sendStatus(currentPlayer, SENTSTRING);
						}
					}
				}
				else {
					theirTurn = false;
				}
				if (playGame){
					currentPlayer.send("theirTurn");
					int stat = getStatus(currentPlayer);
					if (stat == IQUIT) {
						currentPlayer.send("theyQuit");
						playGame = false;
					}
					else if(stat == GAMEOVER){
						playGame = false;
					}
					else if (stat == SENTSTRING) {
						currentPlayer.send(sentString);
					}
					else if (stat == ERROR) {
						currentPlayer.send("error");
						currentPlayer.closeConnections();
						playGame = false;
					}
					else {
						System.out.println("Received Bad Status");
						sendStatus(currentPlayer,ERROR);
						currentPlayer.closeConnections();
						playGame = false;
					}
				}
			}
			currentPlayer.closeConnections();
			return;
		}
		catch (IOException e) {
			System.out.println("I/O Error: " + e);
			System.exit(1);
		}
	}

	private synchronized int getStatus(TicTacToePlayer me) {
		List<Integer> ourMessages = ((me == player1) ? p1MessageQueue : p2MessageQueue);
		while (ourMessages.isEmpty()) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				System.out.println("Error: " + e);
			}
		}
		try {
			Integer retval = ourMessages.get(0);
			try {
				ourMessages.remove(0);
			}
			catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Array index out of bounds: " + e);
				System.exit(1);
			}
			return retval.intValue();
		}
		catch (NoSuchElementException e) {
			System.out.println("Couldn't get first element: " + e);
			System.exit(1);
			return 0;
		}
	}

	private synchronized void sendStatus(TicTacToePlayer me, int message) {
		List<Integer> theirMessage = ((me == player1) ?  p2MessageQueue : p1MessageQueue);
		theirMessage.add(new Integer(message));
		notify();
	}
}

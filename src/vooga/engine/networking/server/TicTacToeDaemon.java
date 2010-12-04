package vooga.engine.networking.server;
import java.net.*;
import java.io.*;

public class TicTacToeDaemon extends Thread {
	public static final int PORTNUM = 1234;
	private ServerSocket port;
	private TicTacToePlayer playerWaiting;
	private Game thisGame;

	public TicTacToeDaemon() {
		try {
			port = new ServerSocket(PORTNUM);
		}
		catch (IOException e) {
			System.out.println("Couldn't access port " + PORTNUM + ": " + e);
			System.exit(1);
		}
	}

	public void run() {
		Socket clientSocket;
		while (true) {
			if (port == null) {
				System.out.println("Sorry, the port disappeared.");
				System.exit(1);
			}
			try {
				clientSocket = port.accept();
				new TicTacToePlayer(this, clientSocket).start();
			}
			catch(SocketTimeoutException s)
			{
				System.out.println("Socket timed out!");
				break;
			}
			catch (IOException e) {
				System.out.println("Couldn't connect player: " + e);
				System.exit(1);
			}
		}
	}

	public synchronized Game waitForGame(TicTacToePlayer p) {
		Game retval = null;
		if(playerWaiting == null){
			playerWaiting = p;
			thisGame = null;
			p.send("wait");
			while (playerWaiting != null) {
				try {
					wait();
				}
				catch (InterruptedException e) {
					System.out.println("Error: " + e);
				}
			}
			return thisGame;
		}
		else{
			thisGame = new Game(playerWaiting, p);
			retval = thisGame;
			playerWaiting = null;
			notify();
			return retval;
		}
	} 

	protected void finalize() {
		if (port != null) {
			try { 
				port.close(); 
			}
			catch (IOException e) {
				System.out.println("Error closing port: " + e);
			}
			port = null;
		}
	}
}
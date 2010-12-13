package vooga.engine.networking.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import vooga.engine.networking.GameSocket;

/**
 * Thread that is run on the server 24/7 for each game using the networking API.  
 * It creates the sockets that the game will be communicating over and 
 * initiates the ChatHandler and ClientHandler subclass for every connection.
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
public class VoogaDaemon extends Thread{
	public int numberOfGames;
	public int gamePortNumber;
	public int chatPortNumber;
	private ServerSocket gamePort;
	private ServerSocket chatPort;
	private int numberOfPlayers;
	private String clientHandler;
	private String gameName;

	/**
	 * Static method to return the XML document with the list of games 
	 * that can be run on the networking Vooga servers.
	 * 
	 * @param gameName the name of the game that is being run
	 * @param gamePortNumber the port through which to send messages related to the game
	 * @param chatPortNumber the port through which to send chats
	 * @param numberOfPlayers the number of players necessary for the game
	 * @param clientHandler the name of the subclass of ClientHandler
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public VoogaDaemon(String gameName, int gamePortNumber, int chatPortNumber, int numberOfPlayers, String clientHandler) {
		try {
			this.gameName = gameName;
			this.gamePortNumber = gamePortNumber;
			this.chatPortNumber = chatPortNumber;
			gamePort = new ServerSocket(gamePortNumber);
			chatPort = new ServerSocket(chatPortNumber);
			this.numberOfPlayers = numberOfPlayers;
			this.clientHandler = clientHandler;
			//Number of games initialized so far on the server.
			numberOfGames = 1;
		}
		catch (IOException e) {
			System.out.println("VoogaDaemon.java: Couldn't access port " + gamePort.getLocalPort() + ": " + e);
			System.exit(1);
		}
	}

	/**
	 * Whenever a new client connects to the chat or game ports, create instances 
	 * of the ChatHandler and ClientHandler subclass respectively and start them.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void run() {
		Socket clientSocket, chatSocket;
		while (true) {
			if (gamePort == null) {
				System.out.println("Sorry, the port disappeared.");
				System.exit(1);
			}
			try {
				chatSocket = chatPort.accept();
				clientSocket = gamePort.accept();
				ChatHandler chatHandler = new ChatHandler(new GameSocket(chatSocket), numberOfGames, gameName);
				chatHandler.start();
				try{
					Class<?> clientHandlerName = Class.forName(clientHandler);
					Class<?>[] clientHandlerParams = new Class[] {VoogaDaemon.class, GameSocket.class, int.class, int.class};
					Object[] clientHandlerFinalParams = new Object[] {this, new GameSocket(clientSocket), numberOfPlayers, numberOfGames};
					((ClientHandler)(clientHandlerName.getConstructor(clientHandlerParams).newInstance(clientHandlerFinalParams))).start();
				}
				catch(Exception e){
					System.out.println("Couldn't find ClientHandler subclass!");
					System.exit(1);
				}
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

	/**
	 * Close the game and chat ports.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	protected void finalize() {
		if (gamePort != null) {
			try { 
				gamePort.close(); 
			}
			catch (IOException e) {
				System.out.println("Error closing gamePort: " + e);
			}
			gamePort = null;
		}

		if (chatPort != null) {
			try { 
				chatPort.close(); 
			}
			catch (IOException e) {
				System.out.println("Error closing chatPort: " + e);
			}
			chatPort = null;
		}
	}
}

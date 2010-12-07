package vooga.engine.networking.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import vooga.engine.networking.GameSocket;

public class VoogaDaemon extends Thread{
	public int numberOfGames;
	private ServerSocket gamePort;
	private ServerSocket chatPort;
	private int numberOfPlayers;
	private String clientHandler;


	public VoogaDaemon(int gamePortNumber, int chatPortNumber, int numberOfPlayers, String clientHandler) {
		try {
			gamePort = new ServerSocket(gamePortNumber);
			chatPort = new ServerSocket(chatPortNumber);
			this.numberOfPlayers = numberOfPlayers;
			this.clientHandler = clientHandler;
			numberOfGames = 1;
		}
		catch (IOException e) {
			System.out.println("Couldn't access port " + gamePort.getLocalPort() + ": " + e);
			System.exit(1);
		}
	}

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
				ChatHandler chatHandler = new ChatHandler(new GameSocket(chatSocket), numberOfGames);
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

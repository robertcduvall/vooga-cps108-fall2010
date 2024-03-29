package vooga.engine.networking.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vooga.engine.networking.GameSocket;

/**
 * Abstract class that lays some of the groundwork for communicating between clients over the network.  There is one instance of a ClientHandler
 * subclass for each time the game is run and a static list of the current running ClientHandler subclasses.  ClientHandler extends the Handler class
 * so it also extends Thread and has a socket to send and receive chats through as well as a session ID to group ClientHandlers that are part of the 
 * same game session.  It also knows the number of players it needs for the game to start, and in its run method waits to start the game until that
 * number is fulfilled.  Classes that extend ClientHandler must extend the firstRun() method which is where the programmer puts anything that needs
 * to be sent before the game is begun.  Subclasses of ClientHandler will also subclass run (while calling super.run() as the first line of the method)
 * in order to add whatever game specific code they need to send messages to the other players in their game session.
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
public abstract class ClientHandler extends Handler{
	protected int numberOfPlayers;
	protected String userName;
	private VoogaDaemon daemon;
	protected static List<ClientHandler> handlers = new ArrayList<ClientHandler>();

	/**
	 * Calls super and initializes the numberOfPlayers in the game, the sessionID of the game, and the daemon that instantiated this.
	 * 
	 * @param daemon the VoogaDaemon that initialized this object; used to increment the numberOfGames when the correct amount of players is met
	 * @param socket the socket that clients will be communicating through
	 * @param numberOfPlayers the number of players necessary to play the game
	 * @param gameNumber this individual instance's number in relation to all the run games on the server, used to group clients in a common game session
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public ClientHandler(VoogaDaemon daemon, GameSocket socket, int numberOfPlayers, int gameNumber){
		super(socket, gameNumber);
		this.numberOfPlayers = numberOfPlayers;
		this.sessionID = gameNumber;
		this.daemon = daemon;
	}

	/**
	 * Each time a ClientHandler is started, add itself to the list of handlers, send out "wait" to all the players until the correct number of players is
	 * met, and then call firstRun() which is where the user will put any code that needs to be run right after the correct number of players are found.
	 * Subclasses of ClientHandler will have to override the interpretMessage method to say what it needs to do once it gets the String from the socket.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void run(){
		try {
			handlers.add(this);
			userName = socket.receive();
			if(userName.equals("guest"))
				userName += getNumberOfPlayers(sessionID);
			if(getNumberOfPlayers(sessionID) != numberOfPlayers){
				broadcastToAll("wait", this);
			}
			else{
				daemon.numberOfGames++;
				this.firstRun();
			}
			while (true) {
				String message = socket.receive();
				if(!interpretMessage(message, userName)){
					return;
				}
			}
		} 
		catch (IOException ex) {
			handlers.remove(this);
			socket.closeConnections();
		}
		finally {
			handlers.remove(this);
			socket.closeConnections();
		}
	}
	
	/**
	 * Abstract method that is called by run() right after the correct number of players are found and before the game has to start. Here is the place to put
	 * any code that needs to be run before the game begins (i.e. messages to say whose turn it is for turn games like TicTacToe, etc.)
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public abstract void firstRun();

	/**
	 * Abstract method that is called by run() after it reads a message from the socket. This is where you put the code you need to execute with the latest
	 * piece of data from the server.
	 *
	 * @param message the message received from the server
	 * @param userName the userName of the player who sent the message
	 * @return if the loop for checking messages should keep running
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public abstract boolean interpretMessage(String message, String userName);
	
	/**
	 * Broadcast (send) a message to everyone in the sender's game session.
	 * 
	 * @param message the message to send
	 * @param sender the handler who is sending the message
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	protected static void broadcastToAll (String message, ClientHandler sender) {
		synchronized (handlers) {
			for(ClientHandler handler : handlers){
				if(handler.getSessionID() == sender.getSessionID())
					handler.getSocket().send(message);
			}
		}
	}

	/**
	 * Broadcast (send) a message to everyone in the sender's game session but the sender.
	 * 
	 * @param message the message to send
	 * @param sender the handler who is sending the method
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	protected static void broadcastToOthers (String chat, ClientHandler sender) {
		synchronized (handlers) {
			for(ClientHandler handler : handlers){
				if(handler.getSessionID() == sender.getSessionID() && sender != handler)
					handler.getSocket().send(chat);
			}
		}
	}

	/**
	 * @return the number of players in a given game session.
	 * @param gameNumber the id of the game session
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public int getNumberOfPlayers(int gameNumber){
		int players = 0;
		for(ClientHandler handler : handlers){
			if(handler.getSessionID() == gameNumber)
				players++;
		}
		return players;
	}

	/**
	 * @return the players in a game session.
	 * @param gameNumber the id of the game session
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public List<ClientHandler> getPlayers(int gameNumber){
		List<ClientHandler> gameHandlers = new ArrayList<ClientHandler>();
		for(ClientHandler handler : handlers){
			if(handler.getSessionID() == gameNumber)
				gameHandlers.add(handler);
		}
		return gameHandlers;
	}
	
	/**
	 * @return the userName of this player
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public String getUsername(){
		return userName;
	}
}
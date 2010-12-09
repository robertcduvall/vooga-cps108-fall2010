package vooga.engine.networking.server;

import vooga.engine.networking.GameSocket;

/**
 * Class that handles communicating over the network.  Handler extends 
 * Thread and has a socket to send and receive chats through as well as 
 * a session ID to group Handlers that are part of the same game session.  
 * Subclasses will need to override the run method to put the code that 
 * sends and receives messages using the socket.
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
public abstract class Handler extends Thread{
	protected GameSocket socket;
	protected int sessionID;

	/**
	 * Initializes a Handler with a socket to communicate through and a gameNumber that
	 * represents the handler's game session's id.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public Handler(GameSocket socket, int sessionID){
		this.socket = socket;
		this.sessionID = sessionID;
	}
	
	/**
	 * Called once the thread is started.  Here is the place to put any code for sending
	 * and receiving messages to and from the socket.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public abstract void run();
	
	/**
	 * @return the ID of this handler's game session.
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public int getSessionID(){
		return sessionID;
	}
	
	/**
	 * @return the GameSocket that this handler is communicating through.
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public GameSocket getSocket(){
		return socket;
	}
}

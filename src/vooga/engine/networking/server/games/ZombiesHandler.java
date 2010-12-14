package vooga.engine.networking.server.games;

import java.util.Random;

import vooga.engine.networking.GameSocket;
import vooga.engine.networking.server.ClientHandler;
import vooga.engine.networking.server.VoogaDaemon;

/**
 * Extends ClientHandler to add Zombies specific logic to receiving and 
 * sending messages through the sockets.  Every game will have a class 
 * like this that extends ClientHandler.
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
public class ZombiesHandler extends ClientHandler{

	/**
	 * Calls ClientHandler's constructor.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public ZombiesHandler(VoogaDaemon daemon, GameSocket socket, int numberOfPlayers,
			int gameNumber) {
		super(daemon, socket, numberOfPlayers, gameNumber);
	}

	/**
	 * If there is no message then that means they have quit so tell the other players that this player has quit. 
	 * Otherwise send the message through to the opponent.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	@Override
	public void interpretMessage(String message){
		if(message == null){
			broadcastToOthers("quit", this);
			return;
		}
		else if(message.equals("gameOver"))
			return;
		broadcastToOthers(message, this);
	}

	/**
	 * Just tell all the players in the game to keep playing.  still is a method in the PlayState that does nothing.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void firstRun(){
		ClientHandler firstPlayer = getFirstPlayer(sessionID);
		Random random = new Random();
		broadcastToAll("seed:" + random.nextLong(), firstPlayer);
	}
}
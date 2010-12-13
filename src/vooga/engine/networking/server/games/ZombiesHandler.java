package vooga.engine.networking.server.games;

import java.io.IOException;

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
	 * Calls super.run() first to wait for the two players. Then tries to receive the 
	 * message from the server. If there is no message then that means they have quit so tell the 
	 * other players that this player has quit. Otherwise send the message through to the opponent.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	@Override
	public void run() {
		try {
			super.run();
			while (true) {
				String message = socket.receive();
				if(message == null){
					broadcastToOthers("quit", this);
					return;
				}
				else if(message.equals("gameOver"))
					return;
				broadcastToOthers(message, this);
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
	 * Nothing needs to be done on the first run for this game.  Look at the TicTacToe example for a game where there are some 
	 * necessary first run initializations.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void firstRun(){
		ClientHandler firstPlayer = getFirstPlayer(sessionID);
//		firstPlayer.getSocket().send("yourTurn");
//		broadcastToOthers("theirTurn", firstPlayer);
		broadcastToAll("still", firstPlayer);
	}
}

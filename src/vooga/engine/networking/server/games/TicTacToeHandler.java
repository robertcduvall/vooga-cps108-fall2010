package vooga.engine.networking.server.games;

import java.io.IOException;

import vooga.engine.networking.GameSocket;
import vooga.engine.networking.server.ClientHandler;
import vooga.engine.networking.server.VoogaDaemon;

public class TicTacToeHandler extends ClientHandler{

	/**
	 * Calls ClientHandler's constructor.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public TicTacToeHandler(VoogaDaemon daemon, GameSocket socket, int numberOfPlayers,
			int gameNumber) {
		super(daemon, socket, numberOfPlayers, gameNumber);
	}

	/**
	 * Calls super.run() first to wait for the two players and call the firstRun method.  Then tries to receive the message from the server. If there is no
	 * message then that means they have quit so tell the other players that this player has quit. Otherwise send the message through to the opponent, tell
	 * the opponent that it is their turn, and then let the sender know that it is now their opponent's turn.
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
				broadcastToOthers(message, this);
				broadcastToOthers("yourTurn", this);
				socket.send("theirTurn");
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
	 * Right after the correct number of players is found, tell the first player that it is their turn and tell the other player that it is their
	 * opponent's turn.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void firstRun(){
		ClientHandler firstPlayer = getFirstPlayer(sessionID);
		firstPlayer.getSocket().send("yourTurn");
		broadcastToOthers("theirTurn", firstPlayer);
	}
}

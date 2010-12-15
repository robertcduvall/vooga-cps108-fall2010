package vooga.engine.networking.server.games;

import vooga.engine.networking.GameSocket;
import vooga.engine.networking.server.ClientHandler;
import vooga.engine.networking.server.VoogaDaemon;

/**
 * Extends ClientHandler to add TicTacToe specific logic to receiving and 
 * sending messages through the sockets.  Every game will have a class 
 * like this that extends ClientHandler.
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
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
	 * If there is no message then that means they have quit so tell the other players that this 
	 * player has quit. Otherwise send the message through to the opponent, tell the opponent that it is 
	 * their turn, and then let the sender know that it is now their opponent's turn.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	@Override
	public boolean interpretMessage(String message){
		if(message == null){
			broadcastToOthers("quit", this);
			return false;
		}
		else if(message.equals("gameOver"))
			return false;
		broadcastToOthers(message, this);
		broadcastToOthers("yourTurn", this);
		socket.send("theirTurn");
		return true;
	}
	
	/**
	 * Right after the correct number of players is found, tell the first player 
	 * that it is their turn and tell the other player that it is their opponent's turn.
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

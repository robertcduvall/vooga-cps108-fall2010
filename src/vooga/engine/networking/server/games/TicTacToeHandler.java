package vooga.engine.networking.server.games;

import java.io.IOException;

import vooga.engine.networking.GameSocket;
import vooga.engine.networking.server.ClientHandler;
import vooga.engine.networking.server.VoogaDaemon;

public class TicTacToeHandler extends ClientHandler{

	public TicTacToeHandler(VoogaDaemon daemon, GameSocket socket, int numberOfPlayers,
			int gameNumber) {
		super(daemon, socket, numberOfPlayers, gameNumber);
	}

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
	
	public void firstRun(){
		ClientHandler firstPlayer = getFirstPlayer(gameNumber);
		firstPlayer.getSocket().send("yourTurn");
		broadcastToOthers("theirTurn", firstPlayer);
	}
}

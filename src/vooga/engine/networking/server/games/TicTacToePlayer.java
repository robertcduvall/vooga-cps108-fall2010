package vooga.engine.networking.server.games;
import java.net.*;

import vooga.engine.networking.server.ServerPlayer;
import vooga.engine.networking.server.VoogaDaemon;

public class TicTacToePlayer extends ServerPlayer{
	public TicTacToePlayer(VoogaDaemon server, Socket sock) {
		super(server, sock);
	}
	
	public void run() {
		daemon.waitForGame(this, "wait").playGame(this);
	}

	public void closeConnections() {
		closeConnections("GAMEOVER");
	}
}

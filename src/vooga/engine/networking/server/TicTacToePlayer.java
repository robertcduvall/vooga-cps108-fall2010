package vooga.engine.networking.server;
import java.net.*;

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

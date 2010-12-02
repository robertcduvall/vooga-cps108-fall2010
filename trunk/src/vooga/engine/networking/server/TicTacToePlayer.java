package vooga.engine.networking.server;
import java.net.*;
import java.io.*;

import vooga.engine.networking.GameSocket;

class TicTacToePlayer extends GameSocket{
	private TicTacToeDaemon daemon = null;

	public TicTacToePlayer(TicTacToeDaemon server, Socket sock) {
		super(sock);
		daemon = server;
	}

	public void run() {
		daemon.waitForGame(this).playGame(this);
	}

	public void closeConnections() {
		super.closeConnections();
		if (outStream != null) {
			send("GAMEOVER");
		}
	}
}

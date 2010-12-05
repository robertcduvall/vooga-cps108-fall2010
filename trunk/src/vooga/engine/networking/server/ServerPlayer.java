package vooga.engine.networking.server;

import java.net.Socket;

import vooga.engine.networking.GameSocket;

public abstract class ServerPlayer extends GameSocket{
	protected VoogaDaemon daemon;

	public ServerPlayer(VoogaDaemon server, Socket sock) {
		super(sock);
		daemon = server;
	}

	public abstract void run();

	public void closeConnections(String message) {
		super.closeConnections();
		if (outStream != null) {
			send(message);
		}
	}
}

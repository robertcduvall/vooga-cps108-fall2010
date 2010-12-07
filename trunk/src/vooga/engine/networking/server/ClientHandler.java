package vooga.engine.networking.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vooga.engine.networking.GameSocket;

public abstract class ClientHandler extends Thread{
	protected GameSocket socket;
	protected int gameNumber;
	protected int numberOfPlayers;
	private VoogaDaemon daemon;
	protected static List<ClientHandler> handlers = new ArrayList<ClientHandler>();

	public ClientHandler(VoogaDaemon daemon, GameSocket socket, int numberOfPlayers, int gameNumber){
		this.socket = socket;
		this.numberOfPlayers = numberOfPlayers;
		this.gameNumber = gameNumber;
		this.daemon = daemon;
	}

	public void run(){
		handlers.add(this);
		if(getPlayers(gameNumber) != numberOfPlayers){
			broadcastToAll("wait", this);
		}
		else{
			daemon.numberOfGames++;
			this.firstRun();
		}
	}
	
	public abstract void firstRun();

	protected static void broadcastToAll (String chat, ClientHandler sender) {
		synchronized (handlers) {
			for(ClientHandler handler : handlers){
				if(handler.getGameNumber() == sender.getGameNumber())
					handler.getSocket().send(chat);
			}
		}
	}

	protected static void broadcastToOthers (String chat, ClientHandler sender) {
		synchronized (handlers) {
			for(ClientHandler handler : handlers){
				if(handler.getGameNumber() == sender.getGameNumber() && sender != handler)
					handler.getSocket().send(chat);
			}
		}
	}

	public int getPlayers(int gameNumber){
		int players = 0;
		for(ClientHandler handler : handlers){
			if(handler.getGameNumber() == gameNumber)
				players++;
		}
		return players;
	}

	public ClientHandler getFirstPlayer(int gameNumber){
		for(ClientHandler handler : handlers){
			if(handler.getGameNumber() == gameNumber)
				return handler;
		}
		return null;
	}

	public int getGameNumber(){
		return gameNumber;
	}

	public GameSocket getSocket(){
		return socket;
	}
}
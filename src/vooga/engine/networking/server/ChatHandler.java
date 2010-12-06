package vooga.engine.networking.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vooga.engine.networking.GameSocket;

public class ChatHandler extends Thread{
	private GameSocket socket;
	private int gameNumber;
	protected static List<ChatHandler> handlers = new ArrayList<ChatHandler>();

	public ChatHandler(GameSocket socket, int gameNumber){
		this.socket = socket;
		this.gameNumber = gameNumber;
	}

	public void run () {
		try {
			handlers.add(this);
			while (true) {
				String chat = socket.receive();
				if(chat == null){
					return;
				}
				broadcast(chat, this);
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

	protected static void broadcast (String chat, ChatHandler sender) {
		synchronized (handlers) {
			for(ChatHandler handler : handlers){
				if(handler.getGameNumber() == sender.getGameNumber() && sender != handler)
					handler.getSocket().send(chat);
			}
		}
	}
	
	public int getGameNumber(){
		return gameNumber;
	}
	
	public GameSocket getSocket(){
		return socket;
	}
}
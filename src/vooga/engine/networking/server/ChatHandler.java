package vooga.engine.networking.server;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import vooga.engine.networking.GameSocket;

public class ChatHandler extends Thread{
	private GameSocket socket;
	protected static Vector<ChatHandler> handlers = new Vector<ChatHandler>();

	public ChatHandler(GameSocket socket){
		this.socket = socket;
	}

	public void run () {
		try {
			handlers.addElement(this);
			while (true) {
				String chat = socket.receive();
				if(chat == null){
					return;
				}
				broadcast(chat, this);
			}
		} 
		catch (IOException ex) {
			handlers.removeElement(this);
			socket.closeConnections();
		}
		finally {
			handlers.removeElement(this);
			socket.closeConnections();
		}
	}

	protected static void broadcast (String chat, ChatHandler sender) {
		synchronized (handlers) {
			Enumeration<ChatHandler> e = handlers.elements();
			while (e.hasMoreElements()) {
				ChatHandler c = e.nextElement();
				if(sender != c)
					c.getSocket().send(chat);
			}
		}
	}
	
	public GameSocket getSocket(){
		return socket;
	}
}
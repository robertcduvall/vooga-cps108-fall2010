package vooga.engine.networking.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import vooga.engine.networking.GameSocket;
import vooga.engine.networking.server.VoogaServer;

public abstract class ClientConnection extends GameSocket{
	
	public ClientConnection(String name) throws UnknownHostException, IOException{
		super(new Socket("localhost", VoogaServer.getPort(name)));
	}
	
	public abstract String getData();
	
	public void sendData(Serializeable data){
		send(data.serialize());
	}
}

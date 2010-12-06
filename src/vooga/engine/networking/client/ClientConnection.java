package vooga.engine.networking.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import vooga.engine.networking.GameSocket;

public abstract class ClientConnection extends GameSocket{
	public ClientConnection() throws UnknownHostException, IOException{
		super(new Socket("10.180.134.73", 1234));
	}
	
	public abstract String getData();
	
	public void sendData(Serializeable data){
		send(data.serialize());
	}
}

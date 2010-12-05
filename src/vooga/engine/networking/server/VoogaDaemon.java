package vooga.engine.networking.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class VoogaDaemon extends Thread{
	public static final int PORTNUM = 1234;
	private ServerSocket port;
	private ServerPlayer[] playersWaiting;
	private ServerPlayer playerWaiting;
	private ServerGame thisGame;
	private String serverGame;
	private String serverPlayer;


	public VoogaDaemon(int numberOfPlayers, String serverGame, String serverPlayer) {
		try {
			port = new ServerSocket(PORTNUM);
			playersWaiting = new ServerPlayer[numberOfPlayers - 1];
			this.serverGame = serverGame;
			this.serverPlayer = serverPlayer;
		}
		catch (IOException e) {
			System.out.println("Couldn't access port " + PORTNUM + ": " + e);
			System.exit(1);
		}
	}

	public void run() {
		Socket clientSocket;
		while (true) {
			if (port == null) {
				System.out.println("Sorry, the port disappeared.");
				System.exit(1);
			}
			try {
				clientSocket = port.accept();
				try{
					Class<?> serverPlayerName = Class.forName(serverPlayer);
					Class<?>[] serverPlayerParams = new Class[] {VoogaDaemon.class, Socket.class};
					Object[] serverPlayerFinalParams = new Object[] {this, clientSocket};
					((ServerPlayer)(serverPlayerName.getConstructor(serverPlayerParams).newInstance(serverPlayerFinalParams))).start();
//					new TicTacToePlayer(this, clientSocket).start();
				}
				catch(Exception e){
					System.out.println("Couldn't find ServerPlayer subclass!");
					System.exit(1);
				}
			}
			catch(SocketTimeoutException s)
			{
				System.out.println("Socket timed out!");
				break;
			}
			catch (IOException e) {
				System.out.println("Couldn't connect player: " + e);
				System.exit(1);
			}
		}
	}

//	public synchronized ServerGame waitForGame(ServerPlayer p, String waitString) {
//		ServerGame retval = null;
//		boolean waiting = false;
//		for(int i = 0; i < playersWaiting.length; i++){
//			ServerPlayer playerWaiting = playersWaiting[i];
//			if(playerWaiting == null){
//				waiting = true;
//				playerWaiting = p;
//				playersWaiting[i] = playerWaiting;
//				thisGame = null;
//				p.send(waitString);
//				while (waiting) {
//					try {
//						wait();
//					}
//					catch (InterruptedException e) {
//						System.out.println("Error: " + e);
//					}
//				}
//				return thisGame;
//			}
//		}
//		if(!waiting){
//			ServerPlayer[] players = new ServerPlayer[playersWaiting.length + 1];
//			for(int i = 0; i < playersWaiting.length; i++){
//				players[i] = playersWaiting[i];
//			}
//			players[players.length - 1] = p;
//			try{
//				Class<?> serverGameName = Class.forName(serverGame);
//				Class<?>[] serverGameParams = new Class[] {ServerPlayer[].class};
//				Object[] serverGameFinalParams = new Object[] {players};
//				thisGame = ((ServerGame)(serverGameName.getConstructor(serverGameParams).newInstance(serverGameFinalParams)));
//			}
//			catch(Exception e){
//				System.out.println("Couldn't find ServerGame subclass!");
//				System.exit(1);
//			}
//			retval = thisGame;
//			waiting = false;
//			notify();
//			return retval;
//		}
//		return null;
//	}
	
	public synchronized ServerGame waitForGame(ServerPlayer p, String waitString) {
		ServerGame retval = null;
		if(playerWaiting == null){
			playerWaiting = p;
			thisGame = null;
			p.send(waitString);
			while (playerWaiting != null) {
				try {
					wait();
				}
				catch (InterruptedException e) {
					System.out.println("Error: " + e);
				}
			}
			return thisGame;
		}
		else{
			try{
				ServerPlayer[] players = new ServerPlayer[] {playerWaiting, p};
				Class<?> serverGameName = Class.forName(serverGame);
				Class<?>[] serverGameParams = new Class[] {ServerPlayer[].class};
				Object[] serverGameFinalParams = new Object[] {players};
				thisGame = ((ServerGame)(serverGameName.getConstructor(serverGameParams).newInstance(serverGameFinalParams)));
			}
			catch(Exception e){
				System.out.println("Couldn't find ServerGame subclass!");
				System.exit(1);
			}
			retval = thisGame;
			playerWaiting = null;
			notify();
			return retval;
		}
	} 

	protected void finalize() {
		if (port != null) {
			try { 
				port.close(); 
			}
			catch (IOException e) {
				System.out.println("Error closing port: " + e);
			}
			port = null;
		}
	}
}

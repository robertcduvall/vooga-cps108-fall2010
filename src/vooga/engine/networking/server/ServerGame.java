package vooga.engine.networking.server;


import java.util.*;

public abstract class ServerGame {
	protected List<ServerPlayer> players;
	protected List<List<Integer>> messageQueues;

	public ServerGame(ServerPlayer[] players) {
		this.players = Arrays.asList(players);
		messageQueues = new ArrayList<List<Integer>>();
		for(int i = 0; i < players.length; i++){
			messageQueues.add(new ArrayList<Integer>());
		}
	}

	public abstract void playGame(ServerPlayer currentPlayer);

	protected synchronized int getStatus(ServerPlayer me) {
		List<Integer> ourMessages = null;
		for(int i = 0; i < players.size(); i++){
			if(players.get(i) == me){
				ourMessages = messageQueues.get(i);
			}
		}
		while (ourMessages.isEmpty()) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				System.out.println("Error: " + e);
			}
		}
		try {
			Integer retval = ourMessages.get(0);
			try {
				ourMessages.remove(0);
			}
			catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Array index out of bounds: " + e);
				System.exit(1);
			}
			return retval.intValue();
		}
		catch (NoSuchElementException e) {
			System.out.println("Couldn't get first element: " + e);
			System.exit(1);
			return 0;
		}
	}

	protected synchronized void sendStatus(ServerPlayer me, int message) {
		List<Integer> theirMessage = null;
		for(int i = 0; i < players.size(); i++){
			if(players.get(i) == me){
				theirMessage = messageQueues.get(i);
			}
		}
		theirMessage.add(new Integer(message));
		notify();
	}
}

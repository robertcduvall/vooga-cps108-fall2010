package vooga.engine.networking.server;

public class VoogaServer {
	public static void main(String args[ ]) {
		System.out.println("Vooga server up and running...");
		new TicTacToeDaemon().start();
	}
}

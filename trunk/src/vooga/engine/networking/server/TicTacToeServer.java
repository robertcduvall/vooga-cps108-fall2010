package vooga.engine.networking.server;

public class TicTacToeServer {
	public static void main(String args[ ]) {
		System.out.println("TicTacToe server up and running...");
		new TicTacToeDaemon().start();
	}
}

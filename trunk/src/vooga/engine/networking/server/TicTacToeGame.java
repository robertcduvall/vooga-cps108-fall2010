package vooga.engine.networking.server;

import java.io.IOException;

public class TicTacToeGame extends ServerGame{
	public static final int ERROR = -1;
	public static final int GAMEOVER = -2;
	public static final int IQUIT = -3;
	public static final int YOURTURN = -4;
	public static final int SENTSTRING = -5;
	private String sentString;
	
	public TicTacToeGame(ServerPlayer[] players){
		super(players);
	}

	@Override
	public void playGame(ServerPlayer currentPlayer) {
		String message;
		boolean playGame = true;
		boolean theirTurn = false;
		try {
			if (currentPlayer != players.get(0))
				theirTurn = true;
//			else if (currentPlayer != player1){
//				System.out.println("Illegal call to playGame!");
//				return;
//			}
			while (playGame){
				if (!theirTurn){
					currentPlayer.send("yourTurn");
					message = currentPlayer.receive();
					if(message == null){
						sendStatus(currentPlayer, IQUIT);
						playGame = false;
					}
					else{
						message = message.toUpperCase();
						message = message.trim();
						if (message.startsWith("IQUIT")){
							sendStatus(currentPlayer, IQUIT);
							playGame = false;
						}
						else if(message.startsWith("GAMEOVER")){
							sendStatus(currentPlayer, GAMEOVER);
							playGame = false;
						}
						else {
							sentString = message;
							sendStatus(currentPlayer, SENTSTRING);
						}
					}
				}
				else {
					theirTurn = false;
				}
				if (playGame){
					currentPlayer.send("theirTurn");
					int stat = getStatus(currentPlayer);
					if (stat == IQUIT) {
						currentPlayer.send("theyQuit");
						playGame = false;
					}
					else if(stat == GAMEOVER){
						playGame = false;
					}
					else if (stat == SENTSTRING) {
						currentPlayer.send(sentString);
					}
					else if (stat == ERROR) {
						currentPlayer.send("error");
						currentPlayer.closeConnections();
						playGame = false;
					}
					else {
						System.out.println("Received Bad Status");
						sendStatus(currentPlayer,ERROR);
						currentPlayer.closeConnections();
						playGame = false;
					}
				}
			}
			currentPlayer.closeConnections();
			return;
		}
		catch (IOException e) {
			System.out.println("I/O Error: " + e);
			System.exit(1);
		}
	}

}

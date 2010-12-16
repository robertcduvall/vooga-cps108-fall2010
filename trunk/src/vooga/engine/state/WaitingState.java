package vooga.engine.state;


import java.awt.Graphics2D;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.networking.client.ClientConnection;
import vooga.engine.state.GameState;

/**
 * GameState for when the Game is waiting for the second player to join.
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
public class WaitingState extends GameState{
	private Game game;
	private ClientConnection waitingConnection;
	private PlayField field;
	private GameState playState;

	/**
	 * Constructor for waiting GameState.
	 * 
	 * @param game Game to be able to switch GameStates
	 * @param connection ClientConnection to get the latest message from the socket
	 * @param field PlayField for WaitingState to render
	 * @param playState PlayState to switch to and pass the message whenever it gets a non "wait" String from the socket
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public WaitingState(Game game, ClientConnection connection, PlayField field, GameState playState){
		this.game = game;
		this.waitingConnection = connection;
		this.field = field;
		this.playState = playState;
	}
	
	/**
	 * If it receives a message from the socket that is not "wait", then switch to the PlayState and tell the PlayState what the message was.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	@Override
	public void update(long t) {
		String status = waitingConnection.getData();
		if(!status.equals("wait")){
			playState.interpretMessage(status);
			game.getGameStateManager().switchTo(playState);
		}
		super.update(t);
		field.update(t);
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
		field.render(g);
	}
	
	
	@Override
	public void initialize() {
	}

}

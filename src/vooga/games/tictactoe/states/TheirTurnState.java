package vooga.games.tictactoe.states;

import java.awt.Graphics2D;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.networking.client.ClientConnection;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;

/**
 * GameState for when the player is waiting for the other player to play.
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
public class TheirTurnState extends GameState{
	private Game game;
	private ClientConnection connection;
	private PlayField field;
	private PlayState playState;
	private int checkDelay = 1;

	/**
	 * Constructor for the GameState shown when it's the other player's turn.
	 * 
	 * @param game Game to be able to switch GameStates
	 * @param connection ClientConnection to get the latest message from the socket
	 * @param field PlayField for TheirTurnState to render
	 * @param playState PlayState to switch to and pass the message whenever it gets a non "theirTurn" String from the socket
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public TheirTurnState(Game game, ClientConnection connection, PlayField field, PlayState playState){
		this.game = game;
		this.connection = connection;
		this.field = field;
		this.playState = playState;
	}
	
	/**
	 * If it receives a message from the socket that is not "theirTurn", then switch to the PlayState and tell the PlayState what the message was.
	 * checkDelay is a way around a rendering issue since render is called after update and thus would never be called if I block the main thread
	 * with the connection.getData() call.
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	@Override
	public void update(long t) {
		if(checkDelay == 0){
			String status = connection.getData();
			if(!status.equals(Resources.getString("theirTurnString"))){
				checkDelay = 1;
				playState.interpretMessage(status);
				game.getGameStateManager().switchTo(playState);
			}
		}
		else{
			checkDelay--;
		}
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
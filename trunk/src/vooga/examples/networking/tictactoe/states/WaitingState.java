package vooga.examples.networking.tictactoe.states;

import java.awt.Graphics2D;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.examples.networking.tictactoe.TicTacToeConnection;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;

public class WaitingState extends GameState{
	private Game game;
	private TicTacToeConnection connection;
	private PlayField field;
	private PlayState playState;

	public WaitingState(Game game, TicTacToeConnection connection, PlayField field, PlayState playState){
		this.game = game;
		this.connection = connection;
		this.field = field;
		this.playState = playState;
	}
	
	@Override
	public void update(long t) {
		int status = connection.getData();
		if(status != Resources.getInt("wait")){
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
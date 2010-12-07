package vooga.examples.networking.tictactoe.states;

import java.awt.Graphics2D;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.examples.networking.tictactoe.TicTacToeConnection;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;

public class TheirTurnState extends GameState{
	private Game game;
	private TicTacToeConnection connection;
	private PlayField field;
	private PlayState playState;
	private int checkDelay = 1;

	public TheirTurnState(Game game, TicTacToeConnection connection, PlayField field, PlayState playState){
		this.game = game;
		this.connection = connection;
		this.field = field;
		this.playState = playState;
	}
	
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
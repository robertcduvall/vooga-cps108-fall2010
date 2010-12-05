package vooga.examples.networking.tictactoe.states;

import java.awt.Graphics2D;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.state.MenuGameState;

public class GameOverState extends MenuGameState{
	private Game myGame;
	private PlayField myPlayfield;
	
	public GameOverState(Game game, PlayField playfield) {
		super();
		myPlayfield = playfield;
		myGame = game;
	}
	
	@Override
	public void update(long t) {
		super.update(t);
		myPlayfield.update(t);
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
		myPlayfield.render(g);
	}
	
	@Override
	public void initialize() {
	}
}

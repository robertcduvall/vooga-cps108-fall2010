package vooga.games.tictactoe.states;

import java.awt.Graphics2D;
import vooga.engine.core.PlayField;
import vooga.engine.state.MenuGameState;

/**
 * GameState for when the Game is over.
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
public class GameOverState extends MenuGameState{
	private PlayField field;
	
	/**
	 * Constructor for the GameState shown when the game is over.
	 * 
	 * @param field PlayField for GameOverState to render
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public GameOverState(PlayField field) {
		super();
		this.field = field;
	}
	
	@Override
	public void update(long t) {
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

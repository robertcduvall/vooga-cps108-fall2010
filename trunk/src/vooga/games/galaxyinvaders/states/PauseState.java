package vooga.games.galaxyinvaders.states;

import java.awt.Color;
import java.awt.event.KeyEvent;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.state.GameState;
import vooga.engine.state.PauseGameState;

public class PauseState extends PauseGameState {
	
	private Game game;

	public PauseState(Game game, GameState previousGameState, String pauseMessage,
			Color color) {
		super(previousGameState, pauseMessage, color);
		this.game = game;
		initControls();
	}
	
	private void initControls()
	{
		Control gameControl = new KeyboardControl(game);
		// this is a cheat code. it kills all the enemies on the screen and advances you to the next level
		gameControl.addInput(KeyEvent.VK_P, "toggle", "vooga.games.galaxyinvaders.DropThis");
		gameControl.addInput(KeyEvent.VK_R, "startNewGame", "vooga.games.galaxyinvaders.DropThis");
	}

}

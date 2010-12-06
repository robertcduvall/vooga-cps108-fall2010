package vooga.games.galaxyinvaders.states;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Collection;

import com.golden.gamedev.object.background.ColorBackground;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
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
		Control gameControl = new KeyboardControl(game, game);
//		gameControl.addInput(KeyEvent.VK_P, "resumeGame", "vooga.games.galaxyinvaders.DropThis");
		gameControl.addInput(KeyEvent.VK_R, "startNewGame", "vooga.games.galaxyinvaders.Blah");
		Collection<PlayField> playfields = this.getUpdateField();
		for (PlayField playfield : playfields)
		{
			playfield.setBackground(new ColorBackground(Color.BLACK));
			playfield.addControl("game", gameControl);
		}
	}

}

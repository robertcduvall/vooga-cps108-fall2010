package vooga.games.doodlejump.buttons;

import java.awt.image.BufferedImage;

import vooga.widget.Button;
import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.games.doodlejump.Blah;

/**
 * The RestartButton class extends Button and creates a button that restarts the
 * game.
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class RestartButton extends Button {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final BufferedImage RESTART_BUTTON_IMAGE = Resources
			.getImage("playAgain");
	private static final double RESTART_BUTTON_X = Resources
			.getDouble("playButtonX");
	private static final double RESTART_BUTTON_Y = Resources
			.getDouble("playButtonY");

	private Blah myGame = (Blah) Resources.getGame();

	public RestartButton(Game game) {
		super(RESTART_BUTTON_IMAGE, RESTART_BUTTON_X, RESTART_BUTTON_Y);
	}

	@Override
	public void actionPerformed() {
		myGame.restartGame();
	}

}

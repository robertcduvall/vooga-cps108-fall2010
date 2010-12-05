package vooga.games.doodlejump.buttons;

import java.awt.image.BufferedImage;

import vooga.widget.Button;
import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.games.doodlejump.Blah;

/**
 * The PlayButton class extends button and creates a button that allows for
 * playing the game from the start menu.
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class PlayButton extends Button {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final BufferedImage PLAY_BUTTON_IMAGE = Resources
			.getImage("playButton");
	private static final double PLAY_BUTTON_X = Resources
			.getDouble("playButtonX");
	private static final double PLAY_BUTTON_Y = Resources
			.getDouble("playButtonY");

	private Blah myGame = (Blah) Resources.getGame();

	public PlayButton() {
		super(PLAY_BUTTON_IMAGE, PLAY_BUTTON_X, PLAY_BUTTON_Y);
	}

	@Override
	public void actionPerformed() {
		myGame.resumeGame();
	}

}

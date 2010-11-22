package vooga.games.doodlejump.buttons;

import java.awt.image.BufferedImage;

import vooga.widget.Button;
import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.games.doodlejump.BlahThis;

/**
 * The ResumeButton extends Button and creates a button that allows for the
 * gameplay to continue.
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class ResumeButton extends Button {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final BufferedImage RESUME_BUTTON_IMAGE = Resources
			.getImage("resumeButton");
	private static final double RESUME_BUTTON_X = Resources
			.getDouble("resumeButtonX");
	private static final double RESUME_BUTTON_Y = Resources
			.getDouble("resumeButtonY");

	private BlahThis myGame;

	public ResumeButton(Game game) {
		super(game, RESUME_BUTTON_IMAGE, RESUME_BUTTON_X, RESUME_BUTTON_Y);
		myGame = (BlahThis) game;
	}

	@Override
	public void actionPerformed() {
		myGame.resumeGame();
	}

}

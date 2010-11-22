package vooga.games.doodlejump.buttons;

import java.awt.image.BufferedImage;

import vooga.widget.Button;
import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.games.doodlejump.BlahThis;

public class ResumeButton extends Button {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final BufferedImage RESUME_BUTTON_IMAGE = Resources.getImage("resumeButton");
	private static final double RESUME_BUTTON_X = Resources.getDouble("playButtonX");
	private static final double RESUME_BUTTON_Y = Resources.getDouble("playButtonY");

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

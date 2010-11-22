package vooga.games.cyberion.buttons;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.widget.Button;
import vooga.games.cyberion.DropThis;
import vooga.games.cyberion.states.PlayState;

/**
 * Play button
 * 
 * @author Harris.He
 * 
 */

public class PlayButton extends Button {
	private static final int START_X = 250;
	private static final int START_Y = 100;
	private static final BufferedImage myImage = Resources
			.getImage("playButton");
	private DropThis myGame;

	public PlayButton(Game game) {
		super(game, myImage, START_X, START_Y);
		myGame = (DropThis) game;
	}

	// TODO - change actionPerformed to switchTo(PlayState)
	@Override
	public void actionPerformed() {
		myGame.setPlayState();
		// this.setActive(false);
		PlayState pgs = (PlayState) myGame.getGameStateManager()
				.getGameState(4);
		pgs.initialize();
		myGame.getGameStateManager().switchTo(pgs);
		myGame.setPlayState();
	}

}

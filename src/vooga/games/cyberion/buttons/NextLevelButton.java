package vooga.games.cyberion.buttons;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.games.cyberion.states.PlayState;
import vooga.games.cyberion.DropThis;
import vooga.widget.Button;

public class NextLevelButton extends Button {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int START_X = 275;
	private static final int START_Y = 200;
	private static final BufferedImage myImage = Resources
			.getImage("nextLevelButtonImage");
	private DropThis myGame = (DropThis) Resources.getGame();

	public NextLevelButton() {
		super(myImage, START_X, START_Y);
	}

	@Override
	public void actionPerformed() {
		myGame.setPlayState();
		PlayState pgs = (PlayState) myGame.getGameStateManager()
				.getGameState(4);
		myGame.getGameStateManager().switchTo(pgs.nextLevel());
		myGame.setPlayState();
	}

}

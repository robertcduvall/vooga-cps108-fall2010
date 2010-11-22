package vooga.games.grandius.sprites.buttons;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.games.grandius.DropThis;
import vooga.widget.Button;

@SuppressWarnings("serial")
public class NextLevelButton extends Button {

	private static final int START_X = 275;
	private static final int START_Y = 200;
	private static final BufferedImage myImage = Resources.getImage("nextLevelButtonImage");
	private DropThis myGame;
	
	public NextLevelButton(Game game) {
		super(game, myImage, START_X, START_Y);
		myGame = (DropThis)game;
	}

	@Override
	public void actionPerformed() {
		myGame.getGameStateManager().switchTo(myGame.getGameStateManager().getGameState(5));
	}

}
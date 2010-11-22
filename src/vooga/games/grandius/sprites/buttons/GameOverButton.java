package vooga.games.grandius.sprites.buttons;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.widget.Button;

@SuppressWarnings("serial")
public class GameOverButton extends Button {

	private static final int START_X = 150;
	private static final int START_Y = 100;
	private static final BufferedImage myImage = Resources.getImage("gameOverButtonImage");
	
	public GameOverButton(Game game) {
		super(game, myImage, START_X, START_Y);
	}

	@Override
	public void actionPerformed() {
		System.exit(1);
	}

}

package vooga.games.grandius.buttons;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.widget.Button;

public class NextLevelButton extends Button {

	private static final int START_X = 220;
	private static final int START_Y = 400;
	private static final BufferedImage myImage = Resources.getImage("NextLevelButton");
	
	public NextLevelButton(Game game) {
		super(game, myImage, START_X, START_Y);
	}

	@Override
	public void actionPerformed() {
		System.out.println("Next level button activated!");
	}

}

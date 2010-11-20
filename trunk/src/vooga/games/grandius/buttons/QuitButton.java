package vooga.games.grandius.buttons;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.widget.Button;

public class QuitButton extends Button {

	private static final int START_X = 220;
	private static final int START_Y = 200;
	private static final BufferedImage myImage = Resources.getImage("quitButtonImage");
	
	public QuitButton(Game game) {
		super(game, myImage, START_X, START_Y);
	}

	@Override
	public void actionPerformed() {
		System.out.println("Quit button activated!");
	}

}

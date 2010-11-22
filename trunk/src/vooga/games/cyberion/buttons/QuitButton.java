package vooga.games.cyberion.buttons;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.widget.Button;

/**
 * Quit game button
 * 
 * @author Harris.He
 * 
 */

public class QuitButton extends Button {
	private static final int START_X = 250;
	private static final int START_Y = 300;
	private static final BufferedImage myImage = Resources
			.getImage("quitButton");

	public QuitButton(Game game) {
		super(game, myImage, START_X, START_Y);
	}

	public void actionPerformed() {
		System.out.println("Quit button activated!");
		this.setActive(false);

	}
}

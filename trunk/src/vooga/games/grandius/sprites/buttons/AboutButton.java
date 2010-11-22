package vooga.games.grandius.sprites.buttons;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.widget.Button;

@SuppressWarnings("serial")
public class AboutButton extends Button {

	private static final int START_X = 20;
	private static final int START_Y = 40;
	private static final BufferedImage myImage = Resources.getImage("aboutButtonImage");
	
	public AboutButton(Game game) {
		super(game, myImage, START_X, START_Y);
	}

	@Override
	public void actionPerformed() {
		System.out.println("Have fun!");
	}
}

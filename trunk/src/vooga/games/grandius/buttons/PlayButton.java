package vooga.games.grandius.buttons;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.widget.Button;

public class PlayButton extends Button {
	private static final int START_X = 220;
	private static final int START_Y = 500;
	private static final BufferedImage myImage = Resources.getImage("PlayButton");
	
	public PlayButton(Game game) {
		super(game, myImage, START_X, START_Y);
	}

	//TODO - change actionPerformed to switchTo(PlayState)
	@Override
	public void actionPerformed() {
		System.out.println("Play button activated!");
		this.setActive(false);

	}

}






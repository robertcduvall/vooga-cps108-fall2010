package vooga.games.grandius;


import java.awt.image.BufferedImage;

import vooga.engine.core.Button;
import vooga.engine.core.Game;

public class StartButton extends Button {
	private static final int START_X = 220;
	private static final int START_Y = 500;
	
	public StartButton (Game game, BufferedImage image) {
		super(game, image, START_X, START_Y);	
	}
	
	//TODO - change actionPerformed to switchTo(PlayState)
	@Override
	public void actionPerformed() {
		this.setActive(false);
	}

}

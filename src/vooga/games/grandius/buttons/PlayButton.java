package vooga.games.grandius.buttons;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.games.grandius.DropThis;
import vooga.widget.Button;

public class PlayButton extends Button {
	private static final int START_X = 220;
	private static final int START_Y = 0;
	private static final BufferedImage myImage = Resources.getImage("playButtonImage");
	
	public PlayButton(Game game) {
		super(game, myImage, START_X, START_Y);
	}
	
	@Override
	public void actionPerformed() {
		((DropThis)this.myGame).startPlayState();
	}

}





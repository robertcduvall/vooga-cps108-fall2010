package vooga.games.grandius.sprites.buttons;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.games.grandius.Blah;
import vooga.widget.Button;

@SuppressWarnings("serial")
public class ShoppingLevelButton extends Button {

	private static final int START_X = 325;
	private static final int START_Y = 500;
	private static final BufferedImage myImage = Resources.getImage("shoppingButton");
	private Blah myGame;
	
	public ShoppingLevelButton(Game game) {
		super(myImage, START_X, START_Y);
		myGame = (Blah)game;
	}
	
	@Override
	public void actionPerformed() {
		myGame.getGameStateManager().switchTo(myGame.getGameStateManager().getGameState(2));
	}

}

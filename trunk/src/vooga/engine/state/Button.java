package vooga.engine.state;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.core.Sprite;
import vooga.engine.event.IEventHandler;

public abstract class Button extends Sprite implements IEventHandler{

	Game myGame;
	BufferedImage DEFAULT_BUTTON = new
	
	public Button (Game game){
		this(game, DEFAULT_BUTTON);
	}
	
	public Button (Game game, BufferedImage image){
		super("",image);
		myGame = game; 
	}
	
	
	
	@Override
	public boolean isTriggerred() {
		return myGame.click() && myGame.checkPosMouse(this, true);
	}

	@Override
	public abstract void actionPerformed();
}

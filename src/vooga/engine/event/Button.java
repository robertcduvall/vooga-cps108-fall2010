package vooga.engine.event;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.core.Sprite;

public abstract class Button extends Sprite implements IEventHandler{

	Game myGame;
	
	public Button(Game game){
		this(game, null, 0, 0);
	}
	
	public Button (Game game, BufferedImage image){
		this(game, image, 0, 0); 
	}
	
	public Button (Game game, BufferedImage image, double x, double y){
		super(image, x, y);
		myGame = game;
	}
	
	public Button(Game game, double x, double y){
		this(game, null, x, y);
	}
	
	@Override
	public boolean isTriggerred() {
		
		return myGame.click() && myGame.checkPosMouse(this, true);
	}

	@Override
	public abstract void actionPerformed();
}

package vooga.widget;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.core.BetterSprite;
import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;


/**
 * Reusable abstract Button implementation that represents a simple Sprite that allows for some action to be performed upon a mouse click
 * 
 * Right now, you must pass each Button an instance of the game it is in, which seems bad.  Ultimately we hope to find a way to call the Game methods without a specific instance of game
 * @author Brian
 *
 */
public abstract class Button extends BetterSprite implements IEventHandler{

	/**
	 * Instance of the game in which the Button exists
	 */
	protected Game myGame;
	
	
	/**
	 * Creates an instance of Button with a null image and a location of (0,0)
	 */
	public Button(){
		this(null, 0, 0);
		myGame = Resources.getGame();
	}
	
	/**
	 * Creates an instance of Button with a specified image and a location of (0,0)
	 * @param image Which represents the image of the button
	 */
	public Button (BufferedImage image){
		this(image, 0, 0);
	}
	
	/**
	 * Creates an instance of Button with a specified image and location
	 * @param image Which represents the image of the button
	 * @param x Which represents the X-coordinate of the button
	 * @param y Which represents the Y-coordinate of the button
	 */
	public Button (BufferedImage image, double x, double y){
		super(image, x, y);
		myGame = Resources.getGame();
	}
	
	/**
	 * Creates an instance of Button at a specified location with a null image
	 * @param x Which represents the X-coordinate of the button
	 * @param y Which represents the Y-coordinate of the button
	 */
	public Button(double x, double y){
		this(null, x, y);
	}

	
	/**
	 * Decides when button has been triggered
	 * @return whether the button has been triggered
	 */
	@Override
	public boolean isTriggered() {
		Game game = Resources.getGame();
		return game.click() && game.checkPosMouse(this, true);
	}

	/**
	 * Specifies what should be done when the button is clicked
	 */
	@Override
	public abstract void actionPerformed();
}

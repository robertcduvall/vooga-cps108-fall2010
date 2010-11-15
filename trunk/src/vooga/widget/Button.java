package vooga.widget;

import java.awt.image.BufferedImage;

import vooga.engine.core.Game;
import vooga.engine.core.Sprite;
import vooga.engine.event.IEventHandler;


/**
 * Reusable abstract Button implementation that represents a simple Sprite that allows for some action to be performed upon a mouse click
 * 
 * Right now, you must pass each Button an instance of the game it is in, which seems bad.  Ultimately we hope to find a way to call the Game methods without a specific instance of game
 * @author Brian Simel
 *
 */
public abstract class Button extends Sprite implements IEventHandler{

	private Game myGame;
	
	/**
	 * Creates an instance of Button with a game, a null image, and a location of (0,0)
	 * @param game Which represents the game 
	 */
	public Button(Game game){
		this(game, null, 0, 0);
	}
	
	/**
	 * Creates an instance of Button with a game and an image and a location of (0,0)
	 * @param game Which represents the game 
	 * @param image Which represents the image of the button
	 */
	public Button (Game game, BufferedImage image){
		this(game, image, 0, 0); 
	}
	
	/**
	 * Creates an instance of Button with a specified game, image, and location
	 * @param game Which represents the game 
	 * @param image Which represents the image of the button
	 * @param x Which represents the X-coordinate of the button
	 * @param y Which represents the Y-coordinate of the button
	 */
	public Button (Game game, BufferedImage image, double x, double y){
		super(image, x, y);
		myGame = game;
	}
	
	/**
	 * Creates an instance of Button at a specified location with a game and a null image
	 * @param game Which represents the game 
	 * @param x Which represents the X-coordinate of the button
	 * @param y Which represents the Y-coordinate of the button
	 */
	public Button(Game game, double x, double y){
		this(game, null, x, y);
	}
	
	/**
	 * Decides when button has been triggered
	 * @return whether the button has been triggered
	 */
	@Override
	public boolean isTriggered() {
		return myGame.click() && myGame.checkPosMouse(this, true);
	}

	/**
	 * Specifies what should be done when the button is clicked
	 */
	@Override
	public abstract void actionPerformed();
}

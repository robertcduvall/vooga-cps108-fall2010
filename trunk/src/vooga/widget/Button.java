package vooga.widget;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.golden.gamedev.util.ImageUtil;

import vooga.engine.core.Game;
import vooga.engine.core.BetterSprite;
import vooga.engine.event.IEventHandler;
import vooga.engine.overlay.OverlayLabel;
import vooga.engine.resource.Resources;


/**
 * Reusable abstract Button implementation that represents a simple 
 * Sprite that allows for some action to be performed upon a mouse click
 * Right now, you must pass each Button an instance of the game it is in,
 * which seems bad.  Ultimately we hope to find a way to call the Game 
 * methods without a specific instance of game.
 * @author Brian
 *
 */
public abstract class Button extends BetterSprite implements IEventHandler{

	/**
	 * Instance of the game in which the Button exists
	 */
	protected Game myGame;
	protected OverlayLabel buttonLabel = null;
	
	
	/**
	 * Creates an instance of Button with a default Button image and a location of (0,0)
	 */
	public Button(BufferedImage img){
		this(img, 0, 0);
	}	
	/**
	 * Creates an instance of Button with a default Button image and a location of (0,0)
	 * and the input String
	 */
	public Button(BufferedImage img, String label){
		this(img, 0, 0);
		setLabel(label);
		
	}

	/**
	 * Creates an instance of Button with a default Button image, a specified location, and a specified String
	 * and the input String
	 */
	public Button(BufferedImage img, String label, double x, double y){
		this(img, x, y);
		setLabel(label);
		
	}
	
	/**
	 * Creates an instance of Button with a specified image and location
	 * @param image Which represents the image of the button
	 * @param x Which represents the X-coordinate of the button
	 * @param y Which represents the Y-coordinate of the button
	 */
	public Button (BufferedImage img, double x, double y){
		super(img, x, y);
		myGame = Resources.getGame();
	}
	

	/**
	 * Changes the size of the Button
	 * @param width - desired width of the Button
	 * @param height - desired height of the Button
	 */
	public void setSize(int width, int height) {
		this.setImage( ImageUtil.resize( this.getImage(), width, height));
	}
	
	/**
	 * Places a String label on a Button
	 * @param text - String textual label for Button
	 */
	public void setLabel(String label) {
		setLabel( new OverlayLabel( this, label, Color.WHITE));
	}
	
	/**
	 * Places an OverlayLabel on a Button
	 * @param label - OverlayLabel to place on Button
	 */	
	public void setLabel(OverlayLabel label) {
		buttonLabel = label;
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
	
	@Override
	public void render(Graphics2D g) {
		super.render(g);
		if(!(buttonLabel==null))
			buttonLabel.render(g);
	}
}
package vooga.engine.overlay;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

/**
 * The OverlayStatImage class displays a GreenfootImage defined by the user
 * @author Se-Gil Feldsott and Justin Goldsmith
 * 
 * GreenfootImage image = new GreenfootImage("...");
 * OverlayStatImage overlay = new OverlayStatImage(image, 10, 10);
 *
 */
public class OverlayStatImage extends Overlay{
	
	/**
	 * Creats a Image to be placed in game.
	 * @param image image to display
	 */
	public OverlayStatImage(GreenfootImage image){
		setImage(image);
	}
	/**
	 * Creats a Image to be placed in game.
	 * @param image image to display		 
	 * @param width width of image
	 * @param height height of image
	 */
	public OverlayStatImage(GreenfootImage image, int width, int height){
		this(image);
		this.getImage().scale(width, height);
	}
	
	/**
	 * Creats a Image to be placed in game.
	 * @param location file location of image to be displayed
	 */
	public OverlayStatImage(String location){
		GreenfootImage image = new GreenfootImage(location);
		setImage(image);
	}
	
	/**
	 * Creats a Image to be placed in game.
	 * @param location file location of image to be displayed
	 * @param width width of image to be displayed
	 * @param height height of image to be displayed
	 */
	public OverlayStatImage(String location, int width, int height){
		this(location);
		this.getImage().scale(width, height);
	}
	
}

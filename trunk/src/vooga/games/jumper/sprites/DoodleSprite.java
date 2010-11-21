package vooga.games.jumper.sprites;

import java.awt.Point;
import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;
import vooga.games.jumper.DropThis;

/**
 * This class represents the Doodle which is the main character of the game
 * @author Brian
 */

public class DoodleSprite extends BetterSprite {


	private double X_ACCELERATION = 5;
	private double X_DECCELERATION = 1;
	private double GRAVITY = 0.1;
	private long ACCELERATION_TIME = 1;
	private double MAX_SPEED = 2.0;
	private double jetpackMultiplier = 3;

	private int mySpriteWidth = getWidth();
	private int mySpriteHeight = getHeight();

	private BufferedImage doodleLeftImage = Resources.getImage("leftDoodle");
	private BufferedImage doodleRightImage = Resources.getImage("rightDoodle");
	private BufferedImage doodleLeftImageJetpack = Resources.getImage("leftJetpackDoodle");
	private BufferedImage doodleRightImageJetpack = Resources.getImage("rightJetpackDoodle");

	private int gameHeight = Resources.getInt("gameHeight");
	private int gameWidth = Resources.getInt("gameWidth");
	
	/**
	 * Create new DoodleSprite
	 * @param image Sprite Image
	 * @param location Point representing starting sprite location
	 */

	
	
	public DoodleSprite(BufferedImage image, Point location) {
		super(image, location.x, location.y);  
	}

	public DoodleSprite(){
		
	}
	
	/**
	 * Update the doodle
	 * @param  elapsedTime long time elapsed from last update
	 */
	public void update(long elapsedTime){        
		move(getHorizontalSpeed(), getVerticalSpeed());
		deccelerate();
	}

	/**
	 * Slow doodle down by a fixed amount so that it decelerates when no key is pressed
	 */
	public void deccelerate(){
		if (getHorizontalSpeed() > 0)
			setHorizontalSpeed(getHorizontalSpeed() - X_DECCELERATION);
		if (getHorizontalSpeed() < 0)
			setHorizontalSpeed(getHorizontalSpeed() + X_DECCELERATION);
		if (getHorizontalSpeed() > -1 || getHorizontalSpeed() < 1)
			setHorizontalSpeed(0);
	}
	
	public void moveLeft(){	
		setHorizontalSpeed(-1* MAX_SPEED);
	}
	
	public void moveRight(){
		setHorizontalSpeed(MAX_SPEED);
	}
	
	public void moveUp(){
		System.out.println("up");
	}
}
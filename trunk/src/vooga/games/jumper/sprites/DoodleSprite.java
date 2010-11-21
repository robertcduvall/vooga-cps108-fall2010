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
	private double MAX_SPEED = 1;
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
	 * @param left BufferedImage representing Doodle when it is looking left
	 * @param right BufferedImage representing Doodle when it is looking right
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
		moveThroughWall();
		deccelerate();
		applyGravity();
		maintainFloor();
	}

	/**
	 * Make sure the doodle doesn't fall out of the screen from below
	 */
	public void maintainFloor(){
		if (getY() + mySpriteHeight > gameHeight){
			setY(gameHeight - mySpriteHeight);
		}
	}
	/**
	 * Apply gravity to the doodle
	 */
	public void applyGravity(){
		addVerticalSpeed(ACCELERATION_TIME, GRAVITY, MAX_SPEED);
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

	/**
	 * Allow doodle to move through wall to the other side
	 */
	public void moveThroughWall(){
		if (getX() + mySpriteWidth > gameWidth){
			setX(0);
		}
		if (getX() < 0){
			setX(gameWidth - mySpriteWidth);
		}
	}

/*	*//**
	 * Accelerate doodle
	 * @param direction representing the "right" or "left" direction
	 *//*	
	public void moveDoodle(String direction){
		
		int directionMultiplier = 1;
		if (direction.equals("left")){
			directionMultiplier = -1;
		}

		if(DropThis.isJetpackOn()){
			if (directionMultiplier == 1){
			setImage(doodleRightImageJetpack);
			}
			else{
				setImage(doodleLeftImageJetpack);
			}
			addHorizontalSpeed(	ACCELERATION_TIME, 
								jetpackMultiplier*X_ACCELERATION * directionMultiplier, 
								jetpackMultiplier*MAX_SPEED * directionMultiplier
								);
		} else{
			if (directionMultiplier == 1){
				setImage(doodleRightImage);
			}
			else{
				setImage(doodleLeftImage);
			}
			addHorizontalSpeed(ACCELERATION_TIME, X_ACCELERATION * directionMultiplier, MAX_SPEED * directionMultiplier);
		}
	}*/
	
	public void moveLeft(){	
		setHorizontalSpeed(-1* MAX_SPEED);
	}
	
	public void moveRight(){
		System.out.println("right");
		setHorizontalSpeed(MAX_SPEED);
//		setImage(doodleRightImage);
	}
	
	public void moveDown(){
		System.out.println("down");
	}
	
	public void moveUp(){
		System.out.println("up");
	}
}
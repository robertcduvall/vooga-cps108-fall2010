package vooga.games.jumper;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import com.golden.gamedev.object.Sprite;
/**
 * This class represents the Doodle which is the main character of the game
 * @author Brian
 *
 */
public class DoodleSprite extends Sprite {

	
	private double X_ACCELERATION = 5;
	private double X_DECCELERATION = 1;
	private double GRAVITY = 1;
	private long ACCELERATION_TIME = 1;
	private double MAX_SPEED = 10;
		
	private int mySpriteWidth = getWidth();
	private int mySpriteHeight = getHeight();
		
	public DoodleSprite(BufferedImage image, Point location) {
        super(image, location.x, location.y);      
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
    	int gameHeight = Jumper.getGameHeight();
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
    	int gameWidth = Jumper.getGameWidth();
    	
    	if (getX() + mySpriteWidth > Jumper.getGameWidth()){
    		setX(0);
    	}
    	if (getX() < 0){
    		setX(gameWidth - mySpriteWidth);
    	}
    }
    
    /**
     * Accelerate doodle to the right
     */
    public void goRight(){
    	addHorizontalSpeed(ACCELERATION_TIME, X_ACCELERATION, MAX_SPEED);
    }

    /**
     * Accelerate doodle to the left
     */
    public void goLeft(){
    	addHorizontalSpeed(ACCELERATION_TIME, -X_ACCELERATION, -MAX_SPEED);
    }
    
}
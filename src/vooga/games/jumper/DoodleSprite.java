package vooga.games.jumper;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import com.golden.gamedev.object.Sprite;

public class DoodleSprite extends Sprite {

	
	private double X_ACCELERATION = 5;
	private double X_DECCELERATION = 1;
		
	private long ACCELERATION_TIME = 1;
	private double MAX_SPEED = 10;
		
	private int mySpriteWidth = this.getWidth();
		
	public DoodleSprite(BufferedImage image, Point location) {
        super(image, location.x, location.y);      
        }

    public void update(long elapsedTime){        
    	move(this.getHorizontalSpeed(), this.getVerticalSpeed());
    	moveThroughWall();
    	deccelerate();
    }

    public void deccelerate(){
    	if (getHorizontalSpeed() > 0)
    		setHorizontalSpeed(getHorizontalSpeed() - X_DECCELERATION);
    	if (getHorizontalSpeed() < 0)
    		setHorizontalSpeed(getHorizontalSpeed() + X_DECCELERATION);
    	if (getHorizontalSpeed() > -1 || getHorizontalSpeed() < 1)
    		setHorizontalSpeed(0);
    }
    
    public void moveThroughWall(){
    	int gameWidth = Jumper.getGameWidth();
    	
    	if (getX() + mySpriteWidth > Jumper.getGameWidth()){
    		setX(0);
    	}
    	if (getX() < 0){
    		setX(gameWidth - mySpriteWidth);
    	}
    }
    
    public void goRight(){
    	addHorizontalSpeed(ACCELERATION_TIME, X_ACCELERATION, MAX_SPEED);
    }
    
    public void goLeft(){
    	addHorizontalSpeed(ACCELERATION_TIME, -X_ACCELERATION, -MAX_SPEED);
    }
    
    public void render(Graphics2D g){
    	super.render(g);
    	
    }
}
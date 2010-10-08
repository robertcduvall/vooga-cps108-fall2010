package vooga.games.jumper;

import java.awt.Point;
import java.awt.image.BufferedImage;
import com.golden.gamedev.object.Sprite;

public class DoodleSprite extends Sprite {

		private double myXLocation;
		private double myYLocation;
	
		private double myXSpeed;
		private double myYSpeed;
		
		private double X_ACCELERATION = 3;
		private double X_DECCELERATION = 1;
		
		private long ACCELERATION_TIME = 1;
		private double MAX_SPEED = 10;
		
		private int mySpriteWidth = this.getWidth();
		
        public DoodleSprite(BufferedImage image, Point location) {
        super(image, location.x, location.y);
//        myXSpeed = this.getHorizontalSpeed();
//        myYSpeed = this.getVerticalSpeed();
    }

    public void update(long elapsedTime){        
    	move(this.getHorizontalSpeed(), this.getVerticalSpeed());
    	moveThroughWall();
    	if (getHorizontalSpeed() > 0)
    		setHorizontalSpeed(getHorizontalSpeed() - X_DECCELERATION);
    }

    
    public void moveThroughWall(){
    	int gameWidth = Jumper.getGameWidth();
    	int spriteWidth = this.getWidth();
    	
    	if (getX() + spriteWidth > Jumper.getGameWidth()){
    		setX(0);
    	}
    	if (getX() < 0){
    		setX(gameWidth - spriteWidth);
    	}
    }
    
    public void goRight(){
    	this.addHorizontalSpeed(ACCELERATION_TIME, X_ACCELERATION, MAX_SPEED);
    }
}
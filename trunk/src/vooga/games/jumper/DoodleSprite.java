package vooga.games.jumper;

import java.awt.Point;
import java.awt.image.BufferedImage;
import com.golden.gamedev.object.Sprite;

public class DoodleSprite extends Sprite {

		private double myXLocation;
		private double myYLocation;
	
		private double myXSpeed;
		private double myYSpeed;
		
		private double X_ACCELERATION = 2;
		private double X_DECCELERATION = 1;
		
		private int mySpriteWidth = this.getWidth();
		
        public DoodleSprite(BufferedImage image, Point location) {
        super(image, location.x, location.y);
        myXLocation = location.x;
        myYLocation = location.y;
        myXSpeed = this.getHorizontalSpeed();
        myYSpeed = this.getVerticalSpeed();
    }

    public void update(long elapsedTime){        
    	move(myXSpeed, myYSpeed);
    	moveThroughWall();
    	if (myXSpeed > 0)
    		setHorizontalSpeed(myXSpeed - X_DECCELERATION);
    }

    
    public void moveThroughWall(){
    	int gameWidth = Jumper.getGameWidth();
    	int spriteWidth = this.getWidth();
    	
    	if (myXLocation + spriteWidth > Jumper.getGameWidth()){
    		setX(0);
    	}
    	if (myXLocation < 0){
    		setX(gameWidth - spriteWidth);
    	}
    }
    
    public void goRight(){
    	this.addHorizontalSpeed(1, 3, 10);
    }
}
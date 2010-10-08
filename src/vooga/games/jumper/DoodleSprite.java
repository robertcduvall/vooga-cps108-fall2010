package vooga.games.jumper;

import java.awt.Point;
import java.awt.image.BufferedImage;
import com.golden.gamedev.object.Sprite;

public class DoodleSprite extends Sprite {

        public DoodleSprite(BufferedImage image, Point location) {
        super(image, location.x, location.y);
    }

    public void update(long elapsedTime){        
    	move(this.getHorizontalSpeed(), this.getVerticalSpeed());
    	moveThroughWall();
    }

    
    public void moveThroughWall(){
    	int gameWidth = Jumper.getGameWidth();
    	int xLocation = (int) this.getX();
    	int spriteWidth = this.getWidth();
    	
    	if (xLocation + spriteWidth > Jumper.getGameWidth()){
    		setX(0);
    	}
    	if (xLocation < 0){
    		setX(gameWidth - spriteWidth);
    	}
    }
}
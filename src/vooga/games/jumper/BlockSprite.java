package vooga.games.jumper;

import java.awt.Point;
import java.awt.image.BufferedImage;
import com.golden.gamedev.object.Sprite;

/**
 * This class represents the Blocks which push the Doodle
 * @author Brian
 *
 */
public class BlockSprite extends Sprite {

        public BlockSprite(BufferedImage image, Point location) {
        super(image, location.x, location.y);
    }

    /**
     * Overriding Sprite update method to include bouncing off wall
     * @param elapsedTime long time elapsed from last update
     */
    public void update(long elapsedTime){
    	move(getHorizontalSpeed(), getVerticalSpeed());
    	bounceOnWall();
    }

    /**
     * Bounce BlockSprite off wall if it touches the side
     */
    public void bounceOnWall(){
    	if (this.getX() + this.getWidth() > Jumper.getGameWidth() || this.getX() < 0){
    		this.setHorizontalSpeed(this.getHorizontalSpeed() * -1);
    	}
    }
}
package vooga.games.jumper;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import com.golden.gamedev.object.Sprite;

public class BlockSprite extends Sprite {

        public BlockSprite(BufferedImage image, Point location) {
        super(image, location.x, location.y);
    }

    public void update(long elapsedTime){
    	move(getHorizontalSpeed(), getVerticalSpeed());
    	bounceOnWall();

    }

    public void bounceOnWall(){
    	if (this.getX() + this.getWidth() > Jumper.getGameWidth() || this.getX() < 0){
    		this.setHorizontalSpeed(this.getHorizontalSpeed() * -1);
    	}
    }
}
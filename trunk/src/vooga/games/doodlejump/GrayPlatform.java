package vooga.games.doodlejump;

import java.awt.image.BufferedImage;
import com.golden.gamedev.object.Sprite;

public class GrayPlatform extends Sprite{
	private int changeDirection;
	public GrayPlatform(BufferedImage image, double x, double y){
		super(image, x, y);
		changeDirection = 300;
	}
	
	@Override
	public void update(long elapsedTime){
		super.update(elapsedTime);
		changeDirection--;
		if(changeDirection <= 0){
			setVerticalSpeed(getVerticalSpeed() * -1);
			changeDirection = 300;
		}
	}
}

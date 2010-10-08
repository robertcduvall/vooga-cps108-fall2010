package vooga.games.doodlejump;

import java.awt.image.BufferedImage;
import com.golden.gamedev.object.Sprite;

public class LightBluePlatform extends Sprite{
	public LightBluePlatform(BufferedImage image, double x, double y){
		super(image, x, y);
	}
	
	@Override
	public void update(long elapsedTime){
		super.update(elapsedTime);
		if(getX() < 0 || getX() > 532 - getWidth())
			setHorizontalSpeed(getHorizontalSpeed() * -1);
	}
}

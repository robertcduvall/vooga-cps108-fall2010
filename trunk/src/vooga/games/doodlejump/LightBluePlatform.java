package vooga.games.doodlejump;

import java.awt.image.BufferedImage;
import com.golden.gamedev.object.Sprite;

/**
 * The LightBluePlatform class extends Sprite and defines a LightBluePlatform
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 *
 */
public class LightBluePlatform extends Sprite{
	public LightBluePlatform(BufferedImage image, double x, double y){
		super(image, x, y);
		setHorizontalSpeed(-0.1);
	}
	
	@Override
	public void update(long elapsedTime){
		super.update(elapsedTime);
		if(getX() < 0 || getX() > 532 - getWidth())
			setHorizontalSpeed(getHorizontalSpeed() * -1);
	}
}

package vooga.games.doodlejump.platforms;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

/**
 * The GrayPlatform class extends Sprite and defines a GrayPlatform
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class GrayPlatform extends BetterSprite {
	private int changeDirection;
	
	public GrayPlatform(){
		this(Resources.getImage("grayPlatform"));
	}

	public GrayPlatform(BufferedImage image) {
		super(image);
	}
	
	@Override
	public void firstRun(){
		changeDirection = 300;
		setVerticalSpeed(-0.1);
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		changeDirection--;
		if (changeDirection <= 0) {
			setVerticalSpeed(getVerticalSpeed() * -1);
			changeDirection = 300;
		}
	}
}

package vooga.games.doodlejump.platforms;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

/**
 * The LightBluePlatform class extends Sprite and defines a LightBluePlatform
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class LightBluePlatform extends BetterSprite {

	private static final String LIGHT_BLUE_PLATFORM_STRING = "lightBluePlatform";
	private static final String MIN_SCREEN_X_STRING = "minScreenX";
	private static final String MAX_SCREEN_X_STRING = "maxScreenX";
	private static final double HORIZONTAL_SPEED = -0.1;
	private static final double MULTIPLY_HORIZONTAL_SPEED = -1;

	public LightBluePlatform() {
		this(Resources.getImage(LIGHT_BLUE_PLATFORM_STRING));
	}

	public LightBluePlatform(BufferedImage image) {
		super(image);
	}

	@Override
	public void firstRun() {
		setHorizontalSpeed(HORIZONTAL_SPEED);
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		checkInBounds();
	}

	private void checkInBounds() {
		if (getX() < Resources.getDouble(MIN_SCREEN_X_STRING)
				|| getX() > Resources.getDouble(MAX_SCREEN_X_STRING)
						- getWidth())
			setHorizontalSpeed(getHorizontalSpeed() * MULTIPLY_HORIZONTAL_SPEED);
	}
}

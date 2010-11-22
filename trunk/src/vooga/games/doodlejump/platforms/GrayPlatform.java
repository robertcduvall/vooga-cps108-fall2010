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

	private static final String GRAY_PLATFORM_STRING = "grayPlatform";
	private static final String CHANGE_DIRECTION_STRING = "changeDirection";
	private static final double VERTICAL_SPEED = -0.1;
	private static final double MULTIPLY_VERTICAL_SPEED = -1;
	private static final int ZERO_CHECK_DIRECTION = 0;

	private int changeDirection;

	public GrayPlatform() {
		this(Resources.getImage(GRAY_PLATFORM_STRING));
	}

	public GrayPlatform(BufferedImage image) {
		super(image);
	}

	@Override
	public void firstRun() {
		changeDirection = Resources.getInt(CHANGE_DIRECTION_STRING);
		setVerticalSpeed(VERTICAL_SPEED);
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		changeDirection--;
		if (changeDirection <= ZERO_CHECK_DIRECTION) {
			setVerticalSpeed(getVerticalSpeed() * MULTIPLY_VERTICAL_SPEED);
			changeDirection = Resources.getInt(CHANGE_DIRECTION_STRING);
		}
	}
}

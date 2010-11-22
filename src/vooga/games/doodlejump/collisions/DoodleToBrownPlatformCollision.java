package vooga.games.doodlejump.collisions;

import vooga.games.doodlejump.DoodleSprite;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

/**
 * The DoodleToBrownPlatformCollision class extends BasicCollisionGroup and
 * defines what happens when Doodle collides with a Brown Platform
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class DoodleToBrownPlatformCollision extends BasicCollisionGroup {

	private static final int ZERO_VERTICAL_SPEED = 0;
	private static final int SUBTRACT_HEIGHT = 10;
	private static final int INITIAL_ANIMATION_FRAME = 0;
	private static final int FINAL_ANIMATION_FRAME = 3;
	private static final double BROWN_PLATFORM_VERTICAL_SPEED = 0.4;

	public DoodleToBrownPlatformCollision(Game game) {
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite doodle, Sprite brown_platform) {
		if (doodle.getVerticalSpeed() > ZERO_VERTICAL_SPEED
				&& doodle.getY() + doodle.getHeight() - SUBTRACT_HEIGHT < brown_platform
						.getY()
				&& !((AnimatedSprite) brown_platform).isAnimate()
				&& !((DoodleSprite) doodle).getDied()) {
			((AnimatedSprite) brown_platform).setAnimationFrame(
					INITIAL_ANIMATION_FRAME, FINAL_ANIMATION_FRAME);
			((AnimatedSprite) brown_platform).setLoopAnim(false);
			((AnimatedSprite) brown_platform).setAnimate(true);
			((AnimatedSprite) brown_platform).setAnimationFrame(
					FINAL_ANIMATION_FRAME, FINAL_ANIMATION_FRAME);
			brown_platform.setVerticalSpeed(BROWN_PLATFORM_VERTICAL_SPEED);
		}
	}
}
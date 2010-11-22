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

	public DoodleToBrownPlatformCollision(Game game) {
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite doodle, Sprite brown_platform) {
		if (doodle.getVerticalSpeed() > 0
				&& doodle.getY() + doodle.getHeight() - 10 < brown_platform
						.getY()
				&& !((AnimatedSprite) brown_platform).isAnimate() && !((DoodleSprite) doodle).getDied()) {
			((AnimatedSprite) brown_platform).setAnimationFrame(0, 3);
			((AnimatedSprite) brown_platform).setLoopAnim(false);
			((AnimatedSprite) brown_platform).setAnimate(true);
			((AnimatedSprite) brown_platform).setAnimationFrame(3, 3);
			brown_platform.setVerticalSpeed(0.4);
		}
	}
}
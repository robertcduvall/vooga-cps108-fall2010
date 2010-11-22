package vooga.games.doodlejump.collisions;

import vooga.engine.core.Game;
import vooga.games.doodlejump.DoodleSprite;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

/**
 * The DoodleToWhitePlatformCollision class extends BasicCollisionGroup and
 * defines what happens when Doodle collides with a White Platform
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class DoodleToWhitePlatformCollision extends BasicCollisionGroup {

	private static final int ZERO_VERTICAL_SPEED = 0;
	private static final int SUBTRACT_HEIGHT = 15;
	private static final double DOODLE_VERTICAL_SPEED = -0.5;

	public DoodleToWhitePlatformCollision(Game game) {
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite doodle, Sprite white_platform) {
		if (doodle.getVerticalSpeed() > ZERO_VERTICAL_SPEED
				&& doodle.getY() + doodle.getHeight() - SUBTRACT_HEIGHT < white_platform
						.getY() && !((DoodleSprite) doodle).getDied()) {
			white_platform.setActive(false);
			doodle.setVerticalSpeed(DOODLE_VERTICAL_SPEED);
		}
	}

}

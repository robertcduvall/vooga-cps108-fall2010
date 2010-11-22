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

	public DoodleToWhitePlatformCollision(Game game) {
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite doodle, Sprite white_platform) {
		// TODO Auto-generated method stub
		if (doodle.getVerticalSpeed() > 0
				&& doodle.getY() + doodle.getHeight() - 15 < white_platform
						.getY() && !((DoodleSprite) doodle).getDied()) {
			white_platform.setActive(false);
			doodle.setVerticalSpeed(-0.5);
		}
	}

}

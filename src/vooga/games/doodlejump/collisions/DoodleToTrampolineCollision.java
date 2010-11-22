package vooga.games.doodlejump.collisions;

import vooga.engine.core.BetterSprite;
import vooga.engine.core.Game;
import vooga.games.doodlejump.DoodleSprite;

import com.golden.gamedev.object.*;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

/**
 * The DoodleToTrampolineCollision class extends BasicCollisionGroup and defines
 * what happens when Doodle collides with a Trampoline
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class DoodleToTrampolineCollision extends BasicCollisionGroup {

	public DoodleToTrampolineCollision(Game game) {
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite doodle, Sprite trampoline) {
		BetterSprite tramp = (BetterSprite) trampoline;
		if (doodle.getVerticalSpeed() > 0
				&& doodle.getY() + doodle.getHeight() - 10 < tramp.getY()
				&& !((AnimatedSprite) tramp.getCurrentSprite()).isAnimate() && !((DoodleSprite) doodle).getDied()) {
			((AnimatedSprite) tramp.getCurrentSprite()).setAnimationFrame(0, 1);
			((AnimatedSprite) tramp.getCurrentSprite()).setLoopAnim(false);
			((AnimatedSprite) tramp.getCurrentSprite()).setAnimate(true);
			doodle.setVerticalSpeed(-1);
		}
	}
}
package vooga.games.doodlejump.collisions;

import vooga.engine.core.Game;

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
		if (doodle.getVerticalSpeed() > 0
				&& doodle.getY() + doodle.getHeight() - 10 < trampoline.getY()
				&& !((AnimatedSprite) trampoline).isAnimate()) {
			((AnimatedSprite) trampoline).setAnimationFrame(0, 1);
			((AnimatedSprite) trampoline).setLoopAnim(false);
			((AnimatedSprite) trampoline).setAnimate(true);
			doodle.setVerticalSpeed(-1);
		}
	}
}
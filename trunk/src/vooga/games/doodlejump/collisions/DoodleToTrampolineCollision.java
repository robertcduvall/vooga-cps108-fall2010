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

	private static final int ZERO_VERTICAL_SPEED = 0;
	private static final int SUBTRACT_HEIGHT = 10;
	private static final int INITIAL_ANIMATION_FRAME = 0;
	private static final int FINAL_ANIMATION_FRAME = 1;
	private static final int DOODLE_VERTICAL_SPEED = -1;

	public DoodleToTrampolineCollision(Game game) {
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite doodle, Sprite trampoline) {
		BetterSprite tramp = (BetterSprite) trampoline;
		if (doodle.getVerticalSpeed() > ZERO_VERTICAL_SPEED
				&& doodle.getY() + doodle.getHeight() - SUBTRACT_HEIGHT < tramp
						.getY()
				&& !((AnimatedSprite) tramp.getCurrentSprite()).isAnimate()
				&& !((DoodleSprite) doodle).getDied()) {
			((AnimatedSprite) tramp.getCurrentSprite()).setAnimationFrame(
					INITIAL_ANIMATION_FRAME, FINAL_ANIMATION_FRAME);
			((AnimatedSprite) tramp.getCurrentSprite()).setLoopAnim(false);
			((AnimatedSprite) tramp.getCurrentSprite()).setAnimate(true);
			doodle.setVerticalSpeed(DOODLE_VERTICAL_SPEED);
		}
	}
}
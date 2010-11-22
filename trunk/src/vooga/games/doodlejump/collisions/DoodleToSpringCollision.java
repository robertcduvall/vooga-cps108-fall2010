package vooga.games.doodlejump.collisions;

import java.applet.AudioClip;
import java.net.URL;

import vooga.engine.core.BetterSprite;
import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.engine.util.SoundPlayer;
import vooga.games.doodlejump.DoodleSprite;

import com.golden.gamedev.object.*;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

/**
 * The DoodleToSpringCollision class extends BasicCollisionGroup and defines
 * what happens when Doodle collides with a Spring
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class DoodleToSpringCollision extends BasicCollisionGroup {

	private static final int ZERO_VERTICAL_SPEED = 0;
	private static final int SUBTRACT_HEIGHT = 10;
	private static final int INITIAL_ANIMATION_FRAME = 0;
	private static final int FINAL_ANIMATION_FRAME = 1;
	private static final double DOODLE_VERTICAL_SPEED = -1.7;

	public DoodleToSpringCollision(Game game) {
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite doodle, Sprite spring) {
		BetterSprite spr = (BetterSprite) spring;
		if (doodle.getVerticalSpeed() > ZERO_VERTICAL_SPEED
				&& doodle.getY() + doodle.getHeight() - SUBTRACT_HEIGHT < spr
						.getY()
				&& !((AnimatedSprite) spr.getCurrentSprite()).isAnimate()
				&& !((DoodleSprite) doodle).getDied()) {
			spr.moveY(-12);
			((AnimatedSprite) spr.getCurrentSprite()).setAnimationFrame(
					INITIAL_ANIMATION_FRAME, FINAL_ANIMATION_FRAME);
			((AnimatedSprite) spr.getCurrentSprite()).setLoopAnim(false);
			((AnimatedSprite) spr.getCurrentSprite()).setAnimate(true);
			((AnimatedSprite) spr.getCurrentSprite()).setAnimationFrame(
					FINAL_ANIMATION_FRAME, FINAL_ANIMATION_FRAME);
			doodle.setVerticalSpeed(DOODLE_VERTICAL_SPEED);
			// SoundPlayer.playSound(Resources.getSound("springSound"));
		}
	}
}
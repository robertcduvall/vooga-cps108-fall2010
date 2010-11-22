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

	public DoodleToSpringCollision(Game game) {
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite doodle, Sprite spring) {
		BetterSprite spr = (BetterSprite) spring;
		if (doodle.getVerticalSpeed() > 0
				&& doodle.getY() + doodle.getHeight() - 10 < spr.getY()
				&& !((AnimatedSprite) spr.getCurrentSprite()).isAnimate() && !((DoodleSprite) doodle).getDied()) {
			spr.moveY(-12);
			((AnimatedSprite) spr.getCurrentSprite()).setAnimationFrame(0, 1);
			((AnimatedSprite) spr.getCurrentSprite()).setLoopAnim(false);
			((AnimatedSprite) spr.getCurrentSprite()).setAnimate(true);
			((AnimatedSprite) spr.getCurrentSprite()).setAnimationFrame(1, 1);
			doodle.setVerticalSpeed(-1.7);
			SoundPlayer.playSound(Resources.getSound("springSound"));
		}
	}
}
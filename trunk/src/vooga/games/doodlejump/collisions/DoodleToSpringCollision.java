package vooga.games.doodlejump.collisions;

import java.applet.AudioClip;
import java.net.URL;

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

	public DoodleToSpringCollision() {
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite doodle, Sprite spring) {
		if (doodle.getVerticalSpeed() > 0
				&& doodle.getY() + doodle.getHeight() - 10 < spring.getY()
				&& !((AnimatedSprite) spring).isAnimate()) {
			spring.moveY(-12);
			((AnimatedSprite) spring).setAnimationFrame(0, 1);
			((AnimatedSprite) spring).setLoopAnim(false);
			((AnimatedSprite) spring).setAnimate(true);
			((AnimatedSprite) spring).setAnimationFrame(1, 1);
			doodle.setVerticalSpeed(-1.7);
			URL spring_url = getClass().getResource("../resources/sounds/spring_sound.wav");
			AudioClip clip = java.applet.Applet.newAudioClip(spring_url);
			clip.play();
		}
	}
}
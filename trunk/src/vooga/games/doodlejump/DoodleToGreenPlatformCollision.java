package vooga.games.doodlejump;

import java.applet.AudioClip;
import java.net.URL;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

/**
 * The DoodleToGreenPlatformCollision class extends BasicCollisionGroup and
 * defines what happens when Doodle collides with a Green Platform
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class DoodleToGreenPlatformCollision extends BasicCollisionGroup {

	public DoodleToGreenPlatformCollision() {
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite doodle, Sprite green_platform) {

		/*
		 * Borrowed Code from http://forums.sun.com/thread.jspa?threadID=5320753
		 */
		if (doodle.getVerticalSpeed() > 0
				&& doodle.getY() + doodle.getHeight() - 15 < green_platform
						.getY()) {
			URL jump_url = getClass().getResource("sounds/jump_sound.wav");
			doodle.setVerticalSpeed(-0.6);
			AudioClip clip = java.applet.Applet.newAudioClip(jump_url);
			clip.play();
		}
	}
}

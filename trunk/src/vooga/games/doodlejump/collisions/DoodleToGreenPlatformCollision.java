package vooga.games.doodlejump.collisions;

import java.applet.AudioClip;
import java.net.URL;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.engine.util.SoundPlayer;
import vooga.games.doodlejump.DoodleSprite;

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

	private static final int ZERO_VERTICAL_SPEED = 0;
	private static final int SUBTRACT_HEIGHT = 15;
	private static final double DOODLE_VERTICAL_SPEED = -0.6;

	private static final String JUMP_SOUND_STRING = "jumpSound";

	public DoodleToGreenPlatformCollision(Game game) {
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite doodle, Sprite green_platform) {
		if (doodle.getVerticalSpeed() > ZERO_VERTICAL_SPEED
				&& doodle.getY() + doodle.getHeight() - SUBTRACT_HEIGHT < green_platform
						.getY() && !((DoodleSprite) doodle).getDied()) {
			doodle.setVerticalSpeed(DOODLE_VERTICAL_SPEED);
			SoundPlayer.playSound(Resources.getSound(JUMP_SOUND_STRING));
		}
	}
}

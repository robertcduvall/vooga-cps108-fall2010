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

	public DoodleToGreenPlatformCollision(Game game) {
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite doodle, Sprite green_platform) {
		if (doodle.getVerticalSpeed() > 0
				&& doodle.getY() + doodle.getHeight() - 15 < green_platform
						.getY() && !((DoodleSprite) doodle).getDied()) {
			doodle.setVerticalSpeed(-0.6);
			SoundPlayer.playSound(Resources.getSound("jumpSound"));
		}
	}
}

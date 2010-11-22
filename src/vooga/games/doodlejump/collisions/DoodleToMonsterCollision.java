package vooga.games.doodlejump.collisions;

import java.applet.AudioClip;
import java.net.URL;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.engine.util.SoundPlayer;
import vooga.games.doodlejump.*;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

/**
 * The DoodleToMonsterCollision class extends BasicCollisionGroup and defines
 * what happens when Doodle collides with a Monster
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class DoodleToMonsterCollision extends BasicCollisionGroup {

	private static final int ZERO_VERTICAL_SPEED = 0;
	private static final int MULTIPLY_VERTICAL_SPEED = -1;
	private static final double DOODLE_VERTICAL_SPEED = -0.5;
	private static final String BUZZER_SOUND_STRING = "buzzerSound";

	public DoodleToMonsterCollision(Game game) {
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite doodle, Sprite monster) {
		if (!((DoodleSprite) doodle).getDied()) {
			if (doodle.getVerticalSpeed() < ZERO_VERTICAL_SPEED) {
				((DoodleSprite) doodle).setDied(true);
				((DoodleSprite) doodle)
						.setVerticalSpeed(((DoodleSprite) doodle)
								.getVerticalSpeed() * MULTIPLY_VERTICAL_SPEED);
				SoundPlayer.playSound(Resources.getSound(BUZZER_SOUND_STRING));
			} else {
				doodle.setVerticalSpeed(DOODLE_VERTICAL_SPEED);
				monster.setActive(false);
			}
		}
	}
}
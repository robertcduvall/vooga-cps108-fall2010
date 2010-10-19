package vooga.games.doodlejump;

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

	public DoodleToMonsterCollision() {
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite doodle, Sprite monster) {
		if (doodle.getVerticalSpeed() < 0) {
			((DoodleSprite) doodle).setDied(true);
			monster.setActive(false);
		} else {
			doodle.setVerticalSpeed(-0.5);
			monster.setActive(false);
		}
	}
}
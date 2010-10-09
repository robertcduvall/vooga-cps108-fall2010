package vooga.games.doodlejump;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

/**
 * The BallToMonsterCollision class extends BasicCollisionGroup and defines what
 * happens when a Ball collides with a Monster
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class BallToMonsterCollision extends BasicCollisionGroup {
	public BallToMonsterCollision() {
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite ball, Sprite monster) {
		((BallSprite) ball).setActive(false);
		monster.setActive(false);
	}
}

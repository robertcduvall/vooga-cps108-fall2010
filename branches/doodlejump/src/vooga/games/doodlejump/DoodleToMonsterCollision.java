package vooga.games.doodlejump;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class DoodleToMonsterCollision extends BasicCollisionGroup {

	public DoodleToMonsterCollision() {
		pixelPerfectCollision = true;
	}

	public void collided(Sprite doodle, Sprite monster) {

		doodle.setActive(false);
		monster.setActive(false);
	}
}
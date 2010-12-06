package vooga.games.tronlegacy.collisions;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class TronCollision extends BasicCollisionGroup {
	@Override
	public void collided(Sprite s1, Sprite s2) {
		s1.setActive(false);
		s2.setActive(false);
	}

}

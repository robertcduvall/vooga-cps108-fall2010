package vooga.games.galaxyinvaders;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class TorpedoBlockCollider extends BasicCollisionGroup {

	public TorpedoBlockCollider() {
		super();
		pixelPerfectCollision = true;
	}
	
	@Override
	public void collided(Sprite s1, Sprite s2) {
		s1.setActive(false);
		((BlockadeSprite) s2).decrementHitPoints();
	}

}

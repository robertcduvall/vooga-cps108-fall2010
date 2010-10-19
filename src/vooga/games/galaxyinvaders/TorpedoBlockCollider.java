package vooga.games.galaxyinvaders;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

/**
 * This class is an extension of BasicCollisionGroup, it handles collisions 
 * between torpedoes and blockades.
 * 
 * @author Drew Sternesky, Kate Yang, Nick Hawthorne
 *
 */
public class TorpedoBlockCollider extends BasicCollisionGroup {

	private static final int DAMAGE_TAKEN = 2;
	
	public TorpedoBlockCollider() {
		super();
		pixelPerfectCollision = true;
	}
	
	@Override
	public void collided(Sprite s1, Sprite s2) {
		s1.setActive(false);
		((BlockadeSprite) s2).decrementHitPoints(DAMAGE_TAKEN);
	}

}

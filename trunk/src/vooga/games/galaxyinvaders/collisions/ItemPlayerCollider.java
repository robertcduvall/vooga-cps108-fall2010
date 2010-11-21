package vooga.games.galaxyinvaders.collisions;

import vooga.engine.core.BetterSprite;
import vooga.engine.overlay.Stat;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
/**
 * This class is an extension of BasicCollisionGroup, it handles collisions 
 * between powerups and players.
 * 
 * @author Drew Sternesky, Kate Yang, Nick Hawthorne
 *
 */
public class ItemPlayerCollider extends BasicCollisionGroup {

	private static int POWER_UP_AMOUNT = 1;
	
	public ItemPlayerCollider() {
		super();
		pixelPerfectCollision = true;
	}
	
	@Override
	public void collided(Sprite s1, Sprite s2) {
		s1.setActive(false);
		Integer lives = (Integer)((BetterSprite) s2).getStat("lives").getStat();
		lives += POWER_UP_AMOUNT;
	}

}

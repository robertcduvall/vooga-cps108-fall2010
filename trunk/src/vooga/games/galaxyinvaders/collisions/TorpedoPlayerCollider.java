package vooga.games.galaxyinvaders.collisions;


import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.*;
import vooga.engine.core.BetterSprite;
import vooga.engine.core.Game;

/**
 * This class is an extension of BasicCollisionGroup, it handles collisions 
 * between torpedoes and players.
 * 
 * @author Drew Sternesky, Kate Yang, Nick Hawthorne
 *
 */
public class TorpedoPlayerCollider extends BasicCollisionGroup {
	
	private static final int LIVES_LOST = 1;
	
	public TorpedoPlayerCollider(Game g) {
		super();
		pixelPerfectCollision = true;
	}
	
	@Override
	public void collided(Sprite s1, Sprite s2) {
		s1.setActive(false);
		Integer lives = (Integer)((BetterSprite) s2).getStat("lives").getStat();
		lives -= LIVES_LOST;
		s2.setX(350);
	}

}

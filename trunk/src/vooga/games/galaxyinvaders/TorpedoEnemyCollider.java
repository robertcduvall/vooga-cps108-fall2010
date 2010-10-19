package vooga.games.galaxyinvaders;

import vooga.engine.player.control.*;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

/**
 * This class is an extension of BasicCollisionGroup, it handles collisions 
 * between torpedoes and enemies.
 * 
 * @author Drew Sternesky, Kate Yang, Nick Hawthorne
 *
 */
public class TorpedoEnemyCollider extends BasicCollisionGroup {
	
	private GalaxyInvaders g;
	
	private static final int ENEMY_DAMAGE_TAKEN = 1;
	private static final int SCORE_PER_HIT = 10;
	
	public TorpedoEnemyCollider(GalaxyInvaders game) {
		super();
		g = game;
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite s1, Sprite s2) {
		s1.setActive(false);
		((EnemySprite) s2).decrementHitPoints(ENEMY_DAMAGE_TAKEN);
		g.increasePlayerScore(SCORE_PER_HIT);
	}
	

}

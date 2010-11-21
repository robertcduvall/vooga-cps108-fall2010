package vooga.games.galaxyinvaders.collisions;


import vooga.engine.core.Game;
import vooga.games.galaxyinvaders.sprites.EnemySprite;
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
	
	private static final int ENEMY_DAMAGE_TAKEN = 1;
	private static final int SCORE_PER_HIT = 10;
	
	public TorpedoEnemyCollider(Game g) {
		super();
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite s1, Sprite s2) {
		s1.setActive(false);
		((EnemySprite) s2).decrementHitPoints(ENEMY_DAMAGE_TAKEN);
//		Integer score = (Integer)((EnemySprite) s2).getStat("score").getStat();
//		score += SCORE_PER_HIT;
	}
	

}

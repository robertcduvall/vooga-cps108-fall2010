package vooga.games.galaxyinvaders;

import vooga.engine.player.control.*;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class TorpedoEnemyCollider extends BasicCollisionGroup {
	
	private GalaxyInvaders g;
	
	public TorpedoEnemyCollider(GalaxyInvaders game) {
		super();
		g = game;
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite s1, Sprite s2) {
		s1.setActive(false);
		((EnemySprite) s2).decrementHitPoints();
		g.increasePlayerScore();
	}
	

}

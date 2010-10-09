package vooga.games.galaxyinvaders;

import vooga.engine.player.control.PlayerSprite;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class ItemPlayerCollider extends BasicCollisionGroup {

	public ItemPlayerCollider() {
		super();
		pixelPerfectCollision = true;
	}
	
	@Override
	public void collided(Sprite s1, Sprite s2) {
		s1.setActive(false);
		((PlayerSprite)s2).updateLives(2);
		
	}
	
	

}

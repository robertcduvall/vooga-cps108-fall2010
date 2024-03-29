package vooga.games.asteroids.collisions;

import vooga.engine.overlay.Stat;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

public class ShipToAsteroidCollision extends AdvanceCollisionGroup {

	public ShipToAsteroidCollision(){
		pixelPerfectCollision = true;
	}
	
	@Override
	public void collided(Sprite ship, Sprite asteroid) {
		asteroid.setActive(false);
		Stat<Integer> score = (Stat<Integer>) ((vooga.engine.core.BetterSprite) ship).getStat("lives");
		score.setStat(score.getStat() -1);
	}

	
	
}

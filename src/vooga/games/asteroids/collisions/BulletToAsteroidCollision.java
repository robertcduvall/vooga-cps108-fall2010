package vooga.games.asteroids.collisions;


import vooga.engine.overlay.Stat;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

public class BulletToAsteroidCollision extends AdvanceCollisionGroup{
	
	private static final int ADD_PER_HIT = 10;

	public BulletToAsteroidCollision(){
		pixelPerfectCollision = true;
	}
	
	@Override
	public void collided(Sprite bullet, Sprite asteroid) {
		bullet.setActive(false);
		asteroid.setActive(false);
	
		Stat<Integer> score = (Stat<Integer>) ((vooga.engine.core.BetterSprite) asteroid).getStat("score");
		score.setStat(score.getStat() + ADD_PER_HIT);
		//TODO:play sound
		//TODO:add score
		//TODO: create smaller asteroids
	}

}

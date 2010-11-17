package vooga.games.asteroids.collisions;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

public class ShipToAsteroidCollision extends AdvanceCollisionGroup {

	public ShipToAsteroidCollision(){
		pixelPerfectCollision = true;
	}
	
	@Override
	public void collided(Sprite ship, Sprite asteroid) {
		asteroid.setActive(false);
		//TODO:reduce life
		
	}

	
	
}

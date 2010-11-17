package vooga.games.asteroids;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

public class ShipToAsteroidCollision extends AdvanceCollisionGroup {

	public ShipToAsteroidCollision(){
		pixelPerfectCollision = true;
	}
	
	@Override
	public void collided(Sprite ship, Sprite asteroid) {
		
	}

	
	
}

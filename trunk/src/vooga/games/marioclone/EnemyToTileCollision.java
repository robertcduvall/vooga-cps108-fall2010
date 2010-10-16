package vooga.games.marioclone;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.PreciseCollisionGroup;

public class EnemyToTileCollision extends PreciseCollisionGroup {

	@Override
	public void collided(Sprite enemy, Sprite tile) {
		revertPosition1();

		if (collisionSide == LEFT_RIGHT_COLLISION
				|| collisionSide == RIGHT_LEFT_COLLISION)
			((Enemy) enemy).bounce();

		
		enemy.setVerticalSpeed(0);
		
	}

}

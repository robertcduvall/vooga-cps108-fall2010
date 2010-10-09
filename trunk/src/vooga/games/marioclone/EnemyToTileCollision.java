package vooga.games.marioclone;

import vooga.games.marioclone.tiles.Tile;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.PreciseCollisionGroup;

public class EnemyToTileCollision extends PreciseCollisionGroup {

	@Override
	public void collided(Sprite enemy, Sprite tile) {
		revertPosition1();
		enemy.setHorizontalSpeed(-enemy.getHorizontalSpeed());
		
		enemy.setVerticalSpeed(0);
		

	}

}

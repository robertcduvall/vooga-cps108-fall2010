package vooga.games.mariocloneold;

import com.golden.gamedev.object.Sprite;

public class EnemyToTileCollision extends BetterCollisionGroup {

	@Override
	public void collided(Sprite enemy, Sprite tile) {
		int side = getCollisionSide(enemy, tile);
		revertPosition1(enemy, tile);

		switch (side) {
		case (LEFT_RIGHT_COLLISION):
		case (RIGHT_LEFT_COLLISION):
			((Enemy) enemy).bounce();
			break;
		case (TOP_BOTTOM_COLLISION):
		case (BOTTOM_TOP_COLLISION):
			enemy.setVerticalSpeed(0);
			break;
		}

	}
}

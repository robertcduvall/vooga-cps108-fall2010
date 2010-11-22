package vooga.games.mariogame.collisions;

import vooga.engine.core.Game;
import vooga.games.mariogame.sprites.Enemy;

import com.golden.gamedev.object.Sprite;

public class ItemToTileCollision extends BetterCollisionGroup {

	public ItemToTileCollision(Game game) {
		super();
	}
	
	@Override
	public void collided(Sprite item, Sprite tile) {
		int side = getCollisionSide(item, tile);
		revertPosition1(item, tile);

		switch (side) {
		case (LEFT_RIGHT_COLLISION):
		case (RIGHT_LEFT_COLLISION):
			item.setHorizontalSpeed(-item.getHorizontalSpeed());
			break;
		case (TOP_BOTTOM_COLLISION):
		case (BOTTOM_TOP_COLLISION):
			item.setVerticalSpeed(0);
			break;
		}

	}
}
package vooga.games.mariogame;

import vooga.engine.factory.MapTile;

import com.golden.gamedev.object.Sprite;

public class MarioToTileCollision extends BetterCollisionGroup {

	@Override
	public void collided(Sprite mario, Sprite tile) {

		int side = getCollisionSide(mario, tile);

		// printCollisionSide(mario,tile);

		revertPosition1(mario, tile);

		switch (side) {
		case (LEFT_RIGHT_COLLISION):
			mario.setHorizontalSpeed(0);
			break;
		case (RIGHT_LEFT_COLLISION):
			mario.setHorizontalSpeed(0);
			break;
		case (TOP_BOTTOM_COLLISION):
			mario.setVerticalSpeed(0);
			((MapTile) tile).actOnCollision(mario);
			break;
		case (BOTTOM_TOP_COLLISION):
			mario.setVerticalSpeed(0);
			((MarioSprite) mario).setOnGround(true);
			break;
		}

	}

}

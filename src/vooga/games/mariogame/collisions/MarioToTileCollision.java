package vooga.games.mariogame.collisions;

import vooga.engine.core.Game;
import vooga.games.mariogame.sprites.MarioSprite;
import vooga.games.mariogame.tiles.Tile;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

public class MarioToTileCollision extends AdvanceCollisionGroup {
	
	public MarioToTileCollision(Game game) {
		super();
	}

	@Override
	public void collided(Sprite mario, Sprite tile) {

//		int side = getCollisionSide(mario, tile);
		int side = getCollisionSide();
		
//		printCollisionSide(mario,tile);
		printCollisionSide();
		
//		revertPosition1(mario, tile);
		revertPosition1();
		
		switch (side) {
		case (LEFT_RIGHT_COLLISION):
			mario.setHorizontalSpeed(0);
			break;
		case (RIGHT_LEFT_COLLISION):
			mario.setHorizontalSpeed(0);
			break;
		case (TOP_BOTTOM_COLLISION):
			mario.setVerticalSpeed(0);
			((Tile) tile).actOnCollision(mario);
			break;
		case (BOTTOM_TOP_COLLISION):
			mario.setVerticalSpeed(0);
			((MarioSprite) mario).setOnGround(true);
			break;
		}

	}

}

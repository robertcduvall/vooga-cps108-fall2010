package vooga.games.marioclone;
import vooga.games.marioclone.tiles.Tile;

import com.golden.gamedev.object.Sprite;

public class MarioToTileCollision extends BetterCollisionGroup{


	
	@Override
	public void collided(Sprite mario, Sprite tile) {
		
		switch(getCollisionSide(mario,tile)) {
		case(LEFT_RIGHT_COLLISION):
			mario.setHorizontalSpeed(0);
			break;
		case(RIGHT_LEFT_COLLISION):
			mario.setHorizontalSpeed(0);
			break;
		case(TOP_BOTTOM_COLLISION):
			mario.setVerticalSpeed(0);
			((Tile) tile).actOnCollision(mario);
			break;
		case(BOTTOM_TOP_COLLISION):
			mario.setVerticalSpeed(0);
			((MarioSprite) mario).setOnGround(true);
			break;
		}	
		
		revertPosition1(mario, tile);
	}

	
}

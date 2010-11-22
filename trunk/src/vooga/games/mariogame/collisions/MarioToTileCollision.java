package vooga.games.mariogame.collisions;

import vooga.engine.core.Game;
import vooga.engine.factory.MapTile;
import vooga.games.mariogame.sprites.MarioSprite;
import vooga.games.mariogame.tiles.FlagTile;

import com.golden.gamedev.object.Sprite;

public class MarioToTileCollision extends BetterCollisionGroup {
	
	public MarioToTileCollision(Game game) {
		super();
	}

	@Override
	public void collided(Sprite mario, Sprite tile) {
		
		if(tile instanceof FlagTile){
			((MapTile) tile).actOnCollision(mario);
			return;
		}

		int side = getCollisionSide(mario, tile);
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

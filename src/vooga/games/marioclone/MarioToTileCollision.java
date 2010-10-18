package vooga.games.marioclone;
import vooga.games.marioclone.tiles.Tile;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

public class MarioToTileCollision extends AdvanceCollisionGroup{
	enum Side{TB, BT, LR, RL, none};
	Side side;

	public MarioToTileCollision(){
		pixelPerfectCollision = true;
		side = Side.none;
	}
	
	@Override
	public void collided(Sprite mario, Sprite tile) {
//		The following code works to some extent, but it causes many issues
		
//		if(mario.getVerticalSpeed()>0) {
//			((MarioSprite) mario).setOnGround(true);
//			mario.setVerticalSpeed(0);
//		}
//		else if(mario.getVerticalSpeed()<0) {
//			mario.setY(tile.getY()+tile.getHeight()+1);
//			mario.setVerticalSpeed(0);
//			((Tile) tile).actOnCollision(mario);
//		}
		
		
//		The following is what should make collisions work, but doesn't.
		
		
		if(mario.getHorizontalSpeed() > 0  && mario.getX()+mario.getWidth() <= tile.getX())
			side = Side.RL;
		else if(mario.getHorizontalSpeed() < 0  && mario.getX() >= tile.getX()+tile.getWidth())
			side = Side.LR;
		
		switch(getCollisionSide()) {
//		case(LEFT_RIGHT_COLLISION):
//			mario.setHorizontalSpeed(0);
//			break;
//		case(RIGHT_LEFT_COLLISION):
//			mario.setHorizontalSpeed(0);
//			break;
		case(TOP_BOTTOM_COLLISION):
			((Tile) tile).actOnCollision(mario);
			mario.setVerticalSpeed(0);
		case(BOTTOM_TOP_COLLISION):
			((MarioSprite) mario).setOnGround(true);
			mario.setVerticalSpeed(0);
		}
		
		switch(side) {
		case none:
			break;
		case LR:
		case RL:
			mario.setHorizontalSpeed(0);
			break;
		}
		
		printCollisionSide();
			
		
		revertPosition1();
				
	}

}

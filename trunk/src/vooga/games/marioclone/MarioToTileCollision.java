package vooga.games.marioclone;
import vooga.games.marioclone.tiles.Tile;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

public class MarioToTileCollision extends AdvanceCollisionGroup{
	
	public MarioToTileCollision(){
		pixelPerfectCollision = true;
	}
	
	@Override
	public void collided(Sprite mario, Sprite tile) {
//		System.out.println(tile+" "+tile.getX()+" "+tile.getY());
//		System.out.println("Collision! Yay!");
		if(mario.getVerticalSpeed()>0) {
//			mario.setY(tile.getY()-mario.getHeight()-1);
			((MarioSprite) mario).setOnGround(true);
			mario.setVerticalSpeed(0);
		}
		else if(mario.getVerticalSpeed()<0) {
			mario.setY(tile.getY()+tile.getHeight()+1);
			mario.setVerticalSpeed(0);
			((Tile) tile).actOnCollision(mario);
		}
		
		
	}

}

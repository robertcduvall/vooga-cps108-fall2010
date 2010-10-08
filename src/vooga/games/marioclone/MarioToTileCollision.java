package vooga.games.marioclone;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

public class MarioToTileCollision extends AdvanceCollisionGroup{
	
	public MarioToTileCollision(){
		pixelPerfectCollision = true;
	}
	
	@Override
	public void collided(Sprite mario, Sprite tile) {
		System.out.println(tile+" "+tile.getX()+" "+tile.getY());
		System.out.println("Collision! Yay!");
		mario.setSpeed(0, 0);
	}

}

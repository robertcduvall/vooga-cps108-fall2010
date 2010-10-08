package vooga.games.jumper;


import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class DoodleToBlockCollision extends BasicCollisionGroup {

	public DoodleToBlockCollision(){
		pixelPerfectCollision = true;
	}
	
	@Override
	public void collided(Sprite doodle, Sprite block) {
		doodle.setVerticalSpeed(block.getVerticalSpeed());
	}

}

package vooga.games.jumper;


import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

public class DoodleToBlockCollision extends AdvanceCollisionGroup {

	private boolean isAdjusted = false;
	
	public DoodleToBlockCollision(){
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite doodle, Sprite block) {
		adjustDoodle(doodle, block);
		doodle.setVerticalSpeed(block.getVerticalSpeed());
	}
	
	public void adjustDoodle(Sprite doodle, Sprite block){
		if(!isAdjusted){
			doodle.setY(block.getY() - doodle.getHeight());
			isAdjusted = true;
		}
	}
}

package vooga.games.doodlejump;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class DoodleToMonsterCollision extends BasicCollisionGroup {

	public DoodleToMonsterCollision() {
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite doodle, Sprite monster) {
		if(doodle.getVerticalSpeed() < 0){
			doodle.setActive(false);
			monster.setActive(false);
		}
		else{
			doodle.setVerticalSpeed(-0.5);
			monster.setActive(false);
		}
	}
}
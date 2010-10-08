package vooga.games.doodlejump;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class DoodleToWhitePlatformCollision extends BasicCollisionGroup {

	public DoodleToWhitePlatformCollision() {
		pixelPerfectCollision = true;
	}
	
	@Override
	public void collided(Sprite doodle, Sprite white_platform) {
		// TODO Auto-generated method stub
		if(doodle.getVerticalSpeed() > 0 && doodle.getY() + doodle.getHeight() - 15 < white_platform.getY()){
			white_platform.setActive(false);
			doodle.setVerticalSpeed(-0.5);
		}
	}

}

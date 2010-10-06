package vooga.games.doodlejump;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class DoodleToGreenPlatformCollision extends BasicCollisionGroup {

	public DoodleToGreenPlatformCollision() {
		pixelPerfectCollision = true;
	}

	public void collided(Sprite doodle, Sprite green_paddle) {

		doodle.setVerticalSpeed(-0.5);

	}
}

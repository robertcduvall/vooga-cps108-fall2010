package vooga.games.doodlejump;

import com.golden.gamedev.object.*;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class DoodleToBrownPlatformCollision extends BasicCollisionGroup {

	public DoodleToBrownPlatformCollision() {
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite doodle, Sprite brown_platform) {
		if(doodle.getVerticalSpeed() > 0 && doodle.getY() + doodle.getHeight() - 10 < brown_platform.getY() && !((AnimatedSprite) brown_platform).isAnimate()){
			System.out.println("hit");
			((AnimatedSprite) brown_platform).setAnimationFrame(0, 3);
			((AnimatedSprite) brown_platform).setLoopAnim(false);
			((AnimatedSprite) brown_platform).setAnimate(true);
			((AnimatedSprite) brown_platform).setAnimationFrame(3, 3);
			brown_platform.setVerticalSpeed(0.4);
		}
	}
}
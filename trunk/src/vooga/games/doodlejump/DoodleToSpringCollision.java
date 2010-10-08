package vooga.games.doodlejump;

import com.golden.gamedev.object.*;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class DoodleToSpringCollision extends BasicCollisionGroup {

	public DoodleToSpringCollision() {
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite doodle, Sprite spring) {
		if(doodle.getVerticalSpeed() > 0 && doodle.getY() + doodle.getHeight() - 10 < spring.getY() && !((AnimatedSprite) spring).isAnimate()){
			System.out.println("hit");
			spring.moveY(-12);
			((AnimatedSprite) spring).setAnimationFrame(0, 1);
			((AnimatedSprite) spring).setLoopAnim(false);
			((AnimatedSprite) spring).setAnimate(true);
			((AnimatedSprite) spring).setAnimationFrame(1, 1);
			doodle.setVerticalSpeed(-1.5);
		}
	}
}
package vooga.games.doodlejump;

import com.golden.gamedev.object.*;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class DoodleToTrampolineCollision extends BasicCollisionGroup {

	public DoodleToTrampolineCollision() {
		pixelPerfectCollision = true;
	}

	@Override
	public void collided(Sprite doodle, Sprite trampoline) {
		if(doodle.getVerticalSpeed() > 0 && doodle.getY() + doodle.getHeight() - 10 < trampoline.getY() && !((AnimatedSprite) trampoline).isAnimate()){
			System.out.println("hit");
			((AnimatedSprite) trampoline).setAnimationFrame(0, 1);
			((AnimatedSprite) trampoline).setLoopAnim(false);
			((AnimatedSprite) trampoline).setAnimate(true);
			doodle.setVerticalSpeed(-1);
		}
	}
}
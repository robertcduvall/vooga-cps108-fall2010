package vooga.games.doodlejump.monsters;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.AnimatedSprite;

public class JigglingFlyingMonster extends AnimatedSprite {
	private int jiggleTimer;

	public JigglingFlyingMonster(BufferedImage[] images, double x, double y) {
		super(images, x, y);
		jiggleTimer = 20;
		setHorizontalSpeed(-0.2);
		setAnimationFrame(0, 4);
		setLoopAnim(true);
		setAnimate(true);
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		jiggleTimer--;
		if (jiggleTimer < 0) {
			setHorizontalSpeed(getHorizontalSpeed() * -1);
			jiggleTimer = 20;
		}
	}
}

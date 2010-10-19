package vooga.games.doodlejump;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

/**
 * The JigglingMonster class extends Sprite and defines a JigglingMonster
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class JigglingMonster extends Sprite {
	private int jiggleTimer;

	public JigglingMonster(BufferedImage image, double x, double y) {
		super(image, x, y);
		jiggleTimer = 20;
		setHorizontalSpeed(-0.2);
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

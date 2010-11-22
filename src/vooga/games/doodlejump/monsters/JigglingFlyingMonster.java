package vooga.games.doodlejump.monsters;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.AnimatedSprite;

public class JigglingFlyingMonster extends BetterSprite {
	private int jiggleTimer;

	public JigglingFlyingMonster(){
		this(Resources.getAnimation("blue_flying_monster"));
	}

	public JigglingFlyingMonster(BufferedImage[] images) {
		super("jiggling_flying_monster", images);
	}

	@Override
	public void firstRun(){
		jiggleTimer = Resources.getInt("jiggleTime");
		AnimatedSprite sprite = (AnimatedSprite) getCurrentSprite();
		sprite.setAnimationFrame(0, 4);
		sprite.setLoopAnim(true);
		sprite.setAnimate(true);
	}
	
	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		jiggleTimer--;
		if (jiggleTimer < 0) {
			setHorizontalSpeed(getHorizontalSpeed() * -1);
			jiggleTimer = Resources.getInt("jiggleTime");
		}
	}
}

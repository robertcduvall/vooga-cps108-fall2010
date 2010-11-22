package vooga.games.doodlejump.monsters;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.AnimatedSprite;

public class JigglingFlyingMonster extends BetterSprite {

	private static final String BLUE_FLYING_MONSTER_STRING = "blue_flying_monster";
	private static final String JIGGLING_FLYING_MONSTER_STRING = "jiggling_flying_monster";
	private static final String JIGGLE_TIME_STRING = "jiggleTime";
	private static final int MULTIPLY_HORIZONTAL_SPEED = -1;
	private static final int ZERO_JIGGLE_TIMER = 0;
	private static final int INITIAL_ANIMATION_FRAME = 0;
	private static final int FINAL_ANIMATION_FRAME = 4;

	private int jiggleTimer;

	public JigglingFlyingMonster() {
		this(Resources.getAnimation(BLUE_FLYING_MONSTER_STRING));
	}

	public JigglingFlyingMonster(BufferedImage[] images) {
		super(JIGGLING_FLYING_MONSTER_STRING, images);
	}

	@Override
	public void firstRun() {
		jiggleTimer = Resources.getInt(JIGGLE_TIME_STRING);
		AnimatedSprite sprite = (AnimatedSprite) getCurrentSprite();
		sprite.setAnimationFrame(INITIAL_ANIMATION_FRAME, FINAL_ANIMATION_FRAME);
		sprite.setLoopAnim(true);
		sprite.setAnimate(true);
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		jiggleTimer--;
		makeMonsterJiggle();
	}

	private void makeMonsterJiggle() {
		if (jiggleTimer < ZERO_JIGGLE_TIMER) {
			setHorizontalSpeed(getHorizontalSpeed() * MULTIPLY_HORIZONTAL_SPEED);
			jiggleTimer = Resources.getInt(JIGGLE_TIME_STRING);
		}
	}
}

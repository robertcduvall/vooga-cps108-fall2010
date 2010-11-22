package vooga.games.doodlejump.monsters;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.AnimatedSprite;

/**
 * The FlyingMonster class extends AnimatedSprite and defines a FlyingMonster
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class FlyingMonster extends BetterSprite {

	private static final String GREEN_FLYING_MONSTER_STRING = "green_flying_monster";
	private static final String FLYING_MONSTER_STRING = "flying_monster";
	private static final String MIN_SCREEN_X_STRING = "minScreenX";
	private static final String MAX_SCREEN_X_STRING = "maxScreenX";
	private static final int MULTIPLY_HORIZONTAL_SPEED = -1;
	
	public FlyingMonster() {
		this(Resources.getAnimation(GREEN_FLYING_MONSTER_STRING));
	}

	public FlyingMonster(BufferedImage[] images) {
		super(FLYING_MONSTER_STRING, images);
	}

	@Override
	public void firstRun() {
		AnimatedSprite sprite = (AnimatedSprite) getCurrentSprite();
		sprite.setAnimationFrame(0, 4);
		sprite.setLoopAnim(true);
		sprite.setAnimate(true);
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		checkInBounds();
	}

	private void checkInBounds() {
		if (getX() < Resources.getDouble(MIN_SCREEN_X_STRING)
				|| getX() > Resources.getDouble(MAX_SCREEN_X_STRING)
						- getWidth()) {
			setHorizontalSpeed(getHorizontalSpeed() * MULTIPLY_HORIZONTAL_SPEED);
		}
	}
}

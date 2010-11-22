package vooga.games.doodlejump.monsters;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.Sprite;

public class JumpingMonster extends BetterSprite {

	private static final String GREEN_JUMPING_MONSTER_STRING = "greenJumpingMonster";
	private static final String JUMP_TIME_STRING = "jumpTime";
	private static final int MULTIPLY_VERTICAL_SPEED = -1;
	private static final int ZERO_JUMP_TIMER = 0;

	private int jumpTimer;

	public JumpingMonster() {
		this(Resources.getImage(GREEN_JUMPING_MONSTER_STRING));
	}

	public JumpingMonster(BufferedImage image) {
		super(image);
	}

	@Override
	public void firstRun() {
		jumpTimer = Resources.getInt(JUMP_TIME_STRING);
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		jumpTimer--;
		makeMonsterJump();
	}

	private void makeMonsterJump() {
		if (jumpTimer < ZERO_JUMP_TIMER) {
			setVerticalSpeed(getVerticalSpeed() * MULTIPLY_VERTICAL_SPEED);
			jumpTimer = Resources.getInt(JUMP_TIME_STRING);
		}
	}
}

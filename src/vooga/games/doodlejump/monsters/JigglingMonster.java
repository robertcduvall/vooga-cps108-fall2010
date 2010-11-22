package vooga.games.doodlejump.monsters;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.Sprite;

/**
 * The JigglingMonster class extends Sprite and defines a JigglingMonster
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class JigglingMonster extends BetterSprite {

	private static final String JIGGLE_TIME_STRING = "jiggleTime";
	private static final int NUMBER_OF_MONSTERS = 4;
	private static final int MULTIPLY_HORIZONTAL_SPEED = -1;

	private int jiggleTimer;
	private static final String[] monsterNames = { "greenMonster",
			"bigBlueMonster", "redMonster", "purpleMonster" };

	public JigglingMonster() {
		this(
				Resources
						.getImage(monsterNames[(int) (Math.random() * NUMBER_OF_MONSTERS)]));
	}

	public JigglingMonster(BufferedImage image) {
		super(image);
	}

	@Override
	public void firstRun() {
		jiggleTimer = Resources.getInt(JIGGLE_TIME_STRING);
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		jiggleTimer--;
		makeMonsterJiggle();
	}

	private void makeMonsterJiggle() {
		if (jiggleTimer < 0) {
			setHorizontalSpeed(getHorizontalSpeed() * MULTIPLY_HORIZONTAL_SPEED);
			jiggleTimer = Resources.getInt(JIGGLE_TIME_STRING);
		}
	}
}

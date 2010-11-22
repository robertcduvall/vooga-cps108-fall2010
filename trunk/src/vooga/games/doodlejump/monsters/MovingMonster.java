package vooga.games.doodlejump.monsters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;

/**
 * The MovingMonster class extends Sprite and defines a MovingMonster
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class MovingMonster extends BetterSprite {

	private static final String BLUE_MONSTER_LEFT_STRING = "blueMonsterLeft";
	private static final String BLUE_MONSTER_RIGHT_STRING = "blueMonsterRight";
	private static final int MULTIPLY_HORIZONTAL_SPEED = -1;

	private static final String MIN_SCREEN_X_STRING = "minScreenX";
	private static final String MAX_SCREEN_X_STRING = "maxScreenX";

	public MovingMonster() {
		this(BLUE_MONSTER_LEFT_STRING);
	}

	public MovingMonster(String label) {
		super(label, new BetterSprite(Resources.getAnimation(label)));
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
			setMonsterImage();
			setHorizontalSpeed(getHorizontalSpeed() * MULTIPLY_HORIZONTAL_SPEED);
		}
	}

	private void setMonsterImage() {
		if (getX() < Resources.getDouble(MIN_SCREEN_X_STRING)) {
			BufferedImage[] doodleImage = { Resources
					.getImage(BLUE_MONSTER_RIGHT_STRING) };
			setImages(doodleImage);
		} else {
			BufferedImage[] doodleImage = { Resources
					.getImage(BLUE_MONSTER_LEFT_STRING) };
			setImages(doodleImage);
		}
	}
}

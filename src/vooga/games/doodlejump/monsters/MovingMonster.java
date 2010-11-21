package vooga.games.doodlejump.monsters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.Sprite;

/**
 * The MovingMonster class extends Sprite and defines a MovingMonster
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class MovingMonster extends BetterSprite {

	public MovingMonster() {
		this("blueMonster");
	}
	public MovingMonster(String label) {
		super(label, new BetterSprite(Resources.getAnimation(label)));
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		if (getX() < 0 || getX() > 532 - getWidth()) {
			if (getX() < 0)
				setImage(Resources.getImage("blue_monster_right"));
			else
				setImage(Resources.getImage("blue_monster_left"));
			setHorizontalSpeed(getHorizontalSpeed() * -1);
		}
	}
}

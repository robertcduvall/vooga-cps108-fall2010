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

	public MovingMonster() {
		this("blueMonsterLeft");
	}
	public MovingMonster(String label) {
		super(label, new BetterSprite(Resources.getAnimation(label)));
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		if (getX() < Resources.getDouble("minScreenX")|| getX() > Resources.getDouble("maxScreenX") - getWidth()) {
			if (getX() < Resources.getDouble("minScreenX")){
				BufferedImage[] doodleImage = { Resources.getImage("blueMonsterRight") };
				setImages(doodleImage);
			}
			else{
				BufferedImage[] doodleImage = { Resources.getImage("blueMonsterLeft") };
				setImages(doodleImage);
			}
			setHorizontalSpeed(getHorizontalSpeed() * -1);
		}
	}
}

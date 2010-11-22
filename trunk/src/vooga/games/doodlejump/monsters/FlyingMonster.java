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
	
	public FlyingMonster(){
		this(Resources.getAnimation("green_flying_monster"));
	}

	public FlyingMonster(BufferedImage[] images) {
		super("flying_monster", images);
	}
	
	@Override
	public void firstRun(){
		AnimatedSprite sprite = (AnimatedSprite) getCurrentSprite();
		sprite.setAnimationFrame(0, 4);
		sprite.setLoopAnim(true);
		sprite.setAnimate(true);
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		if (getX() < Resources.getDouble("minScreenX") || getX() > Resources.getDouble("maxScreenX") - getWidth()) {
			setHorizontalSpeed(getHorizontalSpeed() * -1);
		}
	}
}

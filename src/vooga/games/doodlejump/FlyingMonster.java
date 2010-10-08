package vooga.games.doodlejump;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.AnimatedSprite;

/**
 * The FlyingMonster class extends AnimatedSprite and defines a FlyingMonster 
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 *
 */
public class FlyingMonster extends AnimatedSprite{
	
	public FlyingMonster(BufferedImage[] images, double x, double y){
		super(images, x, y);
		setHorizontalSpeed(-0.2);
		setVerticalSpeed(-0.1);
		setAnimationFrame(0, 4);
		setLoopAnim(true);
		setAnimate(true);
	}
	
	@Override
	public void update(long elapsedTime){
		super.update(elapsedTime);
		if(getX() < 0 || getX() > 532 - getWidth()){
			setHorizontalSpeed(getHorizontalSpeed() * -1);
		}
	}
}

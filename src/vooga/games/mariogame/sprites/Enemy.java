package vooga.games.mariogame.sprites;


import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;

import com.golden.gamedev.object.AnimatedSprite;

/**
 * @author David Herzka, Cameron McCallie, Andrew Brown
 * 
 *         This class represents the main enemy sprite.
 * 
 */

@SuppressWarnings("serial")
public class Enemy extends BetterSprite {

	private static final double mySpeed = .25;
	
	public Enemy(){
	}
	
	public void startAnimation(){
		System.out.println("starting animation");
		setAsRenderedSprite("Goomba");
		((AnimatedSprite) getCurrentSprite()).setAnimate(true);
		((AnimatedSprite) getCurrentSprite()).setLoopAnim(true);
	}
	
	
	@Override
	public void addAnimatedImages(String label, BufferedImage[] images){
		super.addAnimatedImages(label, images);
		startAnimation();
	}
	
	/**
	 * Sends the sprite in the opposite direction with opposite velocity. The
	 * method is named 'bounce' because it occurs after a collision with the
	 * main sprite or wall.
	 * 
	 */

	public void bounce() {
		setHorizontalSpeed(-getHorizontalSpeed());
	}

}

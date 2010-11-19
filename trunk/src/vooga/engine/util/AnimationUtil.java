package vooga.engine.util;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.AnimatedSprite;

/**
 * 
 * Automatically performs common tasks related 
 * to animation to reduce code repetition.
 * 
 * @author Yang Su, Daniel Koverman
 *
 */

public class AnimationUtil {
	
	public static long defaultAnimationDelay = 200;
	public static AnimatedSprite getInitializedAnimatedSprite(BufferedImage[] images) {
		AnimatedSprite sprite = new AnimatedSprite(images);
		initializeAnimatedSprite(sprite, defaultAnimationDelay, true);
		return sprite;
	}

	/**
	 * Set the attributes for an animated sprite
	 * 
	 * @param sprite
	 *            animated sprite object
	 * @param delay
	 *            animation delay
	 * @param loop
	 *            loop animation
	 */
	public static void initializeAnimatedSprite(AnimatedSprite sprite, long delay,
			boolean loop) {
		sprite.getAnimationTimer().setDelay(delay);

		sprite.setAnimationFrame(0, sprite.getImages().length - 1);
		sprite.setAnimate(true);
		sprite.setLoopAnim(loop);
	}

}

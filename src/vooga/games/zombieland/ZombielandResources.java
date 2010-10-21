package vooga.games.zombieland;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.AnimatedSprite;

import vooga.engine.resource.ResourcesBeta;

public class ZombielandResources extends ResourcesBeta {

	private static long defaultAnimationDelay = 300;

	/**
	 * Initialize a set of images to be animated.
	 * 
	 * @param images
	 *            a set of images to be made into an animated sprite
	 * @return initialized animated sprite
	 */
	private AnimatedSprite getInitializedAnimatedSprite(BufferedImage[] images) {
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
	private void initializeAnimatedSprite(AnimatedSprite sprite, long delay,
			boolean loop) {
		sprite.getAnimationTimer().setDelay(delay);

		sprite.setAnimationFrame(0, sprite.getImages().length - 1);
		sprite.setAnimate(true);
		sprite.setLoopAnim(loop);
	}
}

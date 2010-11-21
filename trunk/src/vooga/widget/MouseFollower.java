package vooga.widget;

import java.awt.image.BufferedImage;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

import com.golden.gamedev.engine.BaseInput;
import com.golden.gamedev.object.AnimatedSprite;

public class MouseFollower extends BetterSprite {

	
	private static final long serialVersionUID = 1L;
	private BaseInput input;
	
	/**
	 * Constructs an entity with null image and 0, 0 position and the default
	 * label.
	 */
	public MouseFollower() {
		super();
		input = Resources.getGame().bsInput;
	}

	/**
	 * Constructs an entity with the image and 0, 0 position
	 * 
	 * @param image
	 */
	public MouseFollower(BufferedImage image) {
		super(image);
		input = Resources.getGame().bsInput;
	}

	/**
	 * @param name
	 *            is any name you'd like to give to the object.
	 * @param state
	 *            State name is the name you'd like to map to the following
	 *            Sprite object. e.g."alive" to represent a sprite that's a
	 *            live, or "dead" if the sprite represents the entity in a dead
	 *            state, etc
	 * @param super is the default Sprite that will represent super Entity on the
	 *        Screen.
	 */
	public MouseFollower(String label, com.golden.gamedev.object.Sprite s) {
		super(label, s);
		input = Resources.getGame().bsInput;
	}

	/**
	 * Construct an animated entity from an array of images
	 * 
	 * @param image
	 */
	public MouseFollower(BufferedImage[] images) {
		super(images);
		input = Resources.getGame().bsInput;
	}

	/**
	 * Construct an entity from an array of BufferedImages to create an animated sprite
	 * 
	 * @param image
	 */
	public MouseFollower(String label, BufferedImage[] images) {
		super(label, new AnimatedSprite(images));
		input = Resources.getGame().bsInput;
	}
	
	/**
	 * Constructs an entity with an image located at the specified
	 * coordinates and the default label.
	 * 
	 * @param image
	 * 
	 * @param x
	 *            is the x position.
	 * @param y
	 *            is the y position.
	 */
	public MouseFollower(BufferedImage image, double x, double y) {
		super(image, x, y);
		input = Resources.getGame().bsInput;
	}

	/**
	 * Constructs an entity with null image located at the specified coordinates
	 * with the default label.
	 * 
	 * @param x
	 *            is the x position.
	 * @param y
	 *            is the y position.
	 */
	public MouseFollower(double x, double y) {
		super(null, x, y);
		input = Resources.getGame().bsInput;
	}
	
	/**
	 * Constructs an entity with given image located at specified coordinates
	 * AND labels the image so that you can switch to super initial sprite if
	 * other sprites are added to super entity later.
	 * 
	 * @param label
	 *            is the label for super sprite representation.
	 * @param image
	 * @param x
	 *            is the x position.
	 * @param y
	 *            is the y position.
	 */
	public MouseFollower(String label, BufferedImage image, double x, double y) {
		super(label, new com.golden.gamedev.object.Sprite(image, x, y));
		input = Resources.getGame().bsInput;
	}

	/**
	 * Constructs an entity with given image at 0, 0 position AND labels the
	 * image so that you can switch to super initial sprite if other sprites are
	 * added to super entity later.
	 * 
	 * @param label
	 *            is the label for super sprite representation.
	 * @param image
	 */
	public MouseFollower(String label, BufferedImage image) {
		super(image, 0, 0);
		input = Resources.getGame().bsInput;
		
	}
	
	
	@Override
	public void update(long elapsedTime){
		forceX(input.getMouseX()-this.getWidth()/2);
		forceY(input.getMouseY()-this.getHeight()/2);
		super.update(elapsedTime);
	}
	

}

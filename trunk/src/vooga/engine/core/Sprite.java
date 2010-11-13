package vooga.engine.core;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import vooga.engine.overlay.Stat;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.util.ImageUtil;

/**
 * GameEntitySprite represents any object that you might interact with in a
 * game. Player and Item are both extensions of this class. It supports having
 * multiple images (implemented as sprites) to represent a single GameEntity.
 * This class extends Sprite so that it can act as a sprite, which is the unit
 * that Golden-T is built around, for things like collision detection and being
 * able to be added to SpriteGroups, but can also use multiple sprites (and
 * different kind of sprites) to represent the entity.
 * 
 * @author Jimmy Mu, Marcus Molchany, Drew Sternesky
 * 
 */

@SuppressWarnings("serial")
public class Sprite extends com.golden.gamedev.object.Sprite {

	private static final String DEFAULT_STATE_NAME = "default";
	
	private long myStartTime;
	private Map<String, Sprite> mySprites;
	private com.golden.gamedev.object.Sprite myCurrentSprite;
	private Map<String, Stat<?>> myStatMap;

	/**
	 * Constructs an entity with null image and 0, 0 position and the default label.
	 */
	public Sprite() {
		this(null);
	}
	
	/**
	 * Constructs an entity from the given image with 0, 0 position and the default label.
	 * @param image
	 */
	public Sprite(BufferedImage image) {
		this(DEFAULT_STATE_NAME, image);
	}
	
	/** 
	 * Constructs an entity with null image located at the specified coordinates with the default label.
	 * @param x is the x position.
	 * @param y is the y position.
	 */
	public Sprite(double x, double y) {
		this(null, 0, 0);
	}
	
	/**
	 * Constructs an entity with given image located at the specified coordinates and the default label.
	 * @param image 
	 * @param x is the x position.
	 * @param y is the y position.
	 */
	public Sprite(BufferedImage image, double x, double y) {
		this(DEFAULT_STATE_NAME, new Sprite(image, x, y));
	}
	
	/**
	 * Constructs an entity with given image located at specified coordinates AND labels the 
	 * image so that you can switch to this initial sprite if other sprites are added to this
	 * entity later.
	 * @param label is the label for this sprite representation.
	 * @param image
	 * @param x is the x position.
	 * @param y is the y position.
	 */
	public Sprite(String label, BufferedImage image, double x, double y) {
		this(label, new Sprite(image, x, y));
	}
	
	/**
	 * Constructs an entity with given image at 0, 0 position AND labels the 
	 * image so that you can switch to this initial sprite if other sprites are added to this
	 * entity later.
	 * @param label is the label for this sprite representation.
	 * @param image
	 */
	public Sprite(String label, BufferedImage image) {
		this(image, 0, 0);
	}
	
	/**
	 * @param name
	 *            is any name you'd like to give to the object.
	 * @param state
	 *            State name is the name you'd like to map to the following
	 *            Sprite object. e.g."alive" to represent a sprite that's a
	 *            live, or "dead" if the sprite represents the entity in a dead
	 *            state, etc
	 * @param this is the default Sprite that will represent this Entity on the
	 *        Screen.
	 */
	public Sprite(String label, Sprite s) {
		myStartTime = System.currentTimeMillis();
		mySprites = new HashMap<String, Sprite>();
		addSprite(label, s);
		myCurrentSprite = s;
		myStatMap = new HashMap<String, Stat<?>>();
	}

	/**
	 * @param label
	 *            is the name you'd like to use to represent the Sprite
	 *            parameter. e.g. "alive" or "dead" or "shooting"
	 * @param sp
	 *            is the Sprite you are mapping to the first parameter.
	 */
	public void addSprite(String label, Sprite sp) {
		mySprites.put(label, sp);
	}
	
	/**
	 * Adds a new Sprite made from the given image.
	 * @param label is the name you'd like to use to represent the Sprite
	 *            parameter. e.g. "alive" or "dead" or "shooting"
	 * @param image is the image from which the new Sprite will be constructed.
	 */
	public void addSprite(String label, BufferedImage image) {
		addSprite(label, new Sprite(image));
	}
	
	/**
	 * This method returns my currentSprite. Beware that the currentSprite may change
	 * @return Sprite
	 */
	public com.golden.gamedev.object.Sprite getCurrentSprite()
	{
		return myCurrentSprite;
	}

	/**
	 * 
	 * @return length of time the GameEntity has existed
	 */
	public long getTimeInExistence() {
		long currentTime = System.currentTimeMillis();
		return currentTime - myStartTime;
	}


	/**
	 * Modify GameEntity so that it is represented by the Sprite specified by
	 * spriteName.
	 * 
	 * @param spriteName
	 *            the "name" of the sprite that GameEntity will now be
	 *            represented by.
	 */
	public void setToCurrentSprite(String spriteName) {
		if (mySprites.containsKey(spriteName)) {
			Sprite nextSprite = mySprites.get(spriteName);
			setToCurrentSprite(nextSprite);
		}
	}

	/**
	 * Set the nextSprite to be myCurrentSprite. Synchronize the position and
	 * velocity of the nextSprite to myCurrentSprite
	 * 
	 * @param nextSprite
	 *            set the "nextSprite" to the current sprite
	 */

	private void setToCurrentSprite(Sprite nextSprite) {

		double currentX = myCurrentSprite.getX();
		double currentY = myCurrentSprite.getY();
		double currentVX = myCurrentSprite.getHorizontalSpeed();
		double currentVY = myCurrentSprite.getVerticalSpeed();

		nextSprite.setLocation(currentX, currentY);
		nextSprite.setSpeed(currentVX, currentVY);

		setActive(false);
		myCurrentSprite = nextSprite;
		setActive(true);
	}


	/**
	 * Returns the Stat associated with the given name.
	 * @param statName is the name used 
	 * @return
	 */
	public Stat<?> getStat(String statName) {
		return myStatMap.get(statName);
	}
	
	/**
	 * Sets 
	 */
	public void setStat(String name, Stat<?> t) {
		myStatMap.put(name, t);
	}
	 
	/***********************************************************************************************************
	 * THIS SECTION REWRITES ALL OF SPRITE'S METHODS. These simply forward the
	 * method calls to the currently active sprite in the GameEntity.
	 * *********************************************************************************************************
	 */

	/**
	 * Specify how the GameEntity Object should be updated.
	 */
	@Override
	public void update(long elapsedTime) {
		myCurrentSprite.update(elapsedTime);
	}

	/**
	 * Render the image onto the screen
	 * 
	 * @see com.golden.gamedev.object.Sprite#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
		myCurrentSprite.render(g);
	}

	/***********************************************************************************
	 * myCurrentSprite: IMAGE MANIPULATION, DISPLAY, ROTATION
	 * *********************************************************************************
	 */
	
	/**
	 * Get the bitmap representation of myCurrentSprite
	 */
	@Override
	public BufferedImage getImage() {
		return myCurrentSprite.getImage();
	}

	/**
	 * This method sets a new image to the current Image. This method is used by
	 * rotate Image;
	 * 
	 * @param Image
	 */
	public void setImage(BufferedImage Image) {
		myCurrentSprite.setImage(Image);
	}

	/**
	 * roteSpriteImage rotates the displayed myCurrentSprite by a specified
	 * angle
	 * 
	 * @param angle
	 *            specifies how much myCurrentSprite image is rotated in
	 *            clockwise direction
	 */
	public void rotateSpriteImage(double angle) {
		BufferedImage currentSpriteImage = getImage();
		int width = currentSpriteImage.getWidth();
		int height = currentSpriteImage.getHeight();

		int transparency = currentSpriteImage.getColorModel().getTransparency();
		BufferedImage image = ImageUtil
				.createImage(width, height, transparency);

		Graphics2D g = image.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.rotate(Math.toRadians(angle), width / 2, height / 2);
		g.drawImage(currentSpriteImage, 0, 0, null);
		g.dispose();

		setImage(image);
	}

	/**
	 * Check whether the myCurrentSprite is on the screen
	 */
	@Override
	public boolean isOnScreen() {
		return myCurrentSprite.isOnScreen();
	}


	/**
	 * set whether myCurrentSprite will be visible on the screen
	 */
	@Override
	public void setActive(boolean b) {
		myCurrentSprite.setActive(b);
	}

	/**
	 * check if myCurrentSprite is currently displayed
	 */
	@Override
	public boolean isActive() {
		return myCurrentSprite.isActive();
	}

	/**
	 * Set the background for the Golden T. This allows the sprites to be part
	 * of the playfield.
	 */
	@Override
	public void setBackground(Background backgr) {
		for (String s : mySprites.keySet()) {
			mySprites.get(s).setBackground(backgr);
		}
	}


	/**
	 * Set all the sprites to the same
	 */
	@Override
	public void setLayer(int i) {
		for (String s : mySprites.keySet()) {
			mySprites.get(s).setLayer(i);
		}
	}
	
	/**************************************************************************************
	 * myCurrentSprite: MOVEMENT, SPEED, LOCATION
	 * ************************************************************************************
	 */

	/**
	 * Add an acceleration value to the sprite or change the horizontal speed of
	 * the sprite
	 * 
	 * @see com.golden.gamedev.object.Sprite#addHorizontalSpeed(long, double,
	 *      double)
	 */
	@Override
	public void addHorizontalSpeed(long elapsedTime, double accel,
			double maxSpeed) {
		((Sprite) myCurrentSprite).addHorizontalSpeed(elapsedTime,
				accel, maxSpeed);
	}

	/**
	 * Add an acceleration value to the sprite or change the vertical speed of
	 * the sprite
	 * 
	 * @see com.golden.gamedev.object.Sprite#addVerticalSpeed(long, double,
	 *      double)
	 */
	@Override
	public void addVerticalSpeed(long elapsedTime, double accel, double maxSpeed) {
		myCurrentSprite.addVerticalSpeed(elapsedTime, accel, maxSpeed);
	}

	/**
	 * Force myCurrentSprite to a specific X location newx on the screen
	 */
	@Override
	public void forceX(double newx) {
		myCurrentSprite.forceX(newx);
	}

	/**
	 * Force myCurrentSprite to a specific Y location newy on the screen
	 */
	@Override
	public void forceY(double newy) {
		myCurrentSprite.forceY(newy);
	}

	/**
	 * Get the current X position of myCurrentSprite
	 */
	@Override
	public double getX() {
		return myCurrentSprite.getX();
	}

	/**
	 * Get the current Y position of myCurrentSprite
	 * 
	 * @see com.golden.gamedev.object.Sprite#getY()
	 */
	@Override
	public double getY() {
		return myCurrentSprite.getY();
	}

	/**
	 * Get the distance from mycurrentSprite
	 */
	@Override
	public double getDistance(com.golden.gamedev.object.Sprite other) {
		return myCurrentSprite.getDistance(other);
	}
	
	public double getDistance(Sprite other) {
		return myCurrentSprite.getDistance(other.getCurrentSprite());
	}

	@Override
	public int getHeight() {
		return myCurrentSprite.getHeight();
	}

	@Override
	public int getWidth() {
		return myCurrentSprite.getWidth();
	}

	@Override
	public double getHorizontalSpeed() {
		return myCurrentSprite.getHorizontalSpeed();
	}

	@Override
	public double getVerticalSpeed() {
		return myCurrentSprite.getVerticalSpeed();
	}
	
	@Override
	public void setHorizontalSpeed(double vx) {
		myCurrentSprite.setHorizontalSpeed(vx);
	}

	@Override
	public void setVerticalSpeed(double vy) {
		myCurrentSprite.setVerticalSpeed(vy);
	}
	
	@Override
	public void setLocation(double xs, double ys) {
		myCurrentSprite.setLocation(xs, ys);
	}

	/**
	 * set the magnitude of myCurrenttSprite with an initial angle
	 */
	@Override
	public void setMovement(double speed, double angleDir) {
		myCurrentSprite.setMovement(speed, angleDir);
	}

	@Override
	public void setSpeed(double vs, double vy) {
		myCurrentSprite.setSpeed(vs, vy);
	}

	@Override
	public void setX(double xs) {
		myCurrentSprite.setX(xs);
	}

	@Override
	public void setY(double ys) {
		myCurrentSprite.setY(ys);
	}
	
	/**
	 * Move the current Sprite newx and newy
	 * 
	 * @see com.golden.gamedev.object.Sprite#move(double, double)
	 */
	@Override
	public void move(double newx, double newy) {
		myCurrentSprite.move(newx, newy);
	}

	/**
	 * Move myCurrentSprie to newx and newy and change the velocity to newSpeed
	 */
	@Override
	public boolean moveTo(long elapsedTime, double newx, double newy,
			double newspeed) {
		return myCurrentSprite.moveTo(elapsedTime, newx, newy, newspeed);
	}

	@Override
	public void moveX(double dx) {
		myCurrentSprite.moveX(dx);
	}

	@Override
	public void moveY(double dy) {
		myCurrentSprite.moveY(dy);
	}
	
	

}
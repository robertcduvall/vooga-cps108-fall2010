package vooga.engine.player.control;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import com.golden.gamedev.object.Sprite;
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
public abstract class GameEntitySprite extends Sprite {

	private long myStartTime;
	private String myName;
	private Map<String, Sprite> mySprites;
	private Sprite myCurrentSprite;

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

	public GameEntitySprite(String name, String stateName, Sprite s) {
		myStartTime = System.currentTimeMillis();
		mySprites = new HashMap<String, Sprite>();
		mapNameToSprite(stateName, s);
		myCurrentSprite = s;
		setName(name);
	}

	/**
	 * @param state
	 *            is the name you'd like to use to represent the Sprite
	 *            parameter. e.g. "alive" or "dead" or "shooting"
	 * @param sp
	 *            is the Sprite you are mapping to the first parameter.
	 */

	public void mapNameToSprite(String state, Sprite sp) {
		mySprites.put(state, sp);
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
	 * 
	 * @return GameEntity's name
	 */
	public String getName() {
		return myName;
	}

	/**
	 * Changes the name of the GameEntity.
	 * 
	 * @param name
	 *            new name.
	 */
	private void setName(String name) {
		myName = name;
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

	/***********************************************************************************************************
	 * THIS SECTION REWRITES ALL OF SPRITE'S METHODS. These simply forward the
	 * method calls to the currently active sprite in the GameEntity.
	 * *********************************************************************************************************
	 */

	/**
	 * Specify how the GameEntity Object should be updated.
	 */
	public void update(long elapsedTime) {
		myCurrentSprite.update(elapsedTime);
	}

	/**
	 * Render the image onto the screen
	 * 
	 * @see com.golden.gamedev.object.Sprite#render(java.awt.Graphics2D)
	 */
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
	public BufferedImage getImage() {
		return myCurrentSprite.getImage();
	}

	/**
	 * This method sets a new image to the current Image. This method is used by
	 * rotate Image;
	 * 
	 * @param Image
	 */
	private void setNewImage(BufferedImage Image) {
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

		setNewImage(image);
	}

	/**
	 * Check whether the myCurrentSprite is on the screen
	 */
	public boolean isOnScreen() {
		return myCurrentSprite.isOnScreen();
	}


	/**
	 * set whether myCurrentSprite will be visible on the screen
	 */
	public void setActive(boolean b) {
		myCurrentSprite.setActive(b);
	}

	/**
	 * check if myCurrentSprite is currently displayed
	 */
	public boolean isActive() {
		return myCurrentSprite.isActive();
	}

	/**
	 * Set the background for the Golden T. This allows the sprites to be part
	 * of the playfield.
	 */
	public void setBackground(Background backgr) {
		for (String s : mySprites.keySet()) {
			mySprites.get(s).setBackground(backgr);
		}
	}


	/**
	 * Set all the sprites to the same
	 */
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
	public void addHorizontalSpeed(long elapsedTime, double accel,
			double maxSpeed) {
		((GameEntitySprite) myCurrentSprite).addHorizontalSpeed(elapsedTime,
				accel, maxSpeed);
	}

	/**
	 * Add an acceleration value to the sprite or change the vertical speed of
	 * the sprite
	 * 
	 * @see com.golden.gamedev.object.Sprite#addVerticalSpeed(long, double,
	 *      double)
	 */
	public void addVerticalSpeed(long elapsedTime, double accel, double maxSpeed) {
		myCurrentSprite.addVerticalSpeed(elapsedTime, accel, maxSpeed);
	}

	/**
	 * Force myCurrentSprite to a specific X location newx on the screen
	 */
	public void forceX(double newx) {
		myCurrentSprite.forceX(newx);
	}

	/**
	 * Force myCurrentSprite to a specific Y location newy on the screen
	 */
	public void forceY(double newy) {
		myCurrentSprite.forceY(newy);
	}

	/**
	 * Get the current X position of myCurrentSprite
	 */
	public double getX() {
		return myCurrentSprite.getX();
	}

	/**
	 * Get the current Y position of myCurrentSprite
	 * 
	 * @see com.golden.gamedev.object.Sprite#getY()
	 */
	public double getY() {
		return myCurrentSprite.getY();
	}

	/**
	 * Get the distance from mycurrentSprite
	 */
	public double getDistance(Sprite other) {
		return myCurrentSprite.getDistance(other);
	}

	public int getHeight() {
		return myCurrentSprite.getHeight();
	}

	public int getWidth() {
		return myCurrentSprite.getWidth();
	}

	public double getHorizontalSpeed() {
		return myCurrentSprite.getHorizontalSpeed();
	}

	public double getVerticalSpeed() {
		return myCurrentSprite.getVerticalSpeed();
	}
	
	public void setHorizontalSpeed(double vx) {
		myCurrentSprite.setHorizontalSpeed(vx);
	}

	public void setVerticalSpeed(double vy) {
		myCurrentSprite.setVerticalSpeed(vy);
	}
	
	public void setLocation(double xs, double ys) {
		myCurrentSprite.setLocation(xs, ys);
	}

	/**
	 * set the magnitude of myCurrenttSprite with an initial angle
	 */
	public void setMovement(double speed, double angleDir) {
		myCurrentSprite.setMovement(speed, angleDir);
	}

	public void setSpeed(double vs, double vy) {
		myCurrentSprite.setSpeed(vs, vy);
	}

	public void setX(double xs) {
		myCurrentSprite.setX(xs);
	}

	public void setY(double ys) {
		myCurrentSprite.setY(ys);
	}
	
	/**
	 * Move the current Sprite newx and newy
	 * 
	 * @see com.golden.gamedev.object.Sprite#move(double, double)
	 */
	public void move(double newx, double newy) {
		myCurrentSprite.move(newx, newy);
	}

	/**
	 * Move myCurrentSprie to newx and newy and change the velocity to newSpeed
	 */
	public boolean moveTo(long elapsedTime, double newx, double newy,
			double newspeed) {
		return myCurrentSprite.moveTo(elapsedTime, newx, newy, newspeed);
	}

	public void moveX(double dx) {
		myCurrentSprite.moveX(dx);
	}

	public void moveY(double dy) {
		myCurrentSprite.moveY(dy);
	}
	
	

}
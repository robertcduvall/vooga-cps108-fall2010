package vooga.engine.core;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import vooga.engine.overlay.OverlayLabel;
import vooga.engine.overlay.Stat;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Background;

/**
 * The vooga.engine.core.Sprite class can represent any renderable object in the
 * game environment. The vooga.engine.core.Sprite class extends the
 * com.golden.gamedev.object.Sprite class but provides two additional useful
 * features: the option to switch between renderable images and the capacity to
 * contain any attributes in the form of a Stat.
 * 
 * Like the Golden T Sprite class, the Vooga Sprite class supports update and
 * render behaviors for a Sprite object. In addition, the Sprite class contains
 * all the images and attributes that a Sprite object needs. The functionalities
 * supported by the Sprite class include rendering image animation, setting
 * Sprite location and velocity, switching to a different image, and allows for
 * the updating of Sprite attributes.
 * 
 * Code Example: <XMP> Sprite robertDuvall = new Sprite(normal,
 * "robertDuvall.jpg");
 * 
 * robertDuvall.addSprite(yelling, new Sprite("robertDuvallYelling.jpg"));
 * BufferedImage[] duvallLaughing =
 * Resources.getImages("robertDuvallLaughing"));
 * robertDuvall.addAnimatedImages("laughing", duvallLaughing);
 * robertDuvall.setAsRenderedSprite("laughing");
 * 
 * Stat<String> unhappyprojectResponse = new
 * Stat("Well, this person obviously doesn't know how to code..."); Stat<Double>
 * understandability = new Stat(0.0);
 * robertDuvall.setStat("unhappyprojectResponse", unhappyprojectResponse);
 * robertDuvall.setStat("understandability", understandability);
 * 
 * Stat<Double> intelligible = robertDuvall.getStat("understandability");
 * 
 * long timeExisted = robertDuvall.getTimeInExistence();
 * 
 * robertDuvall.update(timeExisted); robertDvuall.render(g); </XMP>
 * 
 * @author Jimmy Mu, Marcus Molchany, Drew Sternesky
 * 
 */

@SuppressWarnings("serial")
public class BetterSprite extends com.golden.gamedev.object.Sprite {

	private static final String DEFAULT_STATE_NAME = "default";

	private long myStartTime;
	private Map<String, com.golden.gamedev.object.Sprite> mySprites;
	private com.golden.gamedev.object.Sprite myCurrentSprite;
	private Map<String, Stat<?>> myStatMap;
	protected String myLabel;
	private boolean firstRun;

	protected OverlayLabel nameLabel;

	/**
	 * Constructs an entity with null image and 0, 0 position and the default
	 * label.
	 */
	public BetterSprite() {
		this(DEFAULT_STATE_NAME, new com.golden.gamedev.object.Sprite());
	}

	/**
	 * Constructs an entity with null image and 0, 0 position and the default
	 * label.
	 */
	public BetterSprite(com.golden.gamedev.object.Sprite sprite) {
		this(DEFAULT_STATE_NAME, sprite);
	}

	/**
	 * Constructs an entity with the image and 0, 0 position
	 * 
	 * @param image
	 */
	public BetterSprite(BufferedImage image) {
		this(DEFAULT_STATE_NAME, new com.golden.gamedev.object.Sprite(image));
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
	public BetterSprite(String label, com.golden.gamedev.object.Sprite s) {
		myStartTime = System.currentTimeMillis();
		mySprites = new HashMap<String, com.golden.gamedev.object.Sprite>();
		addSprite(label, s);
		myCurrentSprite = s;
		myLabel = label;
		myStatMap = new HashMap<String, Stat<?>>();
		firstRun = true;
	}

	/**
	 * Construct an animated entity from an array of images
	 * 
	 * @param image
	 */
	public BetterSprite(BufferedImage[] images) {
		this(DEFAULT_STATE_NAME, new AnimatedSprite(images));
	}

	/**
	 * Construct an entity from an array of BufferedImages to create an animated
	 * sprite
	 * 
	 * @param image
	 */
	public BetterSprite(String label, BufferedImage[] images) {
		this(label, new AnimatedSprite(images));
		myLabel = label;
	}

	/**
	 * Constructs an entity with an image located at the specified coordinates
	 * and the default label.
	 * 
	 * @param image
	 * 
	 * @param x
	 *            is the x position.
	 * @param y
	 *            is the y position.
	 */
	public BetterSprite(BufferedImage image, double x, double y) {
		this(DEFAULT_STATE_NAME, new com.golden.gamedev.object.Sprite(image, x,
				y));
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
	public BetterSprite(double x, double y) {
		this(null, x, y);
	}

	/**
	 * Constructs an entity with given image located at specified coordinates
	 * AND labels the image so that you can switch to this initial sprite if
	 * other sprites are added to this entity later.
	 * 
	 * @param label
	 *            is the label for this sprite representation.
	 * @param image
	 * @param x
	 *            is the x position.
	 * @param y
	 *            is the y position.
	 */
	public BetterSprite(String label, BufferedImage image, double x, double y) {
		this(label, new com.golden.gamedev.object.Sprite(image, x, y));
	}

	/**
	 * Constructs an entity with given image at 0, 0 position AND labels the
	 * image so that you can switch to this initial sprite if other sprites are
	 * added to this entity later.
	 * 
	 * @param label
	 *            is the label for this sprite representation.
	 * @param image
	 */
	public BetterSprite(String label, BufferedImage image) {
		this(image, 0, 0);
	}

	/**
	 * @param label
	 *            is the name you'd like to use to represent the Sprite
	 *            parameter. e.g. "alive" or "dead" or "shooting"
	 * @param sp
	 *            is the Sprite you are mapping to the first parameter.
	 */
	public void addSprite(String label, com.golden.gamedev.object.Sprite sp) {
		mySprites.put(label, sp);
	}

	/**
	 * Adds a new Sprite made from the given image.
	 * 
	 * @param label
	 *            is the name you'd like to use to represent the Sprite
	 *            parameter. e.g. "alive" or "dead" or "shooting"
	 * @param image
	 *            is the image from which the new Sprite will be constructed.
	 */
	public void addImage(String label, BufferedImage image) {
		addSprite(label, new com.golden.gamedev.object.Sprite(image));
	}

	/**
	 * Adds a new Animatedsprite to the image map;
	 * 
	 * @param label
	 * @param images
	 */
	public void addAnimatedImages(String label, BufferedImage[] images) {
		addSprite(label, new AnimatedSprite(images));
	}

	/**
	 * This method returns my currentSprite. Beware that the currentSprite may
	 * change
	 * 
	 * @return Sprite
	 */
	public com.golden.gamedev.object.Sprite getCurrentSprite() {
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
	public void setAsRenderedSprite(String spriteName) {
		if (mySprites.containsKey(spriteName)) {
			com.golden.gamedev.object.Sprite nextSprite = mySprites
					.get(spriteName);
			setAsRenderedSprite(nextSprite);
		}
	}

	/**
	 * Set the nextSprite to be myCurrentSprite. Synchronize the position and
	 * velocity of the nextSprite to myCurrentSprite
	 * 
	 * @param nextSprite
	 *            set the "nextSprite" to the current sprite
	 */

	private void setAsRenderedSprite(com.golden.gamedev.object.Sprite nextSprite) {

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
	 * 
	 * @param statName
	 *            is the name used
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

	public Stat<Integer> getIntStat(String statname) {
		// TODO: Implement Method
		return new Stat<Integer>(0);
	}

	public void setIntStat(String statname, int stat) {
		// TODO: Implement Method
	}

	public Stat<Double> getDoubleStat(String statname) {
		// TODO: Implement Method
		return new Stat<Double>(0.0);
	}

	public void setDoubleStat(String statname) {
		// TODO: Implement Method
	}

	/**
	 * Specify how the GameEntity Object should be updated.
	 */
	@Override
	public void update(long elapsedTime) {
		if (firstRun) {
			firstRun();
			firstRun = false;
		}
		if (nameLabel != null)
			nameLabel.update(elapsedTime);
		myCurrentSprite.update(elapsedTime);
	}

	/**
	 * Override this method to do any initializations necessary after the sprite
	 * is initialized by the level file.
	 */
	public void firstRun() {

	}

	/**
	 * Render the image onto the screen
	 * 
	 * @see com.golden.gamedev.object.Sprite#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
		if (nameLabel != null) {
			nameLabel.render(g);
		}
		myCurrentSprite.render(g);
	}

	/***************************************************
	 * myCurrentSprite: IMAGE MANIPULATION AND DISPLAY
	 * *************************************************
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
	 * This method sets a new image array to the current image array only if
	 * myCurrentSprite is an AnimatedSprite
	 * 
	 * @param images
	 *            the images to set on myCurrentSprite
	 */
	public void setImages(BufferedImage[] images) {
		if (myCurrentSprite instanceof AnimatedSprite) {
			((AnimatedSprite) myCurrentSprite).setImages(images);
		}
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

	/*********************************************
	 * myCurrentSprite: MOVEMENT, SPEED, LOCATION
	 * *******************************************
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
		((BetterSprite) myCurrentSprite).addHorizontalSpeed(elapsedTime, accel,
				maxSpeed);
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

	public double getDistance(BetterSprite other) {
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

	public String getLabel() {
		return myLabel;
	}

	public void setLabel(String name) {
		myLabel = name;

	}

}
package arcade.ads.adscontent;

import java.awt.Graphics2D;
import java.util.Date;

import javax.swing.JPanel;

import org.w3c.dom.NamedNodeMap;

/**
 * This is simple class will provide general functionality for all ads, such as
 * name, start and end date, maximum vies, onClick, onMouseOver, update, and
 * render methods. We feel like these are the very basic methods and ideas that
 * every single ad is going to need.
 * 
 * @author Hao He (hh89@duke.edu)
 * @author Nick Straub (njs7@duke.edu)
 * @author Scott Winkleman (saw26@duke.edu)
 * @author Kate Yang (kly2@duke.edu)
 * 
 * @version 1.0
 */

public abstract class BasicAd {

	protected String name;
	protected int width;
	protected int height;
	protected Date effectiveDate;
	protected Date expireDate;
	protected long duration;
	protected String targetURL;
	protected double horizontalSpeed;
	protected double verticalSpeed;
	protected double screenX;
	protected double screenY;
	protected double currentX;
	protected double currentY;
	protected boolean status;
	protected boolean onScreen;

	/**
	 * sets ads name
	 * 
	 * @param name
	 */
	public BasicAd(String name) {
		this.name = name;
	}

	/**
	 * sets ads name, and targetURL
	 * 
	 * @param name
	 * @param targetURL
	 */
	public BasicAd(String name, String targetURL) {
		this.name = name;
		this.targetURL = targetURL;
	}

	public BasicAd(String name, String targetURL, int width, int height, Date effective, Date expire, long duration){
		this.name = name;
		this.targetURL = targetURL;
		this.width = width;
		this.height = height;
		this.effectiveDate = effective;
		this.expireDate = expire;
		this.duration = duration;
	}
	
	public BasicAd() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * action when user left clicks on the ads
	 */
	public abstract void onLeftClick();

	/**
	 * action when user right clicks on the ads
	 */
	public abstract void onRightClick();

	/**
	 * action when user moves the mouse on the ads
	 */
	public abstract void onMouseOver();

	/**
	 * Updates this ad.
	 * 
	 * @param elapsedTime
	 */
	public abstract void update(long elapsedTime);

	/**
	 * Renders this ad to specified graphics context.
	 * 
	 * @param g
	 */
	public abstract void render(Graphics2D g);
	
	
	/**
	 * Renders ad image to specified graphics context and specified location.
	 * 
	 * @param g
	 */
	public abstract void render(Graphics2D g, int x, int y);
	
	/**
	 * Renders this ad to specified panel context.
	 * 
	 * @param g
	 */
	public abstract void render(JPanel p);

	/**
	 * Renders ad image to specified panel context and specified location.
	 * 
	 * @param g
	 * @param x
	 * @param y
	 */
	public abstract void render(JPanel p, int x, int y);

	/**
	 * get ads's name
	 * 
	 * @return ads's name
	 */
	public String getName() {
		return this.name;
	};

	/**
	 * set ads's name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	};

	/**
	 * get ads's start time
	 * 
	 * @return ads's start time
	 */
	public Date getEffectiveDate() {
		return this.effectiveDate;
	};

	/**
	 * set ads's start time
	 * 
	 * @param startTime
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	};

	/**
	 * get ads's end time
	 * 
	 * @return ads's end time
	 */
	public Date getExpireDate() {
		return expireDate;
	};

	/**
	 * set ads's end time
	 * 
	 * @param endTime
	 */
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	/**
	 * get ads's duration
	 * 
	 * @return
	 */
	public long getDuration() {
		return this.duration;
	};

	/**
	 * set ad's duration
	 * 
	 * @param attributes
	 */

	public void setDuration(long duration) {
		this.duration = duration;
	}

	/**
	 * Accelerates ad's horizontal speed by accel and limit the speed to
	 * maxSpeed.
	 * 
	 * @param elapsedTime
	 * @param accel
	 * @param maxSpeed
	 */
	public void addHorizontalSpeed(long elapsedTime, double accel,
			double maxSpeed) {
		double speed = this.horizontalSpeed + accel * elapsedTime;
		this.horizontalSpeed = (speed < maxSpeed) ? speed : maxSpeed;
	}

	/**
	 * Accelerates ad vertical speed by accel and limit the speed to maxSpeed.
	 * 
	 * @param elapsedTime
	 * @param accel
	 * @param maxSpeed
	 */
	public void addVerticalSpeed(long elapsedTime, double accel, double maxSpeed) {
		double speed = this.verticalSpeed + accel * elapsedTime;
		this.verticalSpeed = (speed < maxSpeed) ? speed : maxSpeed;
	}

	/**
	 * Forces ad's x position to specified coordinate.
	 * 
	 * @param xs
	 */
	public void forceX(double xs) {
		this.currentX = xs;
	}

	/**
	 * Forces ad's y position to specified coordinate.
	 * 
	 * @param ys
	 */
	public void forceY(double ys) {
		this.currentY = ys;
	}

	/**
	 * Returns the height of this ad.
	 * 
	 * @return
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Returns horizontal speed of the ad.
	 * 
	 * @return
	 */
	public double getHorizontalSpeed() {
		return this.horizontalSpeed;
	}

	/**
	 * Returns ad x coordinate relative to screen area.
	 * 
	 * @return
	 */
	public double getScreenX() {
		return this.screenX;
	}

	/**
	 * Returns ad y coordinate relative to screen area.
	 * 
	 * @return
	 */
	public double getScreenY() {
		return this.screenY;
	}

	/**
	 * Returns vertical speed of the ad.
	 * 
	 * @return
	 */
	public double getVerticalSpeed() {
		return this.verticalSpeed;
	}

	/**
	 * Returns the width of this ad.
	 * 
	 * @return
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Returns ad x coordinate.
	 * 
	 * @return
	 */
	public double getX() {
		return this.currentX;
	}

	/**
	 * Returns ad y coordinate.
	 * 
	 * @return
	 */
	public double getY() {
		return this.currentY;
	}

	/**
	 * Returns active state of this ad.
	 * 
	 * @return
	 */
	public boolean isActive() {
		return this.status;
	}

	/**
	 * Returns whether the screen is still on background screen area.
	 * 
	 * @return
	 */
	public boolean isOnScreen() {
		return this.onScreen;
	}

	/**
	 * Returns whether the screen is still on background screen area in
	 * specified offset.
	 * 
	 * @param leftOffset
	 * @param topOffset
	 * @param rightOffset
	 * @param bottomOffset
	 * @return
	 */
	public boolean isOnScreen(int leftOffset, int topOffset, int rightOffset,
			int bottomOffset) {
		return this.onScreen;
	}

	/**
	 * Moves this ad as far as delta x (dx) and delta y (dy).
	 * 
	 * @param dx
	 * @param dy
	 */
	public void move(double dx, double dy) {
		this.currentX += dx;
		this.currentY += dy;
	}

	/**
	 * Moves ad x coordinate as far as delta x (dx).
	 * 
	 * @param dx
	 */
	public void moveX(double dx) {
		this.currentX += dx;
	}

	/**
	 * Moves ad y coordinate as far as delta y (dy).
	 * 
	 * @param dy
	 */
	public void moveY(double dy) {
		this.currentY += dy;
	}

	/**
	 * Sets active state of this ad, only active ad will be updated and rendered
	 * and check for collision.
	 * 
	 * @param b
	 */
	public void setActive(boolean active) {
		this.status = active;
	}

	/**
	 * sets rendering object's height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Sets horizontal speed of the ad, the speed is based on actual time in
	 * milliseconds, 1 means the ad is moving as far as 1000 (1x1000ms) pixels
	 * in a second to the right, while negative value (-1) means the ad is
	 * moving to the left.
	 * 
	 * @param vx
	 */
	public void setHorizontalSpeed(double vx) {
		this.horizontalSpeed = vx;
	}

	/**
	 * Sets this ad coordinate to specified location on the background.
	 * 
	 * @param xs
	 * @param ys
	 */
	public void setLocation(double xs, double ys) {
		this.currentX = xs;
		this.currentY = ys;
	}

	/**
	 * Moves ad with specified angle, and speed.
	 * 
	 * @param speed
	 * @param angleDir
	 */
	public void setMovement(double speed, double angleDir) {
	}

	/**
	 * Sets the speed of this ad, the speed is based on actual time in
	 * milliseconds, 1 means the ad is moving as far as 1000 (1x1000ms) pixels
	 * in a second.
	 * 
	 * @param vx
	 * @param vy
	 */
	public void setSpeed(double vx, double vy) {
		this.horizontalSpeed = vx;
		this.verticalSpeed = vy;
	}

	/**
	 * Sets vertical speed of the ad, the speed is based on actual time in
	 * milliseconds, 1 means the ad is moving as far as 1000 (1x1000ms) pixels
	 * in a second to the bottom, while negative value (-1) means the ad is
	 * moving to the top.
	 * 
	 * @param vy
	 */
	public void setVerticalSpeed(double vy) {
		this.verticalSpeed = vy;
	}

	/**
	 * sets rendering object's width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Sets ad x coordinate.
	 * 
	 * @param xs
	 */
	public void setX(double xs) {
		this.currentX = xs;
	}

	/**
	 * Sets ad y coordinate.
	 * 
	 * @param ys
	 */
	public void setY(double ys) {
		this.currentY = ys;
	}

	public void setParameters(NamedNodeMap attributes) {

	}
	
	/**
	 * opens the targetURL in the default browser
	 * http://www.mkyong.com/java/open-browser-in-java-windows-or-linux/
	 */
	public void openBrowser() {
		String os = System.getProperty("os.name").toLowerCase();
		Runtime rt = Runtime.getRuntime();
		try {
			if (os.indexOf("win") >= 0) {

				// this doesn't support showing urls in the form of
				// "page.html#nameLink"
				rt.exec("rundll32 url.dll,FileProtocolHandler " + targetURL);

			} else if (os.indexOf("mac") >= 0) {

				rt.exec("open " + targetURL);

			} else if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0) {

				// Do a best guess on unix until we get a platform independent
				// way
				// Build a list of browsers to try, in this order.
				String[] browsers = { "epiphany", "firefox", "mozilla",
						"konqueror", "netscape", "opera", "links", "lynx" };

				// Build a command string which looks like
				// "browser1 "url" || browser2 "url" ||..."
				StringBuffer cmd = new StringBuffer();
				for (int i = 0; i < browsers.length; i++)
					cmd.append((i == 0 ? "" : " || ") + browsers[i] + " \""
							+ targetURL + "\" ");

				rt.exec(new String[] { "sh", "-c", cmd.toString() });

			} else {
				return;
			}
		} catch (Exception e) {
			return;
		}
	}
}

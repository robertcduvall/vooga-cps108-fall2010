package arcade.ads;

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

public abstract class BasicAds {

	protected String name;
	protected int xMin;
	protected int xMax;
	protected int yMin;
	protected int yMax;
	protected long startTime;
	protected long endTime;

	public BasicAds(String name) {
		this.name = name;
	}

	public BasicAds(String name, int xMin, int xMax, int yMin, int yMax) {
		this.name = name;
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
	}

	/**
	 * action when user clicks on the ads
	 */
	public abstract void onClick();

	/**
	 * action when user moves the mouse on the ads
	 */
	public abstract void onMouseOver();

	/**
	 * update ads
	 */
	public abstract void update();

	/**
	 * render ads
	 */
	public abstract void render();

	/**
	 * check if ad is active
	 */
	public abstract boolean isActive();

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
	public long getStartTime() {
		return this.startTime;
	};

	/**
	 * set ads's start time
	 * 
	 * @param startTime
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	};

	/**
	 * get ads's end time
	 * 
	 * @return ads's end time
	 */
	public long getEndTime() {
		return endTime;
	};

	/**
	 * set ads's end time
	 * 
	 * @param endTime
	 */
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	
	/**
	 * get ads's duration
	 * 
	 * @return
	 */
	public long getDuration() {
		return getEndTime() - getStartTime();
	};
}

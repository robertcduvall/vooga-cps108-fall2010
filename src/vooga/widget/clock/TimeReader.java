package vooga.widget.clock;

/**
 * A TimeReader simply returns a String representing the 
 * current time. The current time should be retrieved by using
 * the WorldClock class in the resources package.
 * 
 * @author Daniel Koverman
 *
 */
public interface TimeReader {
	
	/**
	 * Return the current time as a String to 
	 * be displayed on a clock
	 * @return String representing current time
	 */
	public String getTime();

}

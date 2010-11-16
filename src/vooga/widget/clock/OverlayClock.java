package vooga.widget.clock;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Map;

import vooga.engine.overlay.OverlayString;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.resource.clock.WorldClock;

/**
 * OverlayClock displays a string representing the current 
 * local time on the screen. It is an extension of OverlayString 
 * where the String to be displayed is always the local time. The 
 * time is determined on update which means that the clock can be 
 * paused. If this behavior is not desired, do not place the 
 * OverlayClock in a GameState which renders without updating. The 
 * font and color of the clock are customizable. If the clock is 
 * specifying the incorrect time, make sure that you set the proper 
 * time zone for WorldClock. The default way to display time is 
 * the 24 time format.
 * <br/><br/>
 * Sample initialization using default 24 hour time:
 * <xmp>
 * WorldClock.setTimeZone("EST");
 * OverlayClock clock = new OverlayClock();
 * clock.setLocation(WIDTH/2, HEIGHT/2);
 * </xmp>
 * <br/><br/>
 * Sample initialization using default 12 hour time with am/pm:
 * <xmp>
 * WorldClock.setTimeZone("EST");
 * OverlayClock clock = new OverlayClock(new AMandPMReader());
 * clock.setLocation(WIDTH/2, HEIGHT/2);
 * </xmp>
 * @author Daniel Koverman
 *
 */
public class OverlayClock extends OverlayString{

	private static final long serialVersionUID = 1;
	private static final TimeReader DEFAULT_TIME_READER = new TwentyFourHourReader();
	private static TimeReader timeReader;
	
	/**
	 * Create a clock with default font and color
	 */
	public OverlayClock(){
		this(DEFAULT_TIME_READER, DEFAULT_FONT, DEFAULT_COLOR);
	}
	
	/**
	 * Create a clock with default font and color and 
	 * a custom TimeReader
	 */
	public OverlayClock(TimeReader timeReader){
		this(timeReader, DEFAULT_FONT, DEFAULT_COLOR);
	
	}
	
	/**
	 * Create a clock with default color and custom font
	 * @param font Font used to render the clock digits
	 */
	public OverlayClock(Font font){		
		this(DEFAULT_TIME_READER, font, DEFAULT_COLOR);
	}
	
	/**
	 * Create a clock with default font and custom color
	 * @param color Color used to render the clock
	 */
	public OverlayClock(Color color){		
		this(DEFAULT_TIME_READER, DEFAULT_FONT, color);
	}
	
	/**
	 * Create a clock with custom font and color
	 * @param font Font used to render clock digits
	 * @param color Color used to render clock
	 */
	public OverlayClock(TimeReader timeReader, Font font, Color color){		
		super(null, font, color);
		this.timeReader = timeReader;
		setString(getTime());
	}
	
	public OverlayClock(Map<String, String> attributes, OverlayTracker tracker){
		super(attributes, tracker);
		timeReader = DEFAULT_TIME_READER;
		setString(getTime());
	}
	
	/**
	 * Updates the string to be displayed to be the 
	 * current time designated by WorldClock.
	 * 
	 * @param elapsedTime not used
	 */
	@Override
	public void update(long elapsedTime){
		setString(getTime());
	}
	
	private String getTime(){
		return timeReader.getTime();
	}
}

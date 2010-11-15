package vooga.engine.overlay;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Map;

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
 * time zone for WorldClock.
 * <br/><br/>
 * Sample initialization:
 * <pre>WorldClock.setTimeZone("EST");
 * OverlayClock clock = new OverlayClock();
 * clock.setLocation(WIDTH/2, HEIGHT/2);
 * </pre>
 * 
 * @author Daniel Koverman
 *
 */
public class OverlayClock extends OverlayString{

	private static final long serialVersionUID = 1;
	
	/**
	 * Create a clock with default font and color
	 */
	public OverlayClock(){
		this(DEFAULT_FONT, DEFAULT_COLOR);
	}
	
	/**
	 * Create a clock with default color and custom font
	 * @param font Font used to render the clock digits
	 */
	public OverlayClock(Font font){		
		this(font, DEFAULT_COLOR);
	}
	
	/**
	 * Create a clock with default font and custom color
	 * @param color Color used to render the clock
	 */
	public OverlayClock(Color color){		
		this(DEFAULT_FONT, color);
	}
	
	/**
	 * Create a clock with custom font and color
	 * @param font Font used to render clock digits
	 * @param color Color used to render clock
	 */
	public OverlayClock(Font font, Color color){		
		super(null, font, color);
		setString(get24HourTime());
	}
	
	public OverlayClock(Map<String, String> attributes, OverlayTrackerTemp tracker){
		super(attributes, tracker);
		setString(get24HourTime());
	}
	
	/**
	 * Updates the string to be displayed to be the 
	 * current time designated by WorldClock.
	 * 
	 * @param elapsedTime not used
	 */
	@Override
	public void update(long elapsedTime){
		setString(get24HourTime());
	}
	
	private String get24HourTime(){
		return WorldClock.getLocalTimeAsString(WorldClock.HOUR) + ":" + WorldClock.getLocalTimeAsString(WorldClock.MINUTE);
	}
}

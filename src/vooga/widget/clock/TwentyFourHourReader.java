package vooga.widget.clock;

import vooga.engine.resource.clock.WorldClock;

/**
 * Reads the time in 24 hour time. Displays number of 
 * hours since midnight followed by a colon followed by 
 * number of minutes into the hour.
 * Sample outputs: 17:43, 3:03, 12:00, 0:00
 * @author Daniel Koverman
 *
 */
public class TwentyFourHourReader implements TimeReader{

	@Override
	public String getTime() {
		String hour = Integer.toString(WorldClock.getLocalTime(WorldClock.HOUR));
		String minute = Integer.toString(WorldClock.getLocalTime(WorldClock.MINUTE));
		if(minute.length()==1){
			minute = "0" + minute;
		}
		return hour + ":" + minute;
	}

}

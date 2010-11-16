package vooga.widget.clock;

import vooga.engine.resource.clock.WorldClock;

/**
 * Reads the time in 12 hour time. Displays number of 
 * hours since midnight or noon followed by a colon followed by 
 * number of minutes into the hour followed by postfix designating 
 * am or pm. Midnight is 12:00am and noon is 12:00pm
 * Sample output: 4:23am, 12:01am, 8:25pm
 * @author Daniel Koverman
 *
 */
public class AMandPMReeader implements TimeReader{
	
	private final static int FIRST_HOUR_OF_PM = 12;
	
	@Override
	public String getTime() {
		int hour = WorldClock.getLocalTime(WorldClock.HOUR);
		String postfix = "am";
		if(hour>=FIRST_HOUR_OF_PM){
			postfix = "pm";
		}
		String minute = Integer.toString(WorldClock.getLocalTime(WorldClock.MINUTE));
		if(minute.length()==1){
			minute = "0" + minute;
		}
		return hour + ":" + minute + postfix;
	}

}

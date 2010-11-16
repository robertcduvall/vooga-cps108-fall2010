package vooga.widget.clock;

import vooga.engine.resource.clock.WorldClock;

/**
 * Reads the time in military time. Displays number of 
 * hours since midnight followed followed by 
 * number of minutes into the hour.
 * Sample output: 1725, 0240, 1204, 0000, 0024
 * @author Daniel Koverman
 *
 */
public class MilitaryTime implements TimeReader{
	
	@Override
	public String getTime() {
		String hour = Integer.toString(WorldClock.getLocalTime(WorldClock.HOUR));
		String minute = Integer.toString(WorldClock.getLocalTime(WorldClock.MINUTE));
		hour = makeTwoDigits(hour);
		minute = makeTwoDigits(minute);
		return hour + minute;
	}
	
	private String makeTwoDigits(String number){
		if(number.length()==1){
			number = "0" + number;
		}
		return number;
	}

}

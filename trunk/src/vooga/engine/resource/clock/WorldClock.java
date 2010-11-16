package vooga.engine.resource.clock;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Allows for checking the time in the local time zone and Coordinated Universal
 * Time (UTC). The local time zone by default is UTC, but this can be set to any
 * time zone using the TimeZone class or any time zone unique string ids set up
 * by the TimeZone class such as "EST" or "Pacific Standard Time" or "GMT+10" or
 * "America/Los_Angelos". A complete list of available time zones can be found
 * at http://mindprod.com/jgloss/timezone.html
 * 
 * The local or UTC time can be retrieved as milliseconds since January 1, 1970
 * 00:00:00.00 Individual components of the time from the number of milliseconds
 * in the current time to the current year can also be retrieved.
 * 
 * This class can be used to create game events which depend on real world time.
 * For instance, the background of the game could change to mirror the time of
 * day; scenery in the game could reflect the real world season; after 1 hour of
 * the game being open irrespective of how long the game has been running
 * according the game clock the game could display a message telling the user to
 * take a break and go outside like the Wii.
 * 
 * WorldClock is dependent upon java.util.Calendar and java.util.TimeZone.
 * 
 * @author Daniel Koverman
 * @date September 26, 2010
 */
public class WorldClock {
	private static final String DEFAULT_TIME_ZONE_SID = "UTC";

	// Values to be referenced for retrieving specific components of the
	// time/date
	public static final int MILLISECOND = Calendar.MILLISECOND;
	public static final int SECOND = Calendar.SECOND;
	public static final int MINUTE = Calendar.MINUTE;
	public static final int HOUR = Calendar.HOUR_OF_DAY;
	public static final int DAY = Calendar.DAY_OF_MONTH;
	public static final int MONTH = Calendar.MONTH;
	public static final int YEAR = Calendar.YEAR;

	private static TimeZone timeZone = TimeZone
			.getTimeZone(DEFAULT_TIME_ZONE_SID);
	private static Calendar localCal = Calendar.getInstance(timeZone);
	private static Calendar utcCal;

	/**
	 * Set the local time zone using a TimeZone object set to the correct time
	 * zone. Local time and time/date components will be determined from this
	 * time zone.
	 * 
	 * @param tz
	 *            TimeZone object set to the local time zone
	 */
	public static void setTimeZone(TimeZone tz) {
		timeZone = tz;
		localCal = Calendar.getInstance(timeZone);
	}

	/**
	 * Set the local time zone with a time zone String id. Local time and
	 * time/date components will be determined from this time zone.
	 * 
	 * Examples of time zone Strind ids include: "EST" or
	 * "Pacific Standard Time" or "GMT+10" or "America/Los_Angelos"
	 * 
	 * @param timeZoneSID
	 *            the String identification of the local time zone
	 */
	public static void setTimeZone(String timeZoneSID) {
		timeZone = TimeZone.getTimeZone(timeZoneSID);
		localCal = Calendar.getInstance(timeZone);
	}

	/**
	 * Returns the current local time measured as millisecond from January 1,
	 * 1970 00:00.00 in the local time zone.
	 * 
	 * @return number of milliseconds since January 1, 1970 00:00.00 in the
	 *         local time zone.
	 */
	public static long getLocalTime() {
		return System.currentTimeMillis()
				+ timeZone.getOffset(System.currentTimeMillis());
	}

	/**
	 * Returns an individual component of the local time and date. Available
	 * components are milliseconds, second, minutes, hours on a 24 hour clock,
	 * day, month, and year.
	 * 
	 * For example, if in the local time zone it is April 13, 2008 17:32:42.04 -
	 * WorldClock.getLocalTime(WorldClock.MILLISECOND) returns 4
	 * WorldClock.getLocalTime(WorldClock.SECOND) returns 42
	 * WorldClock.getLocalTime(WorldClock.MINUTE) returns 32
	 * WorldClock.getLocalTime(WorldClock.HOUR) returns 17
	 * WorldClock.getLocalTime(WorldClock.DAY) returns 13
	 * WorldClock.getLocalTime(WorldClock.MONTH) returns 4
	 * WorldClock.getLocalTime(WorldClock.HOUR) returns 2008
	 * 
	 * @param integer
	 *            uniquely identifying desired time/date component. These ids
	 *            are static constants of the WorldClock class.
	 * @return requested component of the local date/time
	 */
	public static int getLocalTime(int type) {
		localCal.setTimeInMillis(System.currentTimeMillis());
		return localCal.get(type);
	}

	/**
	 * Returns the current local time measured as millisecond from January 1,
	 * 1970 00:00.00 in Coordinated Universal Time. This is also the system
	 * time.
	 * 
	 * @return number of milliseconds since January 1, 1970 00:00.00 UTC
	 */
	public static long getUTCTime() {
		return System.currentTimeMillis();
	}

	/**
	 * Returns an individual component of the UTC time and date. Available
	 * components are milliseconds, second, minutes, hours on a 24 hour clock,
	 * day, month, and year.
	 * 
	 * For example, if the UTC time and date is April 13, 2008 17:32:42.04 -
	 * WorldClock.getLocalTime(WorldClock.MILLISECOND) returns 4
	 * WorldClock.getLocalTime(WorldClock.SECOND) returns 42
	 * WorldClock.getLocalTime(WorldClock.MINUTE) returns 32
	 * WorldClock.getLocalTime(WorldClock.HOUR) returns 17
	 * WorldClock.getLocalTime(WorldClock.DAY) returns 13
	 * WorldClock.getLocalTime(WorldClock.MONTH) returns 4
	 * WorldClock.getLocalTime(WorldClock.HOUR) returns 2008
	 * 
	 * @param integer
	 *            uniquely identifying desired time/date component. These ids
	 *            are static constants of the WorldClock class.
	 * @return requested component of the UTC date/time
	 */
	public static int getUTCTime(int type) {
		utcCal.setTimeInMillis(System.currentTimeMillis());
		return utcCal.get(type);
	}

}

package arcade.ads.util;

import java.util.Date;

/**
 * This is simple class will provide time for all ads
 * 
 * @author Hao He (hh89@duke.edu)
 * @author Nick Straub (njs7@duke.edu)
 * @author Scott Winkleman (saw26@duke.edu)
 * @author Kate Yang (kly2@duke.edu)
 * 
 * @version 1.0
 */
public class AdDate extends Date {

	private String date;
	private String time;
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;
	private long militime;

	public AdDate(String d) {
		this.date = d.split(" ")[0];
		this.time = d.split(" ")[1];
		this.year = Integer.parseInt(date.split("/")[2]);
		this.month = Integer.parseInt(date.split("/")[0]);
		this.day = Integer.parseInt(date.split("/")[1]);

		this.hour = Integer.parseInt(time.split(":")[0]);
		this.minute = Integer.parseInt(time.split(":")[1]);
		this.second = Integer.parseInt(time.split(":")[2]);

		setTimeMili(year, month, day, hour, minute, second);
	}

	/**
	 * Change year, month .... input to milliseconds since January 1, 1970,
	 * 00:00:00 GMT
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 */
	public void setTimeMili(int year, int month, int day, int hour, int minute,
			int second) {
		this.militime = (new Date(year - 1900, month - 1, day, hour, minute,
				second)).getTime();
	}

	/**
	 * Return a Date object
	 * 
	 * @param myDate
	 *            - null, used for differentiating this method from super
	 *            class's method
	 * @return
	 */
	public Date getDate(String myDate) {
		return (new Date(this.militime));
	}

	public static void main(String[] args) {
		AdDate t = new AdDate("5/5/1987 15:54:32");
		System.out.println(t.getDate(""));

	}

}

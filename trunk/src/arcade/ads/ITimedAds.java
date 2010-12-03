package arcade.ads;

public interface ITimedAds {

	/**
	 * get ads's start time
	 * 
	 * @return ads's start time
	 */
	public long getStartTime();

	/**
	 * set ads's start time
	 * 
	 * @param startTime
	 */
	public void setStartTime(long startTime);

	/**
	 * get ads's end time
	 * 
	 * @return ads's end time
	 */
	public long getEndTime();

	/**
	 * set ads's end time
	 * 
	 * @param endTime
	 */
	public void setEndTime(long endTime);
}

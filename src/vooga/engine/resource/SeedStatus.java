package vooga.engine.resource;

/**
 * Keeps track of the value of the seed, the number of times a seed is used, and
 * the seed string id. Used in the Randomizer class to maintain which seeds were
 * used and how many times in order to simplify replaying a series of events
 * with elements of randomness.
 * 
 * @author Daniel Koverman
 * @date September 26, 2010
 * 
 */
public class SeedStatus {
	private long seedValue;
	private int timesUsed;
	private String name;

	/**
	 * Create an unnamed, unused, seed of a given value
	 * 
	 * @param seedValue
	 *            value of the seed for seeding random number generators
	 */
	public SeedStatus(long seedValue) {
		this(seedValue, 0);
	}

	/**
	 * Create an unnamed seed used a specified number of times
	 * 
	 * @param seedValue
	 *            value of the seed for seeding random number generators
	 * @param timesUsed
	 *            the number of random numbers generated using this seed
	 */
	public SeedStatus(long seedValue, int timesUsed) {
		this(seedValue, timesUsed, "");
	}

	/**
	 * Create an unused seed with a given name and value
	 * 
	 * @param seedValue
	 *            value of the seed for seeding random number generators
	 * @param name
	 *            String id of the seed
	 */
	public SeedStatus(long seedValue, String name) {
		this(seedValue, 0, name);
	}

	/**
	 * Create a named seed of a given value used a given number of times
	 * 
	 * @param seedValue
	 *            value of the seed for seeding random number generators
	 * @param timesUsed
	 *            the number of random numbers generated using this seed
	 * @param name
	 *            String id of the seed
	 */
	public SeedStatus(long seedValue, int timesUsed, String name) {
		this.seedValue = seedValue;
		this.timesUsed = timesUsed;
		this.name = name;
	}

	/**
	 * Get the value of the seed for seeding a random number generator
	 * 
	 * @return the seed value
	 */
	public long getValue() {
		return seedValue;
	}

	/**
	 * Get the number of times the seed was used for resetting a generator to a
	 * the number of times used after seeding from some value
	 * 
	 * @return the number of times a seed was used
	 */
	public int timesUsed() {
		return timesUsed;
	}

	/**
	 * Increment the number of times a seed was used. To insure replayability
	 * this should be called on a seed status every time the generator seeded
	 * with the corresponding seed is used
	 */
	public void incTimesUsed() {
		++timesUsed;
	}

	/**
	 * Reset the number of times a seed is used to zero.
	 */
	public void resetTimesUsed() {
		timesUsed = 0;
	}

	/**
	 * Retrieve the string identification of a seed status
	 * 
	 * @return the string id
	 */
	public String getName() {
		return name;
	}
}

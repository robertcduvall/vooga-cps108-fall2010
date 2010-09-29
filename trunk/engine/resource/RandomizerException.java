package engine.resource;

/**
 * RandomizerException is thrown whenever Randomizer attempts to work with 
 * some kind of inconsistent parameters. This can arise when Randomizer is passed
 * two differing Number types when attempting to specify a range or when the first 
 * parameter when specifying a range is larger than the second. Examples:
 * 
 * @author David Herzka
 *
 */
public class RandomizerException extends Exception {

	/**
	 * Thrown when invalid randomizer actions are performed.
	 */
	private static final long serialVersionUID = 6270428264890360933L;


	/**
	 * Constructs a new {@code GameClockException} that includes the current
	 * stack trace.
	 */
	public RandomizerException() {
		super();
	}
	
	/**
	 * Constructs a new {@code GameClockException} with the current stack
	 * trace and the specified detail message.
	 * 
	 * @param detailMessage
	 *            the detail message for this exception.
	 */
	public RandomizerException(String detailMessage) {
		super(detailMessage);
	}
}

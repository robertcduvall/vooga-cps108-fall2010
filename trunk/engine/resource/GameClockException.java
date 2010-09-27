package engine.resource;

public class GameClockException extends Exception {

	/**
	 * Thrown when invalid game clock events are performed.
	 */
	private static final long serialVersionUID = -680148902235997157L;

	
	/**
	 * Constructs a new {@code GameClockException} that includes the current
	 * stack trace.
	 */
	public GameClockException() {
		super();
	}
	
	/**
	 * Constructs a new {@code GameClockException} with the current stack
	 * trace and the specified detail message.
	 * 
	 * @param detailMessage
	 *            the detail message for this exception.
	 */
	public GameClockException(String detailMessage) {
		super(detailMessage);
	}
}

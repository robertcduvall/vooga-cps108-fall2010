package vooga.engine.resource.clock;

/**
 * Thrown when an invalid GameClock Operation is performed. These operations
 * could include reseting, pausing, or unpausing the GameCLock before it is started;
 * pausing the GameClock when it is already paused; unpausing the GameClock when 
 * the GameClock is already unpaused.
 * 
 * When these exceptions occur, nothing happens within GameClock. For instance a 
 * command to pause a clock that is paused or not yet started is ignored. Generally 
 * these errors will not cause the game to fail, but rather indicate that the code 
 * using the GameClock is improperly implemented. For instance, perhaps the code is 
 * not designed to guarantee that the clock starts before other operations are begun. 
 * Or two different parts of the code have overlapping cases which cause them to pause
 * the game clock.
 * 
 * @author David Herzka
 *
 */
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


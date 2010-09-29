package engine.resource;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Collection;
import java.util.Map;
import javax.swing.Timer;

/**
 * 
 * Keeps track of the game time, manages timers which should occur based on passing
 * of in game time, and allows for setting markers in time to determine how much 
 * time has elapsed from a point in time. The time kept by this class should be used 
 * in all part of the game which pass according to time elapsed in game in order to
 * keep timed events synchronized.
 * 
 * The primary usefulness of GameClock is the ability for pausing in the game to 
 * pause the game time, which allows real world time to elapse without in game 
 * time elapsing. Therefore, any objects which move/draw/update based on in game 
 * time will automatically stop when the game is paused. This includes timers 
 * which GameClock is set to manage.
 * 
 * Time markers simplify updating time in a game loop as well as allow the developer
 * to determine time elapsed since certain in game events. In order to perform a simple
 * time elapsed calculation in a game loop one could use:
 * {
 *     GameClock.beginTime();
 *     GameClock.setMarker("gameLoop");
 *     while(true){
 *         updateGame(GameClock.advanceMarker("gameLoop");
 *     }
 * }
 * This sets a marker at the beginning of game time and moves the marker up to the 
 * current game time each time the game loop is executed, returning the time elapsed 
 * since the last update of the "gameLoop" marker for use in update calculations.
 * 
 * The first operation of the game clock is to start it with GameClock.start(). Until 
 * the clock is started, resetting, pausing, and unpausing will throw exceptions. Once 
 * time is started it can be paused, unpaused, or reset to time zero. However, if the 
 * game is paused while it is already paused or unpaused while it is running, it will 
 * throw an exception.
 * 
 * @author Daniel Koverman	
 * @date September 27, 2010
 */
public class GameClock {
	private static long timeAtStart; // system time in milliseconds when timer
	// begins
	private static long timePaused; // amount of time the clock has been paused
	// since start or last reset
	private static long startOfPause; // time when the game was last paused
	private static boolean running; // true if clock is progressing, false if
	// clock is paused
	private static boolean started; // true if clock has been initialized with
	// begin()
	private static List<Timer> timers; // collection of timers GameClock is
	private static Map<String, Long> markers;

	/**
	 * Stop the GameClock from and all timers from advancing. If time is already
	 * paused or has not begun, no change will occur and an error message is
	 * produced.
	 * @throws GameClockException 
	 */
	public static void pause() throws GameClockException {
		if (!started) {
			throw new GameClockException("Clock not started");
		} else if (!running) {
			throw new GameClockException("Clock already paused");
		} else {
			startOfPause = System.currentTimeMillis();
			running = false;
			pauseAllTimers();
		}
	}

	/**
	 * Start the GameClock and resume advance of all timers. It time is already
	 * progressing or has not begun, no change will occure and an error message
	 * is produced.
	 * @throws GameClockException 
	 */
	public static void unpause() throws GameClockException {
		if (!started) {
			throw new GameClockException("Clock not started");
		} else if (running) {
			throw new GameClockException("Clock already running");
		} else {
			timePaused += System.currentTimeMillis() - startOfPause;
			running = true; // This should be true here, right?
			unpauseAllTimers();
		}
	}

	/**
	 * Initializes the GameClock at zero time. If time has already begun, no
	 * change occurs and an error message is produced.
	 * @throws GameClockException 
	 */
	public static void start() throws GameClockException {
		if (!started) {
			timeAtStart = System.currentTimeMillis();
			started = true;
			running = true;
			timers = new ArrayList<Timer>();
			markers = new HashMap<String, Long>();
		} else {
			throw new GameClockException("Clock already started");
		}
	}

	/**
	 * Resets the GameClock so that current time is considered time zero and
	 * time paused is set to zero. All timers are paused to render them inactive
	 * and ArrayList of timers is reset so that GameClock no longer manages any
	 * timer.
	 * @throws GameClockException 
	 */
	public static void reset() throws GameClockException {
		if (started) {
			timeAtStart = System.currentTimeMillis();
			running = true;
			pauseAllTimers();
			timers = new ArrayList<Timer>();
		} else {
			throw new GameClockException("Clock must be started before being reset");
		}
	}

	/**
	 * Resets the GameClock so that current time is considered time zero and
	 * time paused is set to zero. If preserveTimers parameter is false, all
	 * timers are paused to render them inactive and ArrayList of timers is
	 * reset so that GameClock no longer manages any timer. If preserveTimers
	 * parameter is true the Timers continue to operate as normal.
	 * @throws GameClockException 
	 */
	public static void reset(boolean preserveTimers) throws GameClockException {
		if (started) {
			timeAtStart = System.currentTimeMillis();
			running = true;
			if (!preserveTimers) {
				pauseAllTimers();
				timers = new ArrayList<Timer>();
			}
		} else {
			throw new GameClockException("Clock must be started before being reset");
		}
	}

	/**
	 * Returns the amount of time since the game has started excepting time
	 * passed while the game was paused.
	 * 
	 * @return time elapsed in game
	 */
	public static long getTime() {
		if (running)
			return System.currentTimeMillis() - timeAtStart - timePaused;
		else
			return System.currentTimeMillis() - startOfPause - timeAtStart
					- timePaused + System.currentTimeMillis();
	}

	/**
	 * Returns whether or not the GameClock has been initialized
	 * 
	 * @return true if game has been initialized
	 */
	public static boolean isStarted() {
		return started;
	}

	/**
	 * Return whether or not the GameClock is currently running/progressing in
	 * time
	 * 
	 * @return true if time is not paused
	 */
	public static boolean isRunning() {
		return running;
	}

	/**
	 * Creates a Timer which will set off the ActionListener when the game time
	 * reaches a specified amount of time
	 * 
	 * @param alarmTime
	 *            time from game start in milliseconds for when the Timer should
	 *            trigger
	 * @param action
	 *            the ActionListener to be triggered by the Timer
	 */
	public static void setAlarm(long alarmTime, ActionListener action) {
		int timeRemaining = (int) (alarmTime - getTime());
		Timer t = new Timer(timeRemaining, action);
		t.setRepeats(false);
		setCustomTimer(t);
	}

	/**
	 * Creates a Timer which will set off the ActionListener when the game time
	 * reaches a specified amount of time. If the action is set to repeat, the
	 * Timer will go off with a period equal to the amount of time since the
	 * timer went off to begin with. For instance, if the current game time is
	 * 8000ms and a repeated Timer is set to go off at 10000ms, the Timer will
	 * go off at 10000ms game time and every 2000ms after.
	 * 
	 * @param alarmTime
	 *            time from game start in milliseconds for when the Timer should
	 *            trigger
	 * @param action
	 *            the ActionListener to be triggered by the Timer
	 * @param repeat
	 *            whether or not the Timer should repeat
	 */
	public static void setAlarm(long alarmTime, ActionListener action,
			boolean repeat) {
		int timeRemaining = (int) (alarmTime - getTime());
		Timer t = new Timer(timeRemaining, action);
		t.setRepeats(repeat);
		setCustomTimer(t);
	}

	/**
	 * Creates a series of Timers which will each set off one of the
	 * ActionListener objects simultaneously when the game time reaches a
	 * specified amount of time.
	 * 
	 * @param alarmTime
	 *            time from game start in milliseconds for when the Timer should
	 *            trigger
	 * @param actions
	 *            a Collection holding all the ActionListener to be triggered by
	 *            the Timers
	 */
	public static void setAlarm(long alarmTime,
			Collection<ActionListener> actions) {
		int timeRemaining = (int) (alarmTime - getTime());
		for (ActionListener action : actions) {
			setCustomTimer(new Timer(timeRemaining, action));
		}
	}

	/**
	 * Creates a series of Timers which will each set off one of the
	 * ActionListener objects simultaneously when the game time reaches a
	 * specified amount of time. If the action is set to repeat, the Timers will
	 * all go off with a period equal to the amount of time since the timers
	 * went off to begin with. For instance, if the current game time is 8000ms
	 * and the repeated Timers are set to go off at 10000ms, the Timers will go
	 * off at 10000ms game time and every 2000ms after.
	 * 
	 * @param alarmTime
	 *            time from game start in milliseconds for when the Timer should
	 *            trigger
	 * @param actions
	 *            a Collection holding all the ActionListener to be triggered by
	 *            the Timers
	 * @param repeat
	 *            whether or not the Timer should repeat
	 */
	public static void setAlarm(long alarmTime,
			Collection<ActionListener> actions, boolean repeat) {
		int timeRemaining = (int) (alarmTime - getTime());
		for (ActionListener action : actions) {
			setCustomTimer(new Timer(timeRemaining, action));
		}
	}

	/**
	 * Creates a Timer which will set off the ActionListener after a specified
	 * amount of time
	 * 
	 * @param alarmTime
	 *            amount of time in milliseconds until the Timer should go off
	 * @param action
	 *            the ActionListener to be triggered by the Timer
	 */
	public static void setTimer(int timerTime, ActionListener action) {
		setCustomTimer(new Timer(timerTime, action));
	}

	/**
	 * Creates a Timer which will set off the ActionListener after a specified
	 * amount of time. If the action is set to repeat, the Timer will repeat
	 * with a time a delay of the same duration.
	 * 
	 * @param alarmTime
	 *            amount of time in milliseconds until the Timer should go off
	 * @param action
	 *            the ActionListener to be triggered by the Timer
	 * @param repeat
	 *            whether or not the Timer should repeat
	 */
	public static void setTimer(int timerTime, ActionListener action,
			boolean repeat) {
		setCustomTimer(new Timer(timerTime, action));
	}

	/**
	 * Creates a series of Timers which will each set off one of the
	 * ActionListener objects simultaneously after a certain amount of time.
	 * 
	 * @param alarmTime
	 *            amount of time in milliseconds until the Timer should go off
	 * @param actions
	 *            a Collection holding all the ActionListener to be triggered by
	 *            the Timers
	 */
	public static void setTimer(int timerTime,
			Collection<ActionListener> actions) {
		for (ActionListener action : actions) {
			setCustomTimer(new Timer(timerTime, action));
		}
	}

	/**
	 * Creates a series of Timers which will each set off one of the
	 * ActionListener objects simultaneously after a certain amount of time. If
	 * the action is set to repeat, the Timers will repeat with a time a delay
	 * of the same duration and again set off the ActionListeners
	 * simultaneously.
	 * 
	 * @param alarmTime
	 *            amount of time in milliseconds until the Timer should go off
	 * @param actions
	 *            a Collection holding all the ActionListener to be triggered by
	 *            the Timers
	 * @param repeat
	 *            whether or not the Timer should repeat
	 */
	public static void setTimer(int timerTime,
			Collection<ActionListener> actions, boolean repeat) {
		for (ActionListener action : actions) {
			setCustomTimer(new Timer(timerTime, action));
		}
	}

	/**
	 * Add an unstarted javax.swing.Timer object to the collection of GameClock
	 * managed timers to automatically stop and start that timer as the game
	 * pauses and unpauses. The Timer is automatically started if the time is
	 * unpaused. If the game is paused the Timer will be started when time is
	 * unpaused. Note that java.util.Timer class cannot be used.
	 * 
	 * @param custTimer
	 *            javax.swing.Timer object to be managed by GameClock
	 */
	public static void setCustomTimer(Timer custTimer) {
		if (running) {
			custTimer.start();
		} else {
			custTimer.stop();
		}
		timers.add(custTimer);
	}

	/**
	 * Add a Collection of unstarted javax.swing.Timer objects to the collection
	 * of GameClock managed timers to automatically stop and start the timers as
	 * the game pauses and unpauses. The Timers are automatically started if the
	 * time is unpaused. If the game is paused the Timers will be started when
	 * time is unpaused. Note that java.util.Timer class cannot be used.
	 * 
	 * @param custTimers
	 *            a Collection of javax.swing.Timer object to be managed by
	 *            GameClock
	 */
	public static void setCustomTimer(Collection<Timer> custTimers) {
		for (Timer custTimer : custTimers) {
			setCustomTimer(custTimer);
		}
	}

	/**
	 * Stop all Timers managed by GameClock from advancing.
	 */
	public static void pauseAllTimers() {
		for (Timer timer : timers) {
			timer.stop();
		}
	}

	/**
	 * Resume or start advancement of all Timers managed by GameClock from
	 * advancing.
	 */
	public static void unpauseAllTimers() {
		for (Timer timer : timers) {
			timer.start();
		}
	}
	
	/**
	 * Create a new time marker to later determine how much time has elapsed 
	 * from this point. Each marker is assigned a unique String id for tracking
	 * @param markerUSID The unique String id of the time marker for later reference
	 * @return the game time in milliseconds when the marker is created
	 */
	public static long createMarker(String markerUSID){
		long markerTime = getTime();
		markers.put(markerUSID, markerTime);
		return markerTime;
	}
	
	/**
	 * Find the time at which a marker was set or last updated. Effectively,
	 * this is the current location of the marker in time.
	 * @param markerUSID the unique string id of the marker
	 * @return the game time at which the marker was last created or updated
	 */
	public static long getMarkerTime(String markerUSID){
		return markers.get(markerUSID);
	}
	
	/**
	 * Bring the identified marker up to the current game time and return the difference
	 * between its former time and its new time which is the game time elapsed since the marker
	 * was created or last updated.
	 * @param markerUSID the unique string id of the marker
	 * @return the time elapsed since the marker was last created or updated
	 */
	public static long updateMarker(String markerUSID){
		long currentTime = getTime();
		long timeElapsed = currentTime - markers.get(markerUSID);
		markers.put(markerUSID, currentTime);
		return timeElapsed;
	}
	
	/**
	 * Return how much game time has elapsed sine the marker was created or last updated
	 * @param markerUSID the unique string id of the marker
	 * @return the time elapsed since the marker was last created or updated
	 */
	public static long getMarkerAge(String markerUSID){
		return markers.get(markerUSID) - getTime();
	}
}

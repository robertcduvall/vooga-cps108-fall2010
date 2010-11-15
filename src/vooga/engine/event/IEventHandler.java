package vooga.engine.event;
/**
 * The <code>IEventHandler</code> interface provides the interface for other classes to register for
 * the Event. Two methods in this class {@link #isTriggered() isTriggered} and {@link #actionPerformed() actionPerformed} need to be overridden.
 * @see EventPool
 * @author Meng Li
 * @author Hao He
 * @author Cody Kolodziejzyk
 *
 */
public interface IEventHandler {
	/**
	 * User defines the condition when the event will be triggered
	 * @return true iff the fire condition is satisfied
	 */
	public boolean isTriggered();
	/**
	 * User defines what to do after event has been triggered.
	 */
	public void actionPerformed();
	
}

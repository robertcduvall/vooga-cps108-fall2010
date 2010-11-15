package vooga.engine.event;

public interface IEventHandler {
	/**
	 * User defines the condition when the event will be triggered
	 * @return true if the condition is satisfied
	 */
	public boolean isTriggerred();
	/**
	 * User defines what to do after event has been triggered.
	 */
	public void actionPerformed();
	
}

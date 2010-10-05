package vooga.engine.event;

/**
 * Provides interface for dealing with different types of events fired within a
 * game.
 * 
 * @author Hao He
 * @author Meng Li
 * @author Cody Kolodziejzyk
 * @see examples/event
 * @Version: October 1st
 * 
 */
public interface IEvent {
	/**
	 * get the object of a class
	 * 
	 * @return Object
	 */
	public Object getSource();

	/**
	 * get the name of the event
	 * 
	 * @return String
	 */
	public String getName();
}
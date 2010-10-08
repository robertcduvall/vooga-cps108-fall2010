package vooga.games.cyberion.engine.event;


/**
 * The listener interface for receiving game events. When the game event occurs,
 * that object's actionPerformed method is invoked.
 * 
 * @author Hao He
 * @author Meng Li
 * @author Cody Kolodziejzyk
 * @see examples/event
 * @Version: September 26th
 * 
 */
public interface IEventListener {
	/**
	 * When the event is triggered, this method will be called.
	 * 
	 * @param e
	 *            The occured event
	 */
	public void actionPerformed(IEvent e);
}
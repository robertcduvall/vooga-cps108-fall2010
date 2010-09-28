package engine.event;
/**
 * A object of a class which implements this interface will be passed to the eventlistener
 * 
 * @author Hao He
 * @author Meng Li
 * @author Cody Kolodziejzyk
 * @Version: September 26th
 *gog
 */
public interface MyEvent{
	/**
	 * get the object of a class 
	 * @return Object
	 */
	public Object getSource();
	/**
	 * get the string associated with the event
	 * @return String
	 */
	public String getName();
}
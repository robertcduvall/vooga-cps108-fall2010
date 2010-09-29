package engine.event;


/**
 * A object of a class which implements this interface will be passed to the eventlistener
 * The class which implements this interface can be customized to create more methods.
 * 
 * Example:
 * public class PacmanMoveEvent implements MyEvent {
 *	private Object obj;
 *	private String sName;
 *	private int x;
 *	private int y;
 *
 *
 *	public PacmanMoveEvent(Object source, String sName, int x, int y) {
 *		obj = source;
 *		this.sName = sName;
 *		this.x = x;
 *		this.y = y;
 *	}
 *   @Override
 *   public Object getSource() {
 *	    return obj;
 *   }
 *   @Override
 *   public String getName() {
 *		return sName;
 *	}
 *	
 * @author Hao He
 * @author Meng Li
 * @author Cody Kolodziejzyk
 * @Version: September 26th
 *
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

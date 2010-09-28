package event;


/**
 * A object of a class which wants to addMyEventListener or a class which wants to listen to an event 
 * must implement this interface. If the event has been fired, the actionPerformed in the interface
 * will invoked.
 * 
 * Example:
 * public class Ghost extends AbstractEventManager implements PacmanMoveListener {
	private int x,y;

	Ghost(int x, int y) {
		this. x = x;
		this. y = y;
	}

	@Override
	public void actionPerformed(MyEvent e) {
		action((PacmanMoveEvent)e);
	}

	@Override
	public void action(PacmanMoveEvent e) {
		//do something
		setX(e.getX());
		setY(e.getY());
	}
 * 
 * @author Hao He
 * @author Meng Li
 * @author Cody Kolodziejzyk
 * @Version: September 26th
 *
 */
public interface MyEventListener {
   /**
    * When the event is triggered, this method will be called.
    * @param e:MyEvent interface
    */
   public void actionPerformed(MyEvent e);
}
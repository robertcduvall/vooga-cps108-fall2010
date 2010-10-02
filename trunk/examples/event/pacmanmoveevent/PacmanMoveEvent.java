package examples.event.pacmanmoveevent;

import examples.event.event.IEvent;

/**
 * This event occurs when pacman moves or player tries to move pacman.
 * 
 * @author Hao He
 * @author Meng Li
 * @author Cody Kolodziejzyk
 * 
 */
public class PacmanMoveEvent implements IEvent {

	private Object obj;
	private String eventName;
	private int x;
	private int y;

	public PacmanMoveEvent(Object source, String eventName, int x, int y) {
		obj = source;
		this.eventName = eventName;
		this.x = x;
		this.y = y;
	}

	public Object getSource() {
		return obj;
	}

	public String getName() {
		return eventName;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}

package GameEvent;

import engine.event.IEvent;

public class PlayerMoveEvent implements IEvent{
	private Object obj;
	private String eventName;
	private double x;
	private double y;

	public PlayerMoveEvent(Object source, String eventName, double x, double y) {
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

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}

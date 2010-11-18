package vooga.games.cyberion.events;

import vooga.engine.event.IEventHandler;

public class PlayerMoveEvent implements IEventHandler {
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

	@Override
	public boolean isTriggered() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void actionPerformed() {
		// TODO Auto-generated method stub
		
	}

}

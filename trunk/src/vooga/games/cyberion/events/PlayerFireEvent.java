package vooga.games.cyberion.events;

import vooga.engine.event.IEvent;

public class PlayerFireEvent implements IEvent {
	private Object obj;
	private String eventName;
	private double x;
	private double y;
	private int weaponPower;

	public PlayerFireEvent(Object source, String eventName, double x, double y,
			int weaponPower) {
		obj = source;
		this.eventName = eventName;
		this.x = x;
		this.y = y;
		this.weaponPower = weaponPower;
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

	public int getWeaponPower() {
		return weaponPower;
	}

}

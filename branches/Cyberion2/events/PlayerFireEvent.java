package vooga.games.cyberion.events;

import vooga.engine.event.IEventHandler;

public class PlayerFireEvent implements IEventHandler {
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
	
	@Override
	public void actionPerformed() {
		System.exit(0);
	}
	/**
	 * User defines the condition when the event will be triggered
	 */
	@Override
	public boolean isTriggered() {
		return true;
	}

}

package vooga.games.towerdefense.events;

import vooga.engine.event.IEvent;

public class BuildTowerEvent implements IEvent{

	private Object obj;
	private String eventName;
	private String towerType;
	private double x;
	private double y;

	public BuildTowerEvent(Object source, String eventName, String towerType, double x, double y) {
		obj = source;
		this.eventName = eventName;
		this.towerType = towerType;
		this.x = x;
		this.y = y;
	}

	@Override
	public Object getSource() {
		return obj;
	}

	@Override
	public String getName() {
		return eventName;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public String getTowerType(){
		return towerType;
	}

}

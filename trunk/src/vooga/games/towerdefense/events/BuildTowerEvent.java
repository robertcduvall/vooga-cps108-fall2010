package vooga.games.towerdefense.events;

import vooga.engine.event.IEvent;
import vooga.games.towerdefense.tower.Tower;

/**
 * If a tower is built, then put the tower at location (x, y)
 * 
 * @author Derek Zhou, Daniel Koverman, Justin Goldsmith
 *
 */

public class BuildTowerEvent implements IEvent{

	private Object obj;
	private String eventName;
	private Tower tower;
	private double x;
	private double y;

	public BuildTowerEvent(Object source, String eventName, Tower tower, double x, double y) {
		obj = source;
		this.eventName = eventName;
		this.tower = tower;
		this.tower.forceX(x);
		this.tower.forceY(y);
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
	
	public Tower getTower(){
		return tower;
	}

}

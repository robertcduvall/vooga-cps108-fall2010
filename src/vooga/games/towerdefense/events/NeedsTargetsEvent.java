package vooga.games.towerdefense.events;

import vooga.engine.event.IEvent;
import vooga.games.towerdefense.tower.*;

/**
 * This class pairs enemies with towers
 * 
 * @author Derek Zhou, Daniel Koverman, Justin Goldsmith
 *
 */

public class NeedsTargetsEvent implements IEvent{
	private Object obj;
	private String eventName;
	private ShootingTower tower;
	
	
	public NeedsTargetsEvent(Object source, String eventName, ShootingTower tower) {
		obj = source;
		this.eventName = eventName;
		this.tower = tower;
	}

	@Override
	public Object getSource() {
		return obj;
	}

	@Override
	public String getName() {
		return eventName;
	}

	
	public ShootingTower getTower(){
		return tower;
	}

}

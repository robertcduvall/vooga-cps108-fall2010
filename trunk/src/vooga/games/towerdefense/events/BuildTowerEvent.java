package vooga.games.towerdefense.events;

import java.util.*;

import vooga.engine.event.IEventHandler;
import vooga.games.towerdefense.tower.Tower;



public class BuildTowerEvent implements IEventHandler{
	
	private Queue<Tower> buildQueue = new LinkedList<Tower>();

	@Override
	public void actionPerformed() {
		for(Tower tower: buildQueue){
			buildTower(tower);
		}
		
	}

	@Override
	public boolean isTriggered() {
		return !buildQueue.isEmpty();
	}

	public void addTower(Tower tower){
		buildQueue.add(tower);
	}
	
	private void buildTower(Tower tower){
		//TODO implement building Tower on screen
	}
	

}

package vooga.games.towerdefense.events;

import java.util.LinkedList;
import java.util.Queue;

import vooga.engine.core.PlayField;
import vooga.engine.event.IEventHandler;
import vooga.games.towerdefense.actors.towers.Tower;



public class BuildTowerEvent implements IEventHandler{
	
	private PlayField playField;
	private Queue<Tower> buildQueue = new LinkedList<Tower>();
	
	public BuildTowerEvent(PlayField playField){
		this.playField = playField;
	}

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
		playField.add(tower);
	}
	

}

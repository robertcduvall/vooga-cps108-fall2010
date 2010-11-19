package vooga.games.towerdefense.events;

import java.util.LinkedList;
import java.util.Queue;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.core.PlayField;
import vooga.engine.event.IEventHandler;
import vooga.games.towerdefense.actors.towers.Tower;



public class RemoveTowerEvent implements IEventHandler{
	
	private PlayField playField;
	private Queue<Tower> removeQueue = new LinkedList<Tower>();
	
	public RemoveTowerEvent(PlayField playField){
		this.playField = playField;
	}

	@Override
	public void actionPerformed() {
		for(Tower tower: removeQueue){
			buildTower(tower);
		}
		
	}

	@Override
	public boolean isTriggered() {
		return !removeQueue.isEmpty();
	}

	public void removeTower(Tower tower){
		removeQueue.add(tower);
	}
	
	private void buildTower(Tower tower){
		SpriteGroup towers = playField.getGroup("tower");
		towers.remove(tower);
	}
	

}

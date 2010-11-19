package vooga.games.towerdefense.events;

import java.util.ArrayList;
import java.util.Collection;

import vooga.engine.core.PlayField;
import vooga.engine.event.IEventHandler;
import vooga.games.towerdefense.actors.towers.Tower;

/**
 * Event which tracks the building of new Towers. The player 
 * passed new towers to the buildTower() method. Every update 
 * loop whether or not Towers need to be built. If they are, 
 * new towers are placed on the playField.
 * 
 * @author Daniel Koverman
 *
 */
public class BuildTowerEvent implements IEventHandler{
	
	private PlayField playField;
	private Collection<Tower> buildQueue = new ArrayList<Tower>();
	
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

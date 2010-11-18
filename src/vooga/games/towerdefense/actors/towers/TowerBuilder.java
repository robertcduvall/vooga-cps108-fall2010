package vooga.games.towerdefense.actors.towers;

import vooga.engine.event.*;
import vooga.games.towerdefense.*;
import vooga.games.towerdefense.actors.Tower;
import vooga.games.towerdefense.events.*;

/**
 * This class is a listener to build a new tower when needed
 * 
 * @author Derek Zhou, Daniel Koverman, Justin Goldsmith
 *
 */

public class TowerBuilder implements IEventListener{
	
	private DropThis game;
	public final int TOWER_EDGE = 16;

	public void setGame(DropThis game){
		this.game = game;
	}
	
	@Override
	public void actionPerformed(IEvent event) {
		BuildTowerEvent buildEvent = (BuildTowerEvent) event;
		Tower tower = buildEvent.getTower();
		game.getTowerGroup().add(tower);		
	}

}

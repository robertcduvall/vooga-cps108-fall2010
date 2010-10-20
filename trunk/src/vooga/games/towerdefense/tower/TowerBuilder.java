package vooga.games.towerdefense.tower;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.event.IEvent;
import vooga.games.towerdefense.DropThis;
import vooga.games.towerdefense.events.*;

/**
 * This class is a listener to build a new tower when needed
 * 
 * @author Derek Zhou, Daniel Koverman, Justin Goldsmith
 *
 */

public class TowerBuilder implements BuildTowerListener{
	
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

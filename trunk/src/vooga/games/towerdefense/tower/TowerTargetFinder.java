package vooga.games.towerdefense.tower;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.event.IEvent;
import vooga.games.towerdefense.Enemy;
import vooga.games.towerdefense.DropThis;
import vooga.games.towerdefense.events.NeedsTargetsEvent;
import vooga.games.towerdefense.events.NeedsTargetsListener;

/**
 * This class works with the Event API to make each tower locate the enemy to fire upon.
 * 
 * @author Derek Zhou, Daniel Koverman, Justin Goldsmith
 *
 */

public class TowerTargetFinder implements NeedsTargetsListener{
	DropThis game;
	
	public void setGame(DropThis game){
		this.game = game;
	}
	
	@Override
	public void actionPerformed(IEvent event) {
		NeedsTargetsEvent needsTargetsEvent = (NeedsTargetsEvent) event;
		ShootingTower tower = needsTargetsEvent.getTower();
		tower.setTarget((Enemy) findTarget(tower));
		}
	
	private Sprite findTarget(ShootingTower tower){		
		SpriteGroup enemies = game.getEnemyGroup();
		for(Sprite sprite: enemies.getSprites()){
			if(tower.isValidTarget(sprite)){
				return sprite;
			}
		}
		return null;		
	}

}

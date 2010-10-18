package vooga.games.towerdefense.tower;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.event.IEvent;
import vooga.games.towerdefense.Enemy;
import vooga.games.towerdefense.TowerDefense;
import vooga.games.towerdefense.events.NeedsTargetsEvent;
import vooga.games.towerdefense.events.NeedsTargetsListener;

public class TowerTargetFinder implements NeedsTargetsListener{
	TowerDefense game;
	
	public void setGame(TowerDefense game){
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

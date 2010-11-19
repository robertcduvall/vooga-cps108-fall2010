package vooga.games.towerdefense.events;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.event.IEventHandler;
import vooga.games.towerdefense.DropThis;
import vooga.games.towerdefense.actors.towers.Enemy;
import vooga.games.towerdefense.actors.towers.IEvent;
import vooga.games.towerdefense.actors.towers.ShootingTower;

public class FindTargetEvent implements IEventHandler{

	DropThis game;
	NeedsTargetsEvent needsTargetsEvent;
	
	public void setGame(DropThis game, IEvent event){
		needsTargetsEvent = (NeedsTargetsEvent) event;
		this.game = game;
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

	@Override
	public boolean isTriggered() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void actionPerformed() {
		NeedsTargetsEvent needsTargetsEvent = (NeedsTargetsEvent) event;
		ShootingTower tower = needsTargetsEvent.getTower();
		tower.setTarget((Enemy) findTarget(tower));
	}
}

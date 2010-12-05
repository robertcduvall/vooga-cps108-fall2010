package vooga.games.towerdefense.events;

import java.util.ArrayList;
import java.util.Collection;

import vooga.engine.core.BetterSprite;
import vooga.engine.core.PlayField;
import vooga.engine.event.IEventHandler;
import vooga.games.towerdefense.Blah;
import vooga.games.towerdefense.actors.enemies.Enemy;
import vooga.games.towerdefense.actors.towers.ShootingTower;
import vooga.games.towerdefense.actors.towers.Tower;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class FindTargetEvent implements IEventHandler{

	private PlayField playField;
	private Collection<ShootingTower> targetQueue = new ArrayList<ShootingTower>();
	
	public FindTargetEvent(PlayField playField){
		this.playField = playField;
	}
	
	
	private Sprite findTarget(ShootingTower tower){		
		SpriteGroup enemies = playField.getGroup("enemyGroup");
		for(Sprite sprite: enemies.getSprites()){
			if(tower.isValidTarget(sprite)){
				return sprite;
			}
		}
		return null;		
	}

	public boolean isTriggered() {
		return !targetQueue.isEmpty();
	}

	@Override
	public void actionPerformed() {
		for(ShootingTower tower: targetQueue){
			if(!tower.checkTargetValid(tower.getTarget()))
			{
				tower.setTarget((Enemy) findTarget(tower));
			}
		}
	}
	
	public void addTower(ShootingTower tower){
		targetQueue.add(tower);
	}
}

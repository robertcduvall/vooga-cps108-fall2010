package vooga.games.towerdefense.events;

import java.util.LinkedList;
import java.util.Queue;

import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.games.towerdefense.actors.TowerShot;
import vooga.games.towerdefense.actors.enemies.Enemy;
import vooga.games.towerdefense.actors.towers.ShootingTower;

import com.golden.gamedev.object.SpriteGroup;

public class ShootEvent implements IEventHandler{
	private EnemyHitEvent enemyHit;
	private Queue<ShootingTower> shootQueue = new LinkedList<ShootingTower>();
	private SpriteGroup shotGroup;
	
	public void setHitEvent(EnemyHitEvent enemyHit, SpriteGroup shotGroup){
		this.enemyHit = enemyHit;
		this.shotGroup = shotGroup;
	}
	
	public boolean isTriggered() {
		return !shootQueue.isEmpty();
	}

	public void addTower(ShootingTower tower){
		shootQueue.add(tower);
	}	
	
	@Override
	public void actionPerformed() {
		for(ShootingTower tower: shootQueue){
			Enemy target = tower.getTarget();
			tower.resetShot();
			System.out.println(tower.getShotSpeed());
			TowerShot shot = new TowerShot(Resources.getImage("towerShot"),tower.getX(), tower.getY(), target.getX(), target.getY(), tower.getShotSpeed());
			shotGroup.add(shot);
		}
		shootQueue.clear();
	}
	
	
}

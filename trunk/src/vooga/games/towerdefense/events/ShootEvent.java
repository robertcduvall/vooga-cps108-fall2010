package vooga.games.towerdefense.events;

import java.util.LinkedList;
import java.util.Queue;

import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.games.towerdefense.DropThis;
import vooga.games.towerdefense.actors.Player;
import vooga.games.towerdefense.actors.TowerShot;
import vooga.games.towerdefense.actors.enemies.Enemy;
import vooga.games.towerdefense.actors.towers.ShootingTower;

public class ShootEvent implements IEventHandler{
	private EnemyHitEvent enemyHit;
	private Queue<ShootingTower> shootQueue = new LinkedList<ShootingTower>();
	
	public void setHitEvent(EnemyHitEvent enemyHit){
		this.enemyHit = enemyHit;
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
			enemyHit.addEnemy(target);
			tower.resetShot();
			//TowerShot shot = new TowerShot(Resources.getImage("towerShot"),getX(), getY(), target.getX(), target.getY(), shotSpeed);
		}
		shootQueue.clear();
	}
	
	
}

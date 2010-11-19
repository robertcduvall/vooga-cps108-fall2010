package vooga.games.towerdefense.events;

import java.util.LinkedList;
import java.util.Queue;

import vooga.engine.core.PlayField;
import vooga.engine.event.IEventHandler;
import vooga.engine.overlay.Stat;
import vooga.games.towerdefense.actors.enemies.Enemy;
import vooga.games.towerdefense.actors.towers.Tower;



public class EnemyFailEvent implements IEventHandler{

	private Queue<Enemy> failQueue = new LinkedList<Enemy>();
	private Stat<Integer> mySelfEsteem;
	
	public EnemyFailEvent(Stat<Integer> selfEsteem){
		mySelfEsteem = selfEsteem;
	}
	
	
	@Override
	public void actionPerformed() {
		for(Enemy enemy: failQueue){
			mySelfEsteem.setStat(mySelfEsteem.getStat() - enemy.getLives());
			enemy.setActive(false);
		}
	}

	@Override
	public boolean isTriggered() {
		return !failQueue.isEmpty();
	}

	public void addEnemy(Enemy enemy){
		failQueue.add(enemy);
	}	

}
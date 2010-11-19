package vooga.games.towerdefense.events;

import java.util.LinkedList;
import java.util.Queue;

import vooga.engine.core.PlayField;
import vooga.engine.event.IEventHandler;
import vooga.engine.overlay.Stat;
import vooga.games.towerdefense.actors.enemies.Enemy;
import vooga.games.towerdefense.actors.towers.Tower;



public class EnemyHitEvent implements IEventHandler{

	private Queue<Enemy> hitQueue = new LinkedList<Enemy>();
	private Stat<Integer> myScore;
	private Stat<Integer> myMoney;
	
	public EnemyHitEvent(Stat<Integer> score, Stat<Integer> money){
		myScore = score;
		myMoney = money;
	}
	
	
	@Override
	public void actionPerformed() {
		for(Enemy enemy: hitQueue){
			myScore.setStat(myScore.getStat() + 10);
			myMoney.setStat(myMoney.getStat() + 1);
			enemy.gotHit();
		}
	}

	@Override
	public boolean isTriggered() {
		return !hitQueue.isEmpty();
	}

	public void addEnemy(Enemy enemy){
		hitQueue.add(enemy);
	}	

}

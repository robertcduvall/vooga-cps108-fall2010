package vooga.games.towerdefense.events;

import java.util.LinkedList;
import java.util.Queue;

import vooga.engine.event.IEventHandler;
import vooga.games.towerdefense.actors.Player;
import vooga.games.towerdefense.actors.enemies.Enemy;



public class EnemyHitEvent implements IEventHandler{

	private static final int SCORE_ADD = 10;
	private static final int MONEY_ADD = 1;
	private Queue<Enemy> hitQueue = new LinkedList<Enemy>();
	private Player myPlayer;
	
	public EnemyHitEvent(Player player){
		myPlayer = player;
	}
	
	
	@Override
	public void actionPerformed() {
		for(Enemy enemy: hitQueue){
			myPlayer.addScore(SCORE_ADD);
			myPlayer.addMoney(MONEY_ADD);
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

package vooga.games.towerdefense.events;

import java.util.LinkedList;
import java.util.Queue;

import vooga.engine.event.IEventHandler;
import vooga.games.towerdefense.actors.Player;
import vooga.games.towerdefense.actors.enemies.Enemy;



public class EnemyFailEvent implements IEventHandler{

	private Queue<Enemy> failQueue = new LinkedList<Enemy>();
	private Player myPlayer;
	
	public EnemyFailEvent(Player player){
		myPlayer = player;
	}
	
	
	@Override
	public void actionPerformed() {
		for(Enemy enemy: failQueue){
			myPlayer.removeSelfEsteem(enemy.getLives());
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
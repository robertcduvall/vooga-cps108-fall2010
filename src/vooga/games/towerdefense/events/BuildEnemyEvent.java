package vooga.games.towerdefense.events;

import java.util.LinkedList;
import java.util.Queue;

import vooga.engine.core.PlayField;
import vooga.engine.event.IEventHandler;
import vooga.games.towerdefense.actors.enemies.Enemy;

public class BuildEnemyEvent implements IEventHandler{
		
		private Queue<Enemy> enemyQueue = new LinkedList<Enemy>();
		private PlayField playField;
		
		public BuildEnemyEvent(PlayField playField){
			this.playField = playField;
		}
		

		@Override
		public void actionPerformed() {
			for(Enemy enemy: enemyQueue){
				buildEnemy(enemy);
			}
			enemyQueue.clear();
			
		}

		@Override
		public boolean isTriggered() {
			return !enemyQueue.isEmpty();
		}
		
		private void buildEnemy(Enemy enemy){
			playField.add(enemy);
		}
		
		public void addEnemy(Enemy enemy){
			enemyQueue.add(enemy);
		}
		
	
}

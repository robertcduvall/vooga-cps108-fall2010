package vooga.games.towerdefense.events;

import java.util.LinkedList;
import java.util.Queue;

import vooga.engine.event.IEventHandler;
import vooga.games.towerdefense.actors.enemies.Enemy;

import com.golden.gamedev.object.SpriteGroup;

public class BuildEnemyEvent implements IEventHandler{
		
		private Queue<Enemy> enemyQueue = new LinkedList<Enemy>();
		private SpriteGroup enemyGroup;
		
		public BuildEnemyEvent(SpriteGroup enemyGroup){
			this.enemyGroup = enemyGroup;
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
			enemyGroup.add(enemy);
		}
		
		public void addEnemy(Enemy enemy){
			enemyQueue.add(enemy);
		}
		
	
}

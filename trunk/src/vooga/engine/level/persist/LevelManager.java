package vooga.engine.level.persist;

import java.util.*;

import vooga.engine.collision.CollisionManager;
import vooga.engine.event.EventManager;
import vooga.engine.player.control.GameEntitySprite;
import vooga.engine.resource.ResourceHandler;
import vooga.engine.state.GameState;

/**
 * We changed our design when porting towards the golden T engine. Originally the focus was on the portability of the
 * engine. Now that we have decided on one engine, there is not much need to still keep the original interface. Hence
 * Level Manager is simply a class this time. 
 * 
 * @author Jiaqi Yan
 * Date: 03/10/2010
 *
 */

public class LevelManager{
		private static ArrayList<Level> levels;
		private static Collection<GameEntitySprite> persistentGameEntities = null;
		private static CollisionManager persistentCollisionManager = null;
		private static EventManager persistentEventManager = null;
		private static GameState persistentGameState = null;
		private static ResourceHandler persistentResourceHandler = null;
		
		
		private static int currentLevel = -1;
		private Level current = null;
		
		
		/**
		 * This method takes a level as an argument and appends it to the ArrayList levels.
		 * @param level
		 */
		public void addLevel(Level level) {
			levels.add(level);
		}
		/**
		 * This method ends the currently active level.
		 */
		public void finishCurrentLevel() {
			current.finish();
		}
		/**
		 * This method appends a level to the back of the list and start that level
		 * @param level
		 */
		public void startNewLevel(Level level) {
			finishCurrentLevel();
			addLevel(level);
			level.start();
			currentLevel = levels.size()-1;
			current = level;
		}
		/**
		 * This method ends the current level and sets the level in the given index of ArrayList
		 * levels to active.
		 * @param i
		 */
		public void gotoLevelIndex(int i) {
			finishCurrentLevel();
			current = levels.get(i);
			startNewLevel(current);
			currentLevel = i;
		}
		/**
		 * Similar to addLevel, this method takes a level and adds it to the given index
		 * of the ArrayList levels.
		 * @param index
		 * @param level
		 */
		public void insertLevel(int index, Level level) {
			levels.add(index, level);
		}

		/**
		 * This method ends the current level and sets the next level in the ArrayList levels
		 * to active.
		 */
		public void gotoNextLevel() {
			finishCurrentLevel();
			currentLevel++;
			startNewLevel(levels.get(currentLevel));
		}

		/**
		 * This method ends the current level and sets a level at a random index of ArrayList
		 * levels to active.
		 */
		public void gotoRandomLevel() {
			finishCurrentLevel();
			currentLevel = (int) Math.random()*levels.size();
			current = levels.get(currentLevel);
			startNewLevel(current);
		}

		/**
		 * This method ends the current level and restarts it.
		 */
		public void startLevelOver() {
			finishCurrentLevel();
			startNewLevel(levels.get(currentLevel));
		}
		
		public void setPersistentGameEntities(Collection<GameEntitySprite> ges){
			persistentGameEntities = ges;
		}
		public void setPersistentCollisionManager(CollisionManager cm){
			persistentCollisionManager = cm;
		}
		public void setPersistentEventManager(EventManager em){
			persistentEventManager = em;
		}
		public void setPersistentGameState(GameState gs){
			persistentGameState = gs;
		}
		public void setPersistentResourceHandler(ResourceHandler rh){
			persistentResourceHandler = rh;
		}		
		public void clearPersistentGameEntities(){
			persistentGameEntities.clear();
		}
		
		public void persistCollisionManager(Level l){			
			l.setCollision(persistentCollisionManager);
		}
		public void persistEventManager(Level l){
			l.setEvents(persistentEventManager);
		}
		public void persistGameState(Level l){
			l.setGameState(persistentGameState);
		}
		public void persistentResourceHandler(Level l){
			l.setResources(persistentResourceHandler);
		}
		public void persistentGameEntities(Level l){
			l.setEntities(persistentGameEntities);
		}
		public void initAllPersistentStates(Level l){
			l.setCollision(persistentCollisionManager);
			l.setEvents(persistentEventManager);
			l.setGameState(persistentGameState);
			l.setResources(persistentResourceHandler);
			l.setEntities(persistentGameEntities);
		}
		
}

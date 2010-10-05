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
 * Level Manager is a class this time. The LevelManager handles the flow of the game levels as well as the persistent of 
 * objects between the levels. 
 * 
 * @author Jiaqi Yan
 * Date: 03/10/2010
 *
 */

public class LevelManager{
		private static ArrayList<Level> levels = new ArrayList<Level>();
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
			currentLevel = i;
			current = levels.get(i);
			startNewLevel(current);
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
		/**
		 * Set the persist Game Entity objects. Called by a level when it exits.  
		 * @param ges
		 */
		public void setPersistentGameEntities(Collection<GameEntitySprite> ges){
			persistentGameEntities = ges;
		}
		/**
		 * Set the persist Collision Manager. Called by a level when it exits.
		 * @param cm
		 */
		public void setPersistentCollisionManager(CollisionManager cm){
			persistentCollisionManager = cm;
		}
		/**
		 * Set the persist Event Manager. Called by a level when it exits.
		 * @param em
		 */
		public void setPersistentEventManager(EventManager em){
			persistentEventManager = em;
		}
		/**
		 * Set the persist Game State object. Called by a level when it exits.
		 * @param gs
		 */
		public void setPersistentGameState(GameState gs){
			persistentGameState = gs;
		}
		/**
		 * Set the persist Resource Handler. Called by a level when it exits.
		 * @param rh
		 */
		public void setPersistentResourceHandler(ResourceHandler rh){
			persistentResourceHandler = rh;
		}		
		/**
		 * Clear the persistent Game Entities
		 */
		public void clearPersistentGameEntities(){
			persistentGameEntities.clear();
		}
		/**
		 * Assign the persistent Collision Manager to the level l. 
		 * @param l
		 */
		public void persistCollisionManager(Level l){			
			l.setCollision(persistentCollisionManager);
		}
		/**
		 * Assign the persistent Event Manager to the level l.
		 * @param l
		 */
		public void persistEventManager(Level l){
			l.setEvents(persistentEventManager);
		}
		/**
		 * Assign the persistent Game State object to the level l.
		 * @param l
		 */
		public void persistGameState(Level l){
			l.setGameState(persistentGameState);
		}
		/**
		 * Assign the persistent Resource Handler to the level l.
		 * @param l
		 */
		public void persistentResourceHandler(Level l){
			l.setResources(persistentResourceHandler);
		}
		/**
		 * Assign the persistent Game Entities to the level l.
		 * @param l
		 */
		public void persistentGameEntities(Level l){
			l.setEntities(persistentGameEntities);
		}
		/**
		 * Clear all persistent Parameters.
		 */
		public void clearAllPersistentStates(){
			persistentGameEntities = null;
			persistentCollisionManager = null;
		    persistentEventManager = null;
		    persistentGameState = null;
			persistentResourceHandler = null;
			
		}
		/**
		 * Assign all the persistent parameters to level l. 
		 * @param l
		 */
		public void initAllPersistentStates(Level l){
			l.setCollision(persistentCollisionManager);
			l.setEvents(persistentEventManager);
			l.setGameState(persistentGameState);
			l.setResources(persistentResourceHandler);
			l.setEntities(persistentGameEntities);
		}
}

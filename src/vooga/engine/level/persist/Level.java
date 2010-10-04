package vooga.engine.level.persist;

import vooga.engine.player.control.GameEntitySprite;
import vooga.engine.resource.ResourceHandler;
import vooga.engine.state.GameState;
import vooga.engine.collision.CollisionManager;
import vooga.engine.core.Game;
import vooga.engine.event.EventManager;

import java.util.*;



/**
 * This class is an integration of our original level class with the rest of the API. It is also 
 * a port to golden T.  
 * The constructor contains the locations of Sprites and images, etc.
 * @author Jiaqi Yan 
 *
 */

public class Level extends Game{
		private Collection<GameEntitySprite> gameEntities;
		
		
		private CollisionManager collisionManager;
		private EventManager eventManager;
		private GameState gameState;
		private ResourceHandler resourceHandler;

		public Collection<GameEntitySprite> getEntities(){
			return gameEntities;
		}
		public GameState getLevelState(){
			return gameState;
		}
		public EventManager getEvents(){
			return eventManager;
		}
		public CollisionManager getCollisionManager(){
			return collisionManager;
		}
		public ResourceHandler getResourceHandler(){
			return resourceHandler;
		}
			
		/**
		 * set the game entities
		 */
		public void setEntities(){
		}
		/**
		 * set the game entity sprites to ges
		 * @param ges
		 */
		public void setEntities(Collection<GameEntitySprite> ges){
			gameEntities = ges;
		}
		
		/**
		 * Initialize the collision manager
		 */
		public void setCollision(){
		}
		/**
		 * Initialize the collision manager to cm;
		 * @param cm
		 */
		public void setCollision(CollisionManager cm){
			collisionManager = cm;
		}
		/**
		 * Initialize the gameState objects 
		 */
		public void setGameState(){
					
		}
		/**
		 * Initialize the Game State to gs
		 * @param gs
		 */
		public void setGameState(GameState gs){
			gameState = gs;
		}
		/**
		 * Initialize the event manager
		 */
		public void setEvents(){
		}
		/**
		 * Initialize the Event Manager to em
		 * @param em
		 */
		public void setEvents(EventManager em){
			eventManager = em;
		}
		/**
		 *	Initialize the resources for the game 
		 */
		public void setResources(){
		}
		/**
		 * Initialize the Resource Handler to rh
		 * @param rh
		 */
		public void setResources(ResourceHandler rh){
			resourceHandler = rh;
		}		
		
		
		
		
		

		public void initializeLevel(){
			
		}
	
		public void startLevel(){
			
		}
		
		public void addGameEntity(GameEntitySprite ges){
			gameEntities.add(ges);
		}

}




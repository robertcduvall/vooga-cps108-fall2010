package vooga.engine.level.persist;

/**
 * Author: Jiaqi Yan, Scott Winkleman, Devon Townsend
 * Date: 09/26/2010
 * Our revised API focuses on improving the portability of the design so that it can be more easily applied to other engines. 
 * This is achieved by using the Manager interface which has the core methods for the API. The Level class is simplified to be a 
 * container of other objects of other API's such as Game State objects, Player/Item objects and resources.
 * Our improved game reflected this design. The bouncingBall class implements the FANGLevelManager and the Levels class implements
 * the Level class. In general, our design is simple and robust enough so that we think this can be smoothly merged with other systems
 * in general.    
 */

public interface Manager {
	/**
	 * wrap up the previous level, possibly storing persistent states/objects
	 */
	public void finishPreviousLevel();

	/**
	 * immediately start the specified level. This level should have already
	 * been in the collection of levels.
	 * 
	 * @param level
	 */
	public void startNewLevel(Level level);

	/**
	 * initialize the persistent resources to the current level
	 */
	public void initializePersistResources();

	/**
	 * persist the resources of the current level to future levels
	 * 
	 * @param resource
	 */
	public void persistResources(Class<?> resource);

	/**
	 * Start the current level over, called when the level fails
	 */
	public void startLevelOver();

	/**
	 * begin the specified new level, which has not yet been added to the
	 * collection of levels
	 * 
	 * @param level
	 */
	public void beginNewLevel(Level level);

	/**
	 * Go to a random level in the current collection of levels
	 */
	public void gotoRandomLevel();

	/**
	 * go to the next level in order
	 */
	public void gotoNextLevel();

	/**
	 * add a new level to the current collection of levels
	 * 
	 * @param level
	 */
	public void addLevel(Level level);

	/**
	 * go to the indexed level in the current collection of levels
	 * 
	 * @param i
	 */
	public void gotoLevelIndex(int i);

	/**
	 * insert the level in the specified position
	 * 
	 * @param index
	 * @param level
	 */
	public void insertLevel(int index, Level level);

}

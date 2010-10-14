package vooga.engine.level;

import java.io.*;
import java.util.*;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.AnimatedSprite;

import vooga.engine.collision.CollisionManager;
import vooga.engine.event.EventManager;
import vooga.engine.resource.ResourceHandler;
import vooga.engine.state.GameState;
import vooga.games.grandius.GrandiusLevel;

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

/**
 *@author Bhawana, Cameron, Derek
 * <br>LevelManager class represents a Collection of Level objects.
 * LevelManager is responsible for passing the main Game engine whichever Level 
 * that is requested to be loaded. It is also responsible for adding and removing 
 * Levels to the game.
 * <br>
 * <br>Example: The following piece of code would add all the levels and then
 * change the level if a condition is satisfied
 * <pre>
 * {@code 
 * LevelManager levelList = new LevelManager();
 * levelList.addLevels();
 * 
 * if(levelWon())
 * {
 * Collection<Sprite> spritesList = levelList.nextLevel();
 * }
 * }
 * </pre>
 * 
 */

public class LevelManager {
	private List<Level> myLayout;
	private int myCurrentLevel;
	private static CollisionManager persistentCollisionManager = null;
	private static EventManager persistentEventManager = null;
	private static GameState persistentGameState = null;
	private static ResourceHandler persistentResourceHandler = null;

	public LevelManager() {
		myLayout = new ArrayList<Level>();
		myCurrentLevel = 1;
	}
	
	/**
	 * This method fills a Collection of Levels by scanning through a folder
	 * containing .txt files. The user must start all files with "level" and
	 * end all files with ".txt".
	 * @throws FileNotFoundException
	 */
	public void addLevels(String path) throws FileNotFoundException {
		//String path = ".";
		String files;
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				files = listOfFiles[i].getName();
				if (files.startsWith("level") && 
						(files.endsWith(".txt") || files.endsWith(".TXT"))) {
					myLayout.add(new Level(path+"/"+files));
					System.out.println("files="+files);
				}
			}
		}
	}
	
	public int getMyCurrentLevel() {
		return myCurrentLevel;
	}

	/**
	 * Used to remove a Level from the Collection.
	 * @param levelIndex - Indicates which Level should be removed.
	 */
	public void removeLevel(int levelIndex) {
		if (levelIndex < myCurrentLevel) 
			myCurrentLevel--;
		myLayout.remove(levelIndex);
	}
	
	/**
	 * Used to skip to any Level within the current Collection of Levels.
	 * @param levelIndex - indicates the Level to jump to.
	 * @return the Collection of Sprites that constitutes the selected Level.
	 */

	public ArrayList<ArrayList<Sprite>> skipToLevel(int levelIndex) {
		myCurrentLevel = levelIndex; 
		return myLayout.get(myCurrentLevel-1).getSpritesList();
	}
	
	/**
	 * 
	 * @return The Collection of Sprites that constitutes the next Level in the folder.
	 */
	
	public ArrayList<ArrayList<Sprite>> nextLevel() {
		myCurrentLevel+=1;
		return currentLevel();
	}
	
	public ArrayList<ArrayList<Sprite>> currentLevel() {
		ArrayList<ArrayList<Sprite>> returnCollection = new ArrayList<ArrayList<Sprite>>();
		//array index compensation of -1 (if myCurrentLevel = 1, we want the 0th entry of the list)
			returnCollection = myLayout.get(myCurrentLevel-1).getSpritesList();
		return returnCollection;
	}
	
	public Level getCurrentLevel() {
		return myLayout.get(myCurrentLevel-1);
	}
	
	public List<Level> getLayout() {
		return myLayout;
	}
	
	public void setMyCurrentLevel(int newMyCurrentLevel) {
		myCurrentLevel = newMyCurrentLevel;
	}
	
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
}

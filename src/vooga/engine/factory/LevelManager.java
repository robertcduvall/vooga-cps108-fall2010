package vooga.engine.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import com.golden.gamedev.object.PlayField;

/**
 * This is an abstract class and needs to be extended by a class which provides
 * specific implementation for the getCurrentPlayField method.This class would 
 * take care of different tasks like reading level names from a file, making a 
 * collection of these level files and loading next level etc.
 * An example of how this could be used in a game is as follows:
 * <pre>
 * <code>
 * 	private LevelManagerExample levelManager = new LevelManagerExample();
	private PlayField playfield;
	
	public void initResources() { 
		String levelFilesDirectory = resources.getString(levelFilesDirectory);
		String levelNamesFile = resources.getString(levelNamesFile);
		levelManager.makeLevels(levelFilesDirectory,levelNamesFile);
	}
		
	public void update(long elapsedTime){
		if(levelManager.getCurrentLevel()==0){
			playfield=levelManager.loadNextLevel();
		}
		
		playfield.update(elapsedTime);
		
		if(levelcomplete condition){
			playfield.clearPlayField();
			playfield=levelManager.loadNextLevel();
		}
	}	
 * </code>
 * </pre>
 * 
 * An example for the implementation of the getCurrentPlayField is as follows:
 * <pre>
 * <code>
 * 	public PlayField getCurrentPlayField(File currentLevelFactoryFile) {
		return levelFactory.getPlayfield(currentLevelFactoryFile);
 	}
 * </code>
 * </pre>
 * @author Bhawana, Cameron, Derek
 *
 */
public abstract class LevelManager {
	private List<String> myLevelNames;
	private int myCurrentLevel;
	private static final String COMMENT = "#";
	private static final String EMPTY_STRING = "";


	public LevelManager(){
		myCurrentLevel = -1;
		myLevelNames = new ArrayList<String>();
	}
	
	
	/**
	 * This method reads in from a File containing the names of different levels
	 * and returns a Collection of strings, myLevelNames, which represents the names 
	 * of the different level files in the order in which they need to be loaded.
	 * @param levelPath - directory path where all level files are stored
	 * @param levelNamesFile - name of the File which specifies the names of the 
	 * levels in the order in which they are to be loaded
	 * @return a Collection of strings representing level files names
	 */
	public Collection<String> makeLevels(String levelPath,String levelNamesFile){
		try {
			Scanner scanner = new Scanner(new File(levelPath+"/"+levelNamesFile));
			while(scanner.hasNextLine()){
				String levelName = scanner.nextLine();
				if (levelName.equals(EMPTY_STRING) || levelName.startsWith(COMMENT)){
					continue;
				}
				else{
					myLevelNames.add(levelPath+"/"+levelName);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("LevelNames File not found!");
		}
		return myLevelNames;
	}
	
	
	/**
	 * Gets the playfield for the current level. This can be done by 
	 * making a call to LevelFactory's getPlayField method.
	 * @param currentLevelFactoryFile
	 * @return Playfield for the current level
	 */
	public abstract PlayField getCurrentPlayField(File currentLevelFactoryFile);
	
	
	/**
	 * Loads the next level. Playfield should be cleared before making a call
	 * to this method, so as to remove the game objects from previous level.
	 */
	public PlayField loadNextLevel() {
		myCurrentLevel+=1;
		return getCurrentPlayField(new File(myLevelNames.get(myCurrentLevel)));
	}
	
	
	/**
	 * Skips to and loads the specified level.
	 * @param levelIndex
	 * @return Playfield for the specified level
	 */
	public PlayField skipToLevel(int levelIndex) {
		myCurrentLevel = levelIndex; 
		return getCurrentPlayField(new File(myLevelNames.get(levelIndex)));
	}
	
	
	/**
	 * Returns an integer representing the curremt level number.
	 * @return myCurrentLevel - current level number
	 */
	public int getCurrentLevel() {
		return myCurrentLevel;
	}
	
	
	/**
	 * Returns the name of the current level.
	 * @return Name of the level file for the current level
	 */
	public String getCurrentLevelName(){
		return myLevelNames.get(myCurrentLevel);
	}
	
}

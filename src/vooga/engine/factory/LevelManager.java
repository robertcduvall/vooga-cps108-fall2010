package vooga.engine.factory;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.golden.gamedev.object.PlayField;

/**
 * This is an abstract class and needs to be extended by a class which provides
 * specific implementation for the getLevelNames and getCurrentPlayField methods.
 * This class would take care of different tasks like reading level names from
 * a file, making a collection of these level files and loading next level etc.
 * @author bhawana
 *
 */
public abstract class LevelManager {
	//TODO - Should this be made private?
	protected List<String> myLevelNames;
	private List<File> myLevelFactoryFiles;
	private int myCurrentLevel;


	public LevelManager(){
		myCurrentLevel = 0;
		myLevelFactoryFiles = new ArrayList<File>();
		myLevelNames = new ArrayList<String>();
	}
	
	
	/**
	 * This method reads in from a File containing the names of different levels
	 * and returns a Collection of strings, myLevelNames, which represent the names 
	 * of the different level files in the order in which they need to be loaded,
	 * eg. {easyLevel.txt,shoppingLevel.txt}
	 * @param levelNamesFile - file containing the names of the different levels
	 * @return myLevelnames - a Collection of strings representing level files names
	 */
	public abstract Collection<String> getLevelNames(File levelNamesFile);
	
	
	/**
	 * This method gets the names of the different levels for this game from myLevelNames
	 * and adds the corresponding level files to a Collection of Files, which can be 
	 * used later for loading the next level or for skipping to a particular level.
	 * @param path - directory path where the levelNamesFile and level files are stored
	 * @param levelNamesFile - file containing the list of levels for this game
	 */
	public void addLevels(String path, File levelNamesFile){
		for (String levelName : getLevelNames(new File(path+"/"+levelNamesFile))){
			myLevelFactoryFiles.add(new File(path+"/"+levelName));
			System.out.println("added level "+levelName);
		}
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
		return getCurrentPlayField(myLevelFactoryFiles.get(myCurrentLevel-1));
	}
	
	
	/**
	 * Skips to and loads the specified level.
	 * @param levelIndex
	 * @return Playfield for the specified level
	 */
	public PlayField skipToLevel(int levelIndex) {
		myCurrentLevel = levelIndex; 
		return getCurrentPlayField(myLevelFactoryFiles.get(myCurrentLevel-1));
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
		return myLevelNames.get(myCurrentLevel-1);
	}
	
}

package vooga.engine.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;

/**
 * This class takes care of different tasks like reading level names from a file, 
 * making a collection of these level files and loading next level etc. It loads
 * the levels when they are needed. However, if all the levels need to be loaded
 * at the beginning of the game , then a call to <code> getAllPlayFields()</code>
 * method would return all the level Playfields at once, in a list according to the 
 * order in which they need to be loaded.
 * 
 * An example of how this could be used in a game is as follows:
 * <pre>
 * <code>
	public void initResources() { 
		LevelManager levelManager = new LevelManager(this);
		String levelFilesDirectory = Resources.getString("levelFilesDirectory");
		String levelNamesFile = Resources.getString("levelNamesFile");
		levelManager.makeLevels(levelFilesDirectory,levelNamesFile);
	}
 * </code>
 * </pre>
 * To load levels one by one, i.e., as they are needed, the following code can be used:
 * <pre>
 * <code>	
	public void update(long elapsedTime){	
		if(levelcomplete condition){
			playfield.clearPlayField();
			playfield=levelManager.loadNextLevel();
		}
	}	
 * </code>
 * </pre>
 * 
 * To get all the playfields at the beginning of the game, this method call would be useful:
 * <pre>
 * <code>
 * 	levelManager.getAllPlayFields();
 * </code>
 * </pre>
 * @author Bhawana
 *
 */
public class LevelManager {
	private List<String> myLevelNames;
	private List<PlayField> myPlayFields;
	private int myCurrentLevel;
	private static final String COMMENT = "#";
	private static final String EMPTY_STRING = "";
	private Game game;


	public LevelManager(Game currentgame){
		game = currentgame;
		myCurrentLevel = -1;
		myLevelNames = new ArrayList<String>();
		myPlayFields = new ArrayList<PlayField>();
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
	 * @param currentLevelFileName
	 * @return Playfield for the current level
	 */
	public PlayField getCurrentPlayField(String filepath){
		return game.getLevelParser().getPlayfield(filepath,game);
	}
	
	
	/**
	 * This method is used to get a list of all the playfields for the current game. 
	 * Every playfield is associated with one level. The returned list contains the
	 * playfields in the order in which they are to be loaded. A call to this method 
	 * would be useful in cases where all the playfields / levels need to be loaded 
	 * at the beginning of the game.
	 * @return collection of Playfields listed in the order in which they are to be loaded
	 */
	public Collection<PlayField> getAllPlayFields(){
		for(String levelFile : myLevelNames){
			myPlayFields.add(game.getLevelParser().getPlayfield(levelFile,game));
		}
		return myPlayFields;
	}
	
	
	/**
	 * Loads the next level. Playfield should be cleared before making a call
	 * to this method, so as to remove the game objects from previous level.
	 */
	public PlayField loadNextLevel() {
		myCurrentLevel+=1;
		return getCurrentPlayField(myLevelNames.get(myCurrentLevel));
	}
	
	
	/**
	 * Skips to and loads the specified level.
	 * @param levelIndex
	 * @return Playfield for the specified level
	 */
	public PlayField skipToLevel(int levelIndex) {
		myCurrentLevel = levelIndex; 
		return getCurrentPlayField(myLevelNames.get(myCurrentLevel));
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

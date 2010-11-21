package vooga.engine.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.resource.Resources;
import vooga.games.asteroids.states.PlayState;

/**
 * This class takes care of different tasks like reading level names from a file, 
 * making a Map of these level files and loading next level etc. It loads
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
		PlayField levelPlayField = levelManager.loadFirstLevel();
		playState = new PlayState(this, levelPlayField);
	}
 * </code>
 * </pre>
 * The String "levelFilesDirectory" in resources.xml file should be mapped to something 
 * like "src/vooga/games/asteroids/resources/levels" where asteroids would be replaced by 
 * your game's name,  and here it is assumed that your levelNames file as well as all your 
 * level xml files are stored in a package called levels in your resources package.  
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
	private Map<String,String> myLevelMap;
	private List<PlayField> myPlayFields;
	private int myCurrentLevel;
	private static final String COMMENT = "#";
	private static final String EMPTY_STRING = "";
	private Game game;
	private LevelParser myParser;


	public LevelManager(Game currentgame){
		this(currentgame, new LevelParser());
	}
	
	
	public LevelManager(Game currentgame, LevelParser parser){
		game = currentgame;
		myParser = parser;
		myCurrentLevel = 0;
		myLevelMap = new HashMap<String,String>();
		myPlayFields = new ArrayList<PlayField>();
	}

	
	/**
	 * Returns the PlayField for the first level.
	 * @return PlayField for the firt level
	 */
	public PlayField loadFirstLevel ()
	{
		myCurrentLevel++;
		System.out.println(myLevelMap.get("Level1"));
		return myParser.getPlayfield(myLevelMap.get("Level1"),game);
	}
	
	/**
	 * This method reads in from a File containing the names of different levels
	 * and returns a Map where level numbers are mapped to the names 
	 * of the different level xml files.
	 * @param levelPath - directory path where all level files are stored
	 * @param levelNamesFile - name of the File which specifies the names of the 
	 * levels in the order in which they are to be loaded
	 * @return a Map of level numbers to level files names
	 */
	
	public Map<String,String> makeLevels(String levelPath,String levelNamesFile){
		try {
			int levelIndex = 0;
			Scanner scanner = new Scanner(new File(levelPath+"/"+levelNamesFile));
			while(scanner.hasNextLine()){
				String levelName = scanner.nextLine();
				if (levelName.equals(EMPTY_STRING) || levelName.startsWith(COMMENT)){
					continue;
				}
				else{
					levelIndex++;
					myLevelMap.put("Level"+levelIndex, levelPath+"/"+levelName);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("LevelNames File not found!");
		}
		return myLevelMap;
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
		for(int i=1 ; i<=myLevelMap.size() ; i++){
			myPlayFields.add(myParser.getPlayfield(myLevelMap.get("Level"+i),game));
		}
		return myPlayFields;
	}
	
	
	/**
	 * Returns the PlayField for the next level to be loaded.
	 * @return PlayField for next level 
	 */
	public PlayField loadNextLevel() {
		myCurrentLevel++;
		System.out.println(myLevelMap.get("Level"+myCurrentLevel));
		return myParser.getPlayfield(myLevelMap.get("Level"+myCurrentLevel),game);
	}
	
	
	/**
	 * Skips to and loads the specified level.
	 * @param levelIndex
	 * @return Playfield for the specified level
	 */
	public PlayField skipToLevel(int levelIndex) {
		myCurrentLevel = levelIndex - 1; 
		return loadNextLevel();
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
		return myLevelMap.get("Level" + myCurrentLevel);
	}
	
}

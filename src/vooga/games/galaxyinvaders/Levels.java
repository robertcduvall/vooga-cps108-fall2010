package vooga.games.galaxyinvaders;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The Levels class reads in levels from files. The different levels in this game consist of different
 * motion of the enemies, whose paths are set by the level files.
 * 
 * NOTE: There is a known issue with the createLevels() method not finding local addresses
 * (i.e. src/images/level1.txt) It requires a full address, which right now is set to /Users/nickhawthorne/Documents/Eclipse Workspace/
 * 
 * To make the game run, you have to change that method to read the files on your computer
 * 
 *
 * @author Drew Sternesky, Kate Yang, Nick Hawthorne
 *
 */

public class Levels {

	private static final int LEVELS = 3;

	private ArrayList<File> files;
	private Map<Integer, ArrayList<Point>> pathmap;
	private int[] timers;
	private int levelNum;
	private int[] enemies;


	/**
	 * Constructor for the Levels class. Initializes the filelist and the map for the path
	 */
	public Levels(){
		files = new ArrayList<File>();
		pathmap = new HashMap<Integer, ArrayList<Point>>();
		timers = new int[LEVELS];
		levelNum = 1;
		enemies = new int[LEVELS];
	}

	// for some reason, just using img/level1.txt doesn't work, it requires a full address. To make the game run, just 
	// replace /Users/nickhawthorne/Documents/Eclipse Workspace/  with your personal computers path to the vooga project
	/**
	 * This method creates the levels from text files. It is not working properly with local addresses as explained
	 * above. It must be changed before the game will run
	 * 
	 * @throws FileNotFoundException if file is not found
	 */
	public void createLevels() throws FileNotFoundException{
		files.add(new File("C:/Users/dhs9/workspace cs108/vooga/src/vooga/games/galaxyinvaders/img/level1.txt"));
		files.add(new File("C:/Users/dhs9/workspace cs108/vooga/src/vooga/games/galaxyinvaders/img/level2.txt"));
		files.add(new File("C:/Users/dhs9/workspace cs108/vooga/src/vooga/games/galaxyinvaders/img/level3.txt"));
		makeMap();
	}

	public void makeMap() throws FileNotFoundException{
		for (File file: files){
			Scanner scanner = new Scanner(file);
			int levelNum = scanner.nextInt();
			pathmap.put(levelNum, new ArrayList<Point>());
			int timerNum = scanner.nextInt();
			timers[levelNum-1] = timerNum;
			int enemyNum = scanner.nextInt();
			enemies[levelNum-1] = enemyNum;
			scanner.nextLine();
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				Scanner sc = new Scanner(line);
				int temp1 = sc.nextInt();
				int temp2 = sc.nextInt();
				pathmap.get(levelNum).add(new Point(temp1, temp2));
			}
		}   
	}

	/**
	 * Advances the game to the next level
	 */
	public void nextLevel(){
		levelNum++;
	}

    /**
     * Returns the current level number
     * 
     * @return the current level number
     */
	public int getLevel() {
		return levelNum;
	}

	/**
	 * This method returns the level path an enemy should be following for this level
	 * 
	 * @return the path associated with the current level
	 */
	public ArrayList<Point> getLevelPath(){
		return pathmap.get(levelNum);
	}

	public int getTimer(){
		return timers[levelNum-1];
	}

	public int getEnemySize(){
		return enemies[levelNum-1];
	}
}
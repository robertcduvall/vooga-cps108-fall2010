package engine.level.reader;

import java.io.*;
import java.util.*;
import engine.core.Sprite;

/**
 *@author Bhawana, Cameron, Derek
 * LevelManager class represents a Collection of Level objects.
 * LevelManager is responsible for passing the main Game engine whichever Level 
 * that is requested to be loaded.
 */

public class LevelManager {
	private List<Level> myLayout;
	private int myCurrentLevel;

	public LevelManager() {
		myLayout = new ArrayList<Level>();
		myCurrentLevel = 0;
	}
	
	/**
	 * This method fills a Collection of Levels by scanning through a folder
	 * containing .txt files. The user must start all files with "level" and
	 * end all files with ".txt".
	 * @throws FileNotFoundException
	 */

	public void addLevels() throws FileNotFoundException {
		String path = ".";
		String files;
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				files = listOfFiles[i].getName();
				if (files.startsWith("level") && 
						(files.endsWith(".txt") || files.endsWith(".TXT"))) {
					myLayout.add(new Level(new Scanner(listOfFiles[i])));
				}
			}
		}
	}

	/**
	 * Used to remove a Level from the Collection
	 * @param levelIndex - indicates which Level should be removed
	 */
	public void removeLevel(int levelIndex) {
		if (levelIndex < myCurrentLevel) 
			myCurrentLevel--;
		myLayout.remove(levelIndex);
	}
	
	/**
	 * Used to skip to any Level within the current Collection of Levels
	 * @param levelIndex - indicates the Level to jump to
	 * @return the Collection of Sprites that constitutes the selected Level
	 */

	public Collection<Sprite> skipToLevel(int levelIndex) {
		myCurrentLevel = levelIndex - 1; 
		return myLayout.get(myCurrentLevel).getSpritesList();
	}

	/**
	 * 
	 * @return the Collection of Sprites that constitutes the next Level in the folder
	 */
	
	public Collection<Sprite> nextLevel() {
		myCurrentLevel++;
		return skipToLevel(myCurrentLevel);
	}
}

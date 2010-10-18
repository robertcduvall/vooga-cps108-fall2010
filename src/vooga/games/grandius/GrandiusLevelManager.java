package vooga.games.grandius;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.golden.gamedev.object.Sprite;

import vooga.engine.level.Level;
import vooga.engine.level.LevelManager;

public class GrandiusLevelManager extends LevelManager {

	private List<GrandiusLevel> myGrandiusLayout;
	
	public GrandiusLevelManager() {
		super();
		myGrandiusLayout = new ArrayList<GrandiusLevel>();
	}
	
	@Override
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
					myGrandiusLayout.add(new GrandiusLevel(path+"/"+files));
					System.out.println("files="+files);
				}
			}
		}
	}

	/**
	 * Used to remove a Level from the Collection.
	 * @param levelIndex - Indicates which Level should be removed.
	 */
	@Override
	public void removeLevel(int levelIndex) {
		if (levelIndex < getMyCurrentLevel()) 
			 setMyCurrentLevel(getMyCurrentLevel()-1);
		myGrandiusLayout.remove(levelIndex);
	}
	
	/**
	 * Used to skip to any Level within the current Collection of Levels.
	 * @param levelIndex - indicates the Level to jump to.
	 * @return the Collection of Sprites that constitutes the selected Level.
	 */
	@Override
	public ArrayList<ArrayList<Sprite>> skipToLevel(int levelIndex) {
		setMyCurrentLevel(levelIndex); 
		return myGrandiusLayout.get(getMyCurrentLevel()-1).getGrandiusSpritesList();
	}
	
	/**
	 * 
	 * @return The Collection of Sprites that constitutes the next Level in the folder.
	 */
	
	@Override
	public ArrayList<ArrayList<Sprite>> nextLevel() {
		setMyCurrentLevel(getMyCurrentLevel()+1);
		return currentLevel();
	}
	
	
	@Override
	public ArrayList<ArrayList<Sprite>> currentLevel() {
		ArrayList<ArrayList<Sprite>> returnCollection = new ArrayList<ArrayList<Sprite>>();
		//array index compensation of -1 (if myCurrentLevel = 1, we want the 0th entry of the list)
			returnCollection = (( (GrandiusLevel)(myGrandiusLayout.get(getMyCurrentLevel()-1)) ).getGrandiusSpritesList());
		return returnCollection;
	}
	
	@Override
	public GrandiusLevel getCurrentLevel() {
		return myGrandiusLayout.get(getMyCurrentLevel()-1);
	}
	
	public List<GrandiusLevel> getGrandiusLayout() {
		return myGrandiusLayout;
	}
}

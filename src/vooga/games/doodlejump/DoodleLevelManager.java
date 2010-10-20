package vooga.games.doodlejump;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Scanner;

import com.golden.gamedev.object.PlayField;

import vooga.engine.factory.LevelFactory;
import vooga.engine.factory.LevelManager;

public class DoodleLevelManager extends LevelManager{
	private LevelFactory levelFactory = new DoodleLevel();
	
	@Override
	public Collection<String> getLevelNames(File levelNamesFile) {
		Scanner levelScanner;
		try {
			levelScanner = new Scanner(levelNamesFile);
			while(levelScanner.hasNextLine()){
				String levelName = levelScanner.nextLine();
				if (levelName.equals("") || levelName.startsWith("#")){
					continue;
				}
				else{
					myLevelNames.add(levelName);
				}
			}
		} catch (FileNotFoundException e) {
			System.exit(0);
		}
		return myLevelNames;
	}

	@Override
	public PlayField getCurrentPlayField(File currentLevelFactoryFile) {
		return levelFactory.getPlayfield(currentLevelFactoryFile);
	}
	
	public LevelFactory getCurrentDoodleLevel(){
		return levelFactory;
	}
}

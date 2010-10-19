package vooga.games.galaxyinvaders;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Scanner;

import com.golden.gamedev.object.PlayField;

import vooga.engine.factory.LevelFactory;
import vooga.engine.factory.LevelManager;

public class GalaxyLevelManager extends LevelManager {

	private LevelFactory levelFactory = new GalaxyLevelFactory();
	
	@Override
	public Collection<String> getLevelNames(File levelNamesFile) {
		Scanner scanner;
		try {
			scanner = new Scanner(levelNamesFile);
			while(scanner.hasNextLine()){
				String levelName = scanner.nextLine();
				if (levelName.equals("") || levelName.startsWith("#")){
					continue;
				}
				else{
					myLevelNames.add(levelName);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("LevelNames File not found!");
		}
		return myLevelNames;
	}

	@Override
	public PlayField getCurrentPlayField(File currentLevelFactoryFile) {
		return levelFactory.getPlayfield(currentLevelFactoryFile);
	}

}

package vooga.games.grandius;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import vooga.engine.factory.LevelManager;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;

public class GrandiusLevelManager extends LevelManager{
	private GrandiusLevelFactory levelFactory = new GrandiusLevelFactory();
	
	
	public GrandiusLevelManager() {
		super();
	}

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

	public ArrayList<ArrayList<Sprite>> currentLevel() {
		ArrayList<ArrayList<Sprite>> returnCollection = new ArrayList<ArrayList<Sprite>>();
		returnCollection = (levelFactory.getGrandiusSpritesList());
		return returnCollection;
	}
	
	public Background getBackground(){
		return levelFactory.getBackground();
	}

}


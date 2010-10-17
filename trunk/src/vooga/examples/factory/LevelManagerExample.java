package vooga.examples.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;
import java.util.Scanner;

import com.golden.gamedev.object.PlayField;

import vooga.engine.factory.LevelManager;
import vooga.engine.resource.Resources;

/**
 * This class provides full implementation for abstract LevelManager Class.
 * An example of how this could be used in a game is as follows:
 * <pre>
 * <code>
 * 	private LevelManagerExample levelManager = new LevelManagerExample();
	private PlayField playfield;
	
	public void initResources() { 
		
		Properties propertiesFile = new Properties();
		try {
			propertiesFile.load(new FileInputStream("src/vooga/examples/factory/Directories.properties"));
		}
		catch(IOException e)
		{
			System.out.println(".properties file not found!");
		}
		
		String levelFilesDirectory = propertiesFile.getProperty("levelFilesDirectory");
		String levelNamesFile = propertiesFile.getProperty("levelNamesFile");
		levelManager.addLevels(levelFilesDirectory,new File(levelNamesFile));
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
 * @author bhawana
 *
 */
public class LevelManagerExample extends LevelManager{
	private LevelFactoryExample levelFactory = new LevelFactoryExample();
	
	
	public LevelManagerExample() {
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

}

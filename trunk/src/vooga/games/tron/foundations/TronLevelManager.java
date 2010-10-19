package vooga.games.tron.foundations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;


import vooga.engine.core.Sprite;
import vooga.examples.level.Level;

public class TronLevelManager {

	int currentLevel;
	int totalLevels = 0;
	
	ArrayList<Collection<Sprite>> currentLevels = new ArrayList<Collection<Sprite>>();
	
	public TronLevelManager(File initFile){
	
		currentLevel = 0;
				
		addLevel(initFile);
		
	}
	
	public void addLevel(File initFile){
		
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(initFile);
		} catch (FileNotFoundException e) {
			System.out.println("File not found: "+initFile.getName());
		}
		
		Level tempLevel = new Level(fileScanner);
		
		currentLevels.add(tempLevel.getSpritesList());
		
		totalLevels++;
		
	}

	public Collection<Sprite> getCurrentLevel(){
		return currentLevels.get(currentLevel);
	}
	
	public Collection<Sprite> getCurrentLevelAndIncrement(){
		currentLevel++;
		return currentLevels.get(currentLevel-1);
	}
	
	public boolean outOfLevels(){
		return (currentLevel >= totalLevels);
	}
	
	
}

package vooga.games.tronlegacy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.factory.LevelManager;
import vooga.engine.resource.Resources;

public class TLevelManager extends LevelManager {

	@Override
	public Collection<String> getLevelNames(File levelNamesFile) {
		
		ArrayList<String> returnNames = new ArrayList<String>();
		
		try {
			Scanner levelScanner = new Scanner(levelNamesFile);
		
			levelScanner.useDelimiter("\n");
			
			while (levelScanner.hasNext()){
			
				returnNames.add(levelScanner.next());
				
			}
		
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return returnNames;
		
	}

	@Override
	public PlayField getCurrentPlayField(File currentLevelFactoryFile) {
		
		PlayField returnField = new PlayField();
		SpriteGroup levelSprites = new SpriteGroup("levelSprites");
		
		try {
			Scanner levelScanner = new Scanner(currentLevelFactoryFile);			
			while (levelScanner.hasNextLine()){
				
				Scanner lineScanner = new Scanner(levelScanner.nextLine());
				lineScanner.useDelimiter(",");
				
				String spriteName = lineScanner.next();
				int spriteX = lineScanner.nextInt();
				int spriteY = lineScanner.nextInt();
		
				Sprite tempSprite = new Sprite(Resources.getImage(spriteName),spriteX,spriteY);
				
				levelSprites.add(tempSprite);
				
			}
			
		} catch (FileNotFoundException e) {
			//do nothing for a blank level
		}
		
		returnField.addGroup(levelSprites);
		
		return returnField;
		
	}

}

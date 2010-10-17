package vooga.examples.factory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.object.Sprite;

import vooga.engine.factory.LevelFactory;
import vooga.engine.resource.Resources;

/**
 * This is an example to demonstarte the LevelFactory interface.This class will 
 * be used by the class extending LvelManager to get the Playfield corresponding 
 * to the level passed into the getPlayfield method of this class.
 * @author bhawana
 *
 */
public class LevelFactoryExample implements LevelFactory{
	private PlayField playfield;	
	
	@Override
	public PlayField getPlayfield(File levelFactoryFile) {
		
		playfield = new PlayField();	
		try {
			Scanner scanner = new Scanner(levelFactoryFile);
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				if(line.startsWith("#Background")){
					line=scanner.nextLine();
					BufferedImage image =  getGameObject(line).getImage();
					Background background = new ImageBackground(image);
					playfield.setBackground(background);
				}
				else if (line.equals("") || line.startsWith("#")){
					continue;
				}
				else {
					playfield.add(getGameObject(line));
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("LevelFactoryFile not found!");
		}
		return playfield;
	}

	
	private Sprite getGameObject(String line) {
		Scanner details = new Scanner(line);
		details.useDelimiter(", *");
		String image = details.next();
		System.out.println(image);
		double width = details.nextDouble();
		double height = details.nextDouble();
		Sprite sprite = new Sprite(Resources.getImage(image),width,height);
		return sprite;
	}
	
}

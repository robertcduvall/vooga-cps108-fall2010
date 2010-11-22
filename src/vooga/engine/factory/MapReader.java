package vooga.engine.factory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import vooga.engine.core.PlayField;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

/**
 * 
 * This class can be used to read in .txt files that serve as a map for placement of different Sprites.
 * This is a unique implementation of a level.
 * 
 * 
 * @example
 * 
 * @author David Herzka - original author for MarioClone game
 * @author Cameron McCallie, Derek Zhou- ported the MarioClone version to be reusable by all
 *
 */
public class MapReader {

	private static PlayField myPlayfield;
	private static int myWidth;
	private static int myHeight;
	private static String path;
	private static Map<String, String> mySpriteClasses;
	private static Map<String, List<String>> mySpriteImages;
	// This is the pixel size of each object in the text file.
	private static int SPRITE_SIZE = 64;


	/**
	 * This MapReader will be instantiated by the LevelParser class if it contains a <Map> tag.
	 * This tag will store all information about Sprites unique to the mapping representation of the text file.
	 * This constructor returns the PlayField that is represented by the current level from the LevelParser. This
	 * constructor will update the PlayField based on all the information in the <Map> tag.
	 */

	public MapReader(String pathName, PlayField level){
		path = pathName;
		myPlayfield = level;
		mySpriteClasses = new HashMap<String, String>();
		mySpriteImages = new HashMap<String, List<String>>();

	}

	/**
	 * Spaces each sprite exactly the size of the sprite (in pixels) away from the previous sprite.
	 * 
	 */
	private double spritesToPixels(int coord) {
		return coord * SPRITE_SIZE;
	}



	public void addAssociationClass(String key, String className){
		mySpriteClasses.put(key, className);
	}
	
	public void addAssociationImage(String key, String imageName) {
		if (!mySpriteImages.containsKey(key))
			mySpriteImages.put(key, new ArrayList<String>());
		mySpriteImages.get(key).add(imageName);
	}

	public PlayField processMap(){
		try {
			loadMappedSprites(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myPlayfield;
	}

	@SuppressWarnings("unchecked")
	public void loadMappedSprites(String pathName) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();
		myWidth = 0;
		myHeight = 0;

		//	TODO: Need an error check if the pathName is messed up - resources job?

		Scanner lineReader = new Scanner(new File(pathName));

		while (lineReader.hasNextLine()) {
			String currentLine = lineReader.nextLine();

			// Add every line except for comments
			if (!currentLine.startsWith("#")) {
				lines.add(currentLine);

				//Ensures that myWidth is equal to the length of the longest line in the map
				myWidth = Math.max(myWidth, currentLine.length());
			}
		}

		// currentKey represents a key from the Associativities map,
		// and will always be represented as a character.
		Character currentKey = ' ';
		myHeight = lines.size();
		SpriteGroup mapGroup = new SpriteGroup("Map Group");
		
		Class<?>[] constructorParams = {double.class, double.class, BufferedImage[].class};
		
		for (int j = 0; j < myWidth; j++) {
			for (int k = 0; k < myHeight; k++) {
				double x = spritesToPixels(j);
				double y = spritesToPixels(k);

				currentKey = (j < lines.get(k).length()) ? lines.get(k).charAt(j) : ' ';
				
				if (mySpriteClasses.containsKey(currentKey+"")){
					String classToCreate = mySpriteClasses.get(currentKey+""); 
					List<String> imageNames = mySpriteImages.get(currentKey+"");
					BufferedImage[] images = new BufferedImage[imageNames.size()];
					for(int i = 0; i < imageNames.size(); i++)
						images[i] = Resources.getImage(imageNames.get(i));
					Class<? extends Sprite> op;
					Constructor<? extends Sprite> cons;
					try {
						op = (Class<? extends Sprite>) Class.forName(classToCreate);
						cons = op.getConstructor(constructorParams);
						Sprite obj = cons.newInstance(x,y,images);
						mapGroup.add(obj);
					} catch (Exception e) {
						throw ClassAssociatedException.CLASS_NOT_FOUND ;
					}
				}
				else{
					if(currentKey!=' ')
						throw ClassAssociatedException.CHARACTER_NOT_FOUND;
				}
			}
		}
		myPlayfield.addGroup(mapGroup);
	}
}

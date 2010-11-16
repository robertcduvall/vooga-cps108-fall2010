package vooga.engine.factory;

import java.io.IOException;
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
	private static Map<String, String> myAssociativities;

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
		myAssociativities = new HashMap<String, String>();
		

	}

	/**
	 * Spaces each sprite exactly the size of the sprite (in pixels) away from the previous sprite.
	 * 
	 */
	private double spritesToPixels(int coord) {
		return coord * SPRITE_SIZE;
	}



	private void addAssociation(String key, String className){
		myAssociativities.put(key, className);
	}

	private PlayField processMap(){
		try {
			loadMappedSprites(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myPlayfield;
	}

	public void loadMappedSprites(String pathName) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();
		myWidth = 0;
		myHeight = 0;

		//	TODO: Need an error check if the pathName is messed up - resources job?

		Scanner lineReader = new Scanner(pathName);

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
		for (int j = 0; j < myWidth; j++) {
			for (int k = 0; k < myHeight; k++) {
				double x = spritesToPixels(j);
				double y = spritesToPixels(k);

				currentKey = (j < lines.get(k).length()) ? lines.get(k).charAt(j) : ' ';
				
				if (myAssociativities.containsKey(currentKey)){
					String classToCreate = myAssociativities.get(currentKey); 
					Class<Sprite> op;
					try {
						op = (Class<Sprite>) Class.forName(classToCreate);
						Sprite obj = op.newInstance();
						myPlayfield.add(obj);
					} catch (Exception e) {
						throw ClassAssociatedException.CLASS_NOT_FOUND ;
					}
				}
				else{
					throw ClassAssociatedException.CHARACTER_NOT_FOUND;
				}
			}
		}
	}
}

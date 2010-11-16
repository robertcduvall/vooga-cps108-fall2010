

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
 * @author Cameron McCallie - ported the MarioClone version to be reusable by all
 *
 */
public class MapReader {
	
	private static PlayField myPlayfield;
	private static int myWidth;
	private static int myHeight;
	
	// This is the pixel size of each object in the text file.
	private static int SPRITE_SIZE = 64;

	
	/**
	 * This MapReader will be instantiated by the LevelParser class if it contains a <Map> tag.
	 * This tag will store all information about Sprites unique to the mapping representation of the text file.
	 * This constructor returns the PlayField that is represented by the current level from the LevelParser. This
	 * constructor will update the PlayField based on all the information in the <Map> tag.
	 */
	
	public MapReader(String pathName, PlayField level){
		
		loadMappedSprites(pathName);
		
	}
	
	/**
	 * Spaces each sprite exactly the size of the sprite (in pixels) away from the previous sprite.
	 * 
	 */
	private double spritesToPixels(int coord) {
		return coord * SPRITE_SIZE;
	}

	
	
	private void addAssociation(String key, String className){
		Class sprite = Class.forName(className);
		
	}
	
	private PlayField processMap(){
		
		return myPlayfield;
	}

	public void loadMappedSprites(String pathName) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();
		myWidth = 0;
		myHeight = 0;

//		TODO: Need an error check if the pathName is messed up - resources job?

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

		// parse the lines to create a tile list
		Character curChar = ' ';
		myHeight = lines.size();
		for (int j = 0; j < myWidth; j++) {
			for (int k = 0; k < myHeight; k++) {
				double x = spritesToPixels(j);
				double y = spritesToPixels(k);
				
				curChar = (j < lines.get(k).length()) ? lines.get(k).charAt(j) : ' ';
				
				switch (curChar) {
//				case (' '):
//					break;
//				case ('F'):
//					addTile(new IndestructibleTile(x, y, Resources.getImage("GrassTile")));
//					break;
//				case ('D'):
//					addTile(new IndestructibleTile(x, y, Resources
//							.getImage("DirtTile")));
//					break;
//				case ('B'):
//					addTile(new BreakTile(x, y, Resources.getImage("Break")));
//					break;
//				case ('C'):
//					List<BufferedImage> changingImages = new ArrayList<BufferedImage>();
//					changingImages.add(Resources.getImage("Changing1"));
//					changingImages.add(Resources.getImage("Changing2"));
//					changingImages.add(Resources.getImage("Changing3"));
//					changingImages.add(Resources.getImage("Changing4"));
//					addTile(new ChangingTile(x, y, changingImages));
//					break;
//				case ('G'):
//					List<BufferedImage> itemTileImages = new ArrayList<BufferedImage>();
//					itemTileImages.add(Resources.getImage("ItemTile1"));
//					itemTileImages.add(Resources.getImage("ItemTile2"));
//					GravityItem gravityItem = new GravityItem(new Sprite(
//							Resources.getImage("GravityItem")), .5);
//					gravityItem.setLocation(x
//							+ (TILE_SIZE - gravityItem.getWidth()) / 2, y
//							- gravityItem.getHeight());
//					addTile(new ItemTile(x, y, itemTileImages, gravityItem));
//					break;
//				case ('S'):
//					List<BufferedImage> coinTileImages = new ArrayList<BufferedImage>();
//					coinTileImages.add(Resources.getImage("ItemTile1"));
//					coinTileImages.add(Resources.getImage("ItemTile2"));
//					Coin coin = new Coin(new Sprite(Resources
//							.getImage("Coin")));
//					coin.setLocation(x + (TILE_SIZE - coin.getWidth()) / 2, y
//							- coin.getHeight());
//					addTile(new CoinTile(x, y, coinTileImages, coin));
//					break;
				}
			}
		}

	}

}

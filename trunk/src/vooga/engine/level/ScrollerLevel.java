package vooga.engine.level;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.StringTokenizer;

import vooga.engine.resource.Resources;
import vooga.games.grandius.enemy.boss.BossPart;
import vooga.games.grandius.enemy.common.Boomer;
import vooga.games.grandius.enemy.common.Zipster;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;

/**
 * @author Bhawana, Cameron, Derek
 * 
 *         This describes the type of level where the portion of the game space
 *         displayed on the screen moves with the position of the hero, similar
 *         to a side-scroller. Here, the hero is always in the center of the
 *         screen. <br>
 * 		   <br>
 *         Every time a new level is loaded, i.e., the LevelManager's nextLevel
 *         method is called, the Collection of Sprites returned should be stored
 *         in an instance variable, say allsprites. The getCurrentScreenSprites
 *         method needs to be invoked in the update method of the main game with
 *         allsprites as a parameter. All the Sprites returned by this method
 *         should then be updated to the screen.
 * 
 * <pre>
 * @code
 * LevelManager manager = new LevelManager();
 * Collection(Sprite) allSprites = manager.nextLevel();
 * 
 * public void update(long elapsedTime){
 * Collection<Sprite> currentSprites = ScrollerLevel.getCurrentScreenSprites(allSprites,heroX,heroY);
 * for(Sprite sprite : currentSprites){
 * 	sprite.update(elapsedTime);
 * }
 * </pre>
 */
public class ScrollerLevel extends Level {
//	private Dimension myGameSpace;
//	private Dimension myScreenSize;
	private ArrayList<Sprite> myBossPartList;
	
//	/**
//	 * @param fileToBeRead
//	 *            - level file that contains Sprites' details
//	 * @param screenSize
//	 *            - Dimension of the Screen
//	 * @param gameSpace
//	 *            - Dimension of the gameSpace
//	 */
//	public ScrollerLevel(String fileToBeRead, Dimension screenSize,
//			Dimension gameSpace) {
//		super(fileToBeRead, true);
//		myBossPartList = new ArrayList<Sprite>();
//		System.out.println("back in the ScrollerLevel constructor");
//		try {
//			System.out.println("reached ScrollerLevel constructor try block");
//			loadScrollerLevel(fileToBeRead);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		myScreenSize = screenSize;
//		myGameSpace = gameSpace;
//	}
	
	public ScrollerLevel(String fileToBeRead) {
		super(fileToBeRead);
		myBossPartList = new ArrayList<Sprite>();
		//System.out.println("back in the ScrollerLevel constructor");
		try {
			//System.out.println("reached ScrollerLevel constructor try block");
			loadScrollerLevel(fileToBeRead);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public void setScreenSize(Dimension screensize) {
//		myScreenSize = screensize;
//	}
//	
//	public void setGameSpace(Dimension gamespace) {
//		myGameSpace = gamespace;
//	}

	public void loadScrollerLevel(String fileToBeRead) throws IOException {
		System.out.println("reached loadScrollerLevel");
		//while (fileToBeRead.hasNextLine()) {
		//            String spriteDetails = fileToBeRead.nextLine();
		//            Scanner details = new Scanner(spriteDetails);
		//            details.useDelimiter(", *");
		//            String imageName = details.next();
		//            BufferedImage image = ResourceHandler.getImage(imageName);
		//            
		//            double xPosition = details.nextDouble();
		//            double yPosition = details.nextDouble();
		//            Sprite sprite = new Sprite(image, xPosition, yPosition);
		//            mySpritesList.add(sprite);

		ArrayList<String> lines = new ArrayList<String>();
		int size = 0;
		boolean bossMode = false;
		StringTokenizer st;
		URL url = Resources.class.getClassLoader().getResource(fileToBeRead);
		try {
			url = new File(fileToBeRead).toURI().toURL();
		} catch (MalformedURLException e) {
			System.out.println("Malformed URL");
		}
		System.out.println(url);
		if (url == null) {
			throw new IOException("No such directory: " + fileToBeRead);
		}
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(url.openStream()));
		while (true) {
			String line = reader.readLine();
			if (line == null) {
				reader.close();
				break;
			}
			if (!line.equals("") && !line.startsWith("#")) {
				lines.add(line);
			}
		}
		size = lines.size();
//		st = new StringTokenizer(lines.get(0), ",");
//		this.setScreenSize(new Dimension(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
//		st = new StringTokenizer(lines.get(1), ",");
//		this.setGameSpace(new Dimension(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		for (int y=2; y<size; y++) {
			String line = lines.get(y);
			if (line.equals("$Bosses")) {
				bossMode = true;
				y++;
				line = lines.get(y);
			}
			if (bossMode) {
				st = new StringTokenizer(line, ",");
				String bossName = st.nextToken();
				line = lines.get(++y);
				st = new StringTokenizer(line, ",");
				int[] breakpoints = new int[2];
				breakpoints[0] = Integer.parseInt(st.nextToken());
				breakpoints[1] = Integer.parseInt(st.nextToken());
				line = lines.get(++y);
				st = new StringTokenizer(line, ",");
				double xPosition = Double.parseDouble(st.nextToken());
				double yPosition = Double.parseDouble(st.nextToken());
				line = lines.get(++y);
				st = new StringTokenizer(line, ",");
				int health = Integer.parseInt(st.nextToken());
				//int shields = Integer.parseInt(st.nextToken());
//				BossPart newBossPart = new BossPart(Resources.getAnimation(bossName), breakpoints, xPosition, yPosition, health, shields);
//				myBossPartList.add(newBossPart);
			} else {
				st = new StringTokenizer(line, ",");
				String spriteName = st.nextToken();
				double xPosition = Double.parseDouble(st.nextToken());
				double yPosition = Double.parseDouble(st.nextToken());
				if (spriteName.equals("Zipster")) {
					Zipster newZipster = new Zipster(Resources.getAnimation(spriteName), xPosition, yPosition);
					mySpritesList.add(newZipster);
				}
				if (spriteName.equals("Boomer")) {
					Boomer newBoomer = new Boomer(Resources.getAnimation(spriteName), xPosition, yPosition);
					mySpritesList.add(newBoomer);
				}
				//AnimatedSprite newAnimatedSprite = new AnimatedSprite(Resources.getAnimation(spriteName), xPosition, yPosition);
			}
		}
	}

	public ArrayList<ArrayList<Sprite>> getScrollerSpritesList() {
		ArrayList<ArrayList<Sprite>> returnCollection = new ArrayList<ArrayList<Sprite>>();
		returnCollection.add(mySpritesList);
		returnCollection.add(myBossPartList);
		return returnCollection;
	}
	/**
	 * This method will be called in the update method of the main game class.
	 * It returns all the sprites to be currently displayed on the screen,
	 * ensuring that the hero is in the center of the screen.
	 * 
	 * @param allSprites - Collection of all the sprites for the current level
	 * @param heroX - X coordinate of the hero, 
	 * @param heroY - Y coordinate of the hero
	 * 
	 * @return Collection of Sprites to be updated to the screen
	 */
	public Collection<Sprite> getCurrentScreenSprites(
			Collection<Sprite> allSprites, double heroX, double heroY) {

//		double screenWidth = myScreenSize.getWidth();
////		System.out.println("Screenwidth = "+screenWidth);
//		double screenHeight = myScreenSize.getHeight();
//		System.out.println("Screenheight = "+screenHeight);
//		double xMin = getMin(heroX, screenWidth, myGameSpace.getWidth());
//		double yMin = getMin(heroY, screenHeight, myGameSpace.getHeight());
//		double xMax = xMin + screenWidth;
//		double yMax = yMin + screenHeight;
		
//		double xMin = getMin(heroX-myScreenSize.getWidth()/2, 0, myGameSpace.getWidth());
//		double xMin = Math.max(heroX-screenWidth/2, 0);
////		System.out.println("xmin="+xMin);
//		//double yMin = getMax(heroY, 0, myGameSpace.getHeight());
//		double xMax = Math.min(xMin + screenWidth, myGameSpace.getWidth());
////		System.out.println("xmax="+xMax);
		Collection<Sprite> currentSpritesList = new ArrayList<Sprite>();
		for (Sprite sprite : allSprites) {
//			System.out.println("spritex ="+sprite.getX());
			//if (isInRange(sprite.getX(), xMin, xMax)) {
//				System.out.println(sprite+"is in range");
					//&& isInRange(sprite.getY(), yMin, yMax)) {
				currentSpritesList.add(sprite);
			//}
		}
		return currentSpritesList;
	}

	/**
	 * This method returns the minimum value of the coordinate to be displayed
	 * on the screen depending upon the current position of the hero.
	 * 
	 * @param heroCoordinate - hero's current position
	 * @param screen - screen dimension
	 * @param gameSpace - gameSpace dimension
	 * @return minimum value of the coordinate to be displayed
	 */
	private double getMin(double heroCoordinate, double screen, double gameSpace) {
		if (heroCoordinate < screen / 2) {
			return 0;
		} 
		else if (heroCoordinate > (gameSpace - screen / 2)) {
			return gameSpace - screen;
		} 
		else{
			return heroCoordinate - screen / 2;
		}
	}
	
	/**
	 * This method checks whether a Sprite's current position is in range or not,
	 * i.e., if the sprite's current coordinates lie between the minimum and
	 * maximum values of the displayed coordinates.
	 *
	 *@param value - Sprite's coordinate to be checked
	 *@param min - minimum coordinate displayed on screen
	 *@param max - maximum coordinate displayed on screen
	 *@return a boolean which indicates whether the sprite's current coordinate is in range or not
	 */
	private boolean isInRange(double value, double min, double max) {
		return (min < value && value < max);
	}

}

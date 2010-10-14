package vooga.games.grandius;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import vooga.engine.level.Level;
import vooga.engine.resource.Resources;
import vooga.games.grandius.enemy.boss.BossPart;
import vooga.games.grandius.enemy.boss.GrandiusBoss;
import vooga.games.grandius.enemy.boss.reacher.Reacher;
import vooga.games.grandius.enemy.boss.reacher.ReacherEye;
import vooga.games.grandius.enemy.common.Boomer;
import vooga.games.grandius.enemy.common.Zipster;

import com.golden.gamedev.object.Sprite;

/**
 * A level in the game Grandius.
 * @author jtk11
 *
 */
public class GrandiusLevel extends Level {
	
	private ArrayList<Sprite> myBossPartList;
	private ArrayList<Sprite> myBossList;

	public GrandiusLevel(String fileToBeRead) {
		super(fileToBeRead);
		myBossPartList = new ArrayList<Sprite>();
		myBossList = new ArrayList<Sprite>();
		try {
			loadGrandiusLevel(fileToBeRead);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadGrandiusLevel(String fileToBeRead) throws IOException {
		System.out.println("reached loadGrandiusLevel");
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
		for (int y=2; y<size; y++) {
			String line = lines.get(y);
			if (line.equals("$Bosses")) {
				bossMode = true;
				System.out.println("Going into boss mode");
				y++;
				line = lines.get(y);
			}
			if (bossMode) {
				st = new StringTokenizer(line, ",");
				String bossName = st.nextToken();
				line = lines.get(++y);
				st = new StringTokenizer(line, ",");
				int[] bossBreakpoints = new int[2];
				bossBreakpoints[0] = Integer.parseInt(st.nextToken());
				bossBreakpoints[1] = Integer.parseInt(st.nextToken());
				line = lines.get(++y);
				st = new StringTokenizer(line, ",");
				double bossXPosition = Double.parseDouble(st.nextToken());
				double bossYPosition = Double.parseDouble(st.nextToken());
				line = lines.get(++y);
				st = new StringTokenizer(line, ",");
				int bossHealth = Integer.parseInt(st.nextToken());
				GrandiusBoss newBoss = null;
				if (lines.get(++y).equals("$Parts")) {
					System.out.println("Parts mode");
					line = lines.get(++y);
					System.out.println(line);
					while(!line.isEmpty()) {
						System.out.println(line);
						st = new StringTokenizer(line, ",");
						String bossPartName = st.nextToken();
						line = lines.get(++y);
						System.out.println(line);
						st = new StringTokenizer(line, ",");
						int[] breakpoints = new int[2];
						breakpoints[0] = Integer.parseInt(st.nextToken());
						breakpoints[1] = Integer.parseInt(st.nextToken());
						line = lines.get(++y);
						System.out.println(line);
						st = new StringTokenizer(line, ",");
						double xPosition = Double.parseDouble(st.nextToken());
						double yPosition = Double.parseDouble(st.nextToken());
						line = lines.get(++y);
						System.out.println(line);
						st = new StringTokenizer(line, ",");
						int health = Integer.parseInt(st.nextToken());
						int shields = Integer.parseInt(st.nextToken());
						BossPart newBossPart = null;
						if (bossPartName.equals("ReacherEye")) {
							System.out.println("making new ReacherEye");
							newBossPart = new ReacherEye(Resources.getAnimation(bossPartName), breakpoints, xPosition, yPosition, health, shields);
						}
						myBossPartList.add(newBossPart);
						//y++;
						System.out.println("y="+y);
						System.out.println("size="+lines.size());
						if ((y+1)==lines.size())
							break;
						line = lines.get(++y);
					}
				}
//				y++;
//				line = lines.get(y);
				if (bossName.equals("Reacher")) {
					System.out.println("creating Reacher at" + bossXPosition + "," + bossYPosition);
					newBoss = new Reacher(Resources.getAnimation(bossName), bossBreakpoints, bossXPosition, bossYPosition, bossHealth, myBossPartList);
				}
				myBossList.add(newBoss);
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

	public ArrayList<ArrayList<Sprite>> getGrandiusSpritesList() {
		ArrayList<ArrayList<Sprite>> returnCollection = new ArrayList<ArrayList<Sprite>>();
		returnCollection.add(mySpritesList);
		returnCollection.add(myBossPartList);
		returnCollection.add(myBossList);
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
//		Collection<Sprite> currentSpritesList = new ArrayList<Sprite>();
//		for (Sprite sprite : allSprites) {
////			System.out.println("spritex ="+sprite.getX());
//			//if (isInRange(sprite.getX(), xMin, xMax)) {
////				System.out.println(sprite+"is in range");
//					//&& isInRange(sprite.getY(), yMin, yMax)) {
//				currentSpritesList.add(sprite);
//			//}
//		}
//		return currentSpritesList;
		return allSprites;
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


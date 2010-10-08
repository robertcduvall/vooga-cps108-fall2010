package vooga.engine.level;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

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
	private static Dimension myGameSpace;
	private static Dimension myScreenSize;

	/**
	 * @param fileToBeRead
	 *            - level file that contains Sprites' details
	 * @param screenSize
	 *            - Dimension of the Screen
	 * @param gameSpace
	 *            - Dimension of the gameSpace
	 */
	public ScrollerLevel(String fileToBeRead, Dimension screenSize,
			Dimension gameSpace) {
		super(fileToBeRead);
		myScreenSize = screenSize;
		myGameSpace = gameSpace;
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
	public static Collection<Sprite> getCurrentScreenSprites(
			Collection<Sprite> allSprites, double heroX, double heroY) {

		double screenWidth = myScreenSize.getWidth();
		double screenHeight = myScreenSize.getHeight();

		double xMin = getMin(heroX, screenWidth, myGameSpace.getWidth());
		double yMin = getMin(heroY, screenHeight, myGameSpace.getHeight());
		double xMax = xMin + screenWidth;
		double yMax = yMin + screenHeight;

		Collection<Sprite> currentSpritesList = new ArrayList<Sprite>();
		for (Sprite sprite : allSprites) {
			if (isInRange(sprite.getX(), xMin, xMax)
					&& isInRange(sprite.getY(), yMin, yMax)) {
				currentSpritesList.add(sprite);
			}
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
	private static double getMin(double heroCoordinate, double screen, double gameSpace) {
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
	private static boolean isInRange(double value, double min, double max) {
		return (min < value && value < max);
	}

}

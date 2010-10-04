package vooga.engine.level.reader;

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
	public ScrollerLevel(Scanner fileToBeRead, Dimension screenSize,
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

	private static boolean isInRange(double value, double min, double max) {
		return (min < value && value < max);
	}

}

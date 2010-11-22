package vooga.games.jumper.events;

import java.awt.Point;
import java.util.Random;

import com.golden.gamedev.object.Sprite;

import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.games.jumper.sprites.NormalBlock;
import vooga.games.jumper.states.PlayGameState;

/**
 * Event to create the blocks.
 * @author Cody, Devon, Brian
 */
public class BlockGeneratorEvent implements IEventHandler {

	private static int blockCounter;
	Random random = new Random();
	
	int xMax = Resources.getInt("gameWidth");
	int xMin = 0;
	int yMax = Resources.getInt("gameHeight");
	int yMin = 0;
	double xVelocity = 0;
	double yVelocity = -0.2;
	//Minimum space between blocks.
	private final int MINIMUM_SPACE = 20;

	private PlayGameState playState;

	/**
	 * Sets the gamestate that this event applies to.
	 * @param gamestate
	 */
	public BlockGeneratorEvent(PlayGameState gamestate) {
		playState = gamestate;
	}

	@Override
	/**
	 * Returns true if a block is to be created.
	 */
	public boolean isTriggered() {
		return true;
	}

	/**
	 * Prevents blocks from generating over existing blocks.
	 * @param x
	 * @param y
	 * @return true if the proposed block to be 
	 * generated would overlap with an existing
	 * block.
	 */
	public boolean isOverlap(int x, int y) {
		for (Sprite s : playState.getGroup("normalBlocks").getSprites()) {
			if (s != null && x > s.getX() - MINIMUM_SPACE
					&& x < s.getX() + s.getWidth() + MINIMUM_SPACE
					&& y > s.getY() + s.getHeight() + MINIMUM_SPACE
					&& y < s.getY() - MINIMUM_SPACE) {
				return true;
			}
		}
		return false;
	}

	@Override
	/**
	 * If the event is triggered, generates a block.
	 */
	public void actionPerformed() {
		int randomX = random.nextInt(xMax - xMin) + xMin;
		int randomY = random.nextInt(yMax - yMin) + yMax;
		Point initPoint = new Point(randomX, randomY);
		NormalBlock addBlock;
		if (blockCounter == 1) {
			addBlock = new NormalBlock(
				Resources.getImage("platformGreen"), initPoint, xVelocity,
				yVelocity);
			blockCounter++;
		}else if (blockCounter == 2) {
			addBlock = new NormalBlock(
					Resources.getImage("platformLightBlue"), initPoint, xVelocity,
					yVelocity);
				blockCounter=0;
			}
		else {
			addBlock = new NormalBlock(
				Resources.getImage("platformWhite"), initPoint, xVelocity,yVelocity);
			blockCounter++;
		}
		if (!isOverlap(initPoint.x, initPoint.y)) {
			playState.getGroup("normalBlocks").add(addBlock);
		}
	}
}

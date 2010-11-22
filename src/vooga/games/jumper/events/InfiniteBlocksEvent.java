package vooga.games.jumper.events;

import java.awt.Point;
import java.util.Random;

import com.golden.gamedev.object.Sprite;

import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.games.jumper.sprites.NormalBlock;
import vooga.games.jumper.states.PlayGameState;

/**
 * Create random blocks
 * 
 * @author Cody
 */
public class InfiniteBlocksEvent implements IEventHandler {

	Random random = new Random();
	int xMax = Resources.getInt("gameWidth");
	int xMin = 0;
	int yMax = Resources.getInt("gameHeight");
	int yMin = 0;
	double xVelocity = 0;
	double yVelocity = -0.2;
	private final int MINIMUM_SPACE = 20;

	private PlayGameState playState;

	public InfiniteBlocksEvent(PlayGameState gamestate) {
		playState = gamestate;
	}

	@Override
	public boolean isTriggered() {
		return true;
	}

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
	public void actionPerformed() {
		int randomX = random.nextInt(xMax - xMin) + xMin;
		int randomY = random.nextInt(yMax - yMin) + yMax;
		Point initPoint = new Point(randomX, randomY);
		NormalBlock addBlock = new NormalBlock(
				Resources.getImage("platformGreen"), initPoint, xVelocity,
				yVelocity);
		if (!isOverlap(initPoint.x, initPoint.y)) {
			playState.getGroup("normalBlocks").add(addBlock);
		}
	}
}

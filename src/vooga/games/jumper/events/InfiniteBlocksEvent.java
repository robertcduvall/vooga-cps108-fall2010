package vooga.games.jumper.events;

import java.awt.Point;
import java.util.Random;

import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.games.jumper.sprites.NormalBlock;
import vooga.games.jumper.states.PlayGameState;

public class InfiniteBlocksEvent implements IEventHandler {
	Point initPoint = new Point(100, 100);
	double xVelocity = -3;
	double yVelocity = 0;

	private PlayGameState playState;
	Random random = new Random();

	public InfiniteBlocksEvent(PlayGameState gamestate) {
		playState = gamestate;
	}

	@Override
	public boolean isTriggered() {
		return true;
	}

	@Override
	public void actionPerformed() {
		NormalBlock addBlock = new NormalBlock(Resources.getImage("platformGreen"), initPoint, xVelocity, yVelocity);
		playState.getGroup("normalBlocks").add(addBlock);
	}
}

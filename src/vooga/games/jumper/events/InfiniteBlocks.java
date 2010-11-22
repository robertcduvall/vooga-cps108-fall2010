package vooga.games.jumper.events;

import java.awt.Point;
import java.util.Random;

import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.games.jumper.sprites.NormalBlock;
import vooga.games.jumper.states.PlayGameState;

public class InfiniteBlocks implements IEventHandler {
	private PlayGameState playState;
	Random random = new Random();

	public InfiniteBlocks(PlayGameState gamestate) {
		playState = gamestate;
	}

	@Override
	public boolean isTriggered() {
		return true;
	}

	@Override
	public void actionPerformed() {
		Point point = new Point(100, 100);
		NormalBlock addBlock = new NormalBlock(Resources.getImage("platformGreen"), point, -3, 0);
		playState.getGroup("normalBlocks").add(addBlock);
	}
}

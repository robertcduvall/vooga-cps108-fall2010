package vooga.games.jumper.events;

import com.golden.gamedev.object.Sprite;

import vooga.engine.event.IEventHandler;
import vooga.games.jumper.sprites.NormalBlock;
import vooga.games.jumper.states.PlayGameState;

/**
 * When a block hits the roof, remove it from the sprite group
 * 
 * @author Cody
 * 
 * 
 */
public class BlockOffScreenEvent implements IEventHandler {

	private double BLOCK_APPEARANCE_RATE_MULTIPLIER = 1.0005;
	private PlayGameState playState;
	NormalBlock blockToBeRemoved;

	public BlockOffScreenEvent(PlayGameState gamestate) {
		playState = gamestate;
	}

	@Override
	public boolean isTriggered() {
		for (Sprite s : playState.getGroup("normalBlocks").getSprites()) {

			if (s != null
					&& !s.isOnScreen()
					&& playState.getGroup("normalBlocks").getSize() > (20 * BLOCK_APPEARANCE_RATE_MULTIPLIER)) {
				blockToBeRemoved = (NormalBlock) s;
				return true;
			}
		}
		return false;
	}

	@Override
	public void actionPerformed() {
		BLOCK_APPEARANCE_RATE_MULTIPLIER += 0.0005;
		System.out.println(playState.getGroup("normalBlocks").getSize());
		playState.getGroup("normalBlocks").remove(blockToBeRemoved);
	}

}

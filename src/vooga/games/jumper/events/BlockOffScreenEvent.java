package vooga.games.jumper.events;

import com.golden.gamedev.object.Sprite;

import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.games.jumper.sprites.NormalBlock;
import vooga.games.jumper.states.PlayGameState;

/**
 * Removes blocks from sprite group when they reach the top of the screen.
 * @author Cody, Devon, Brian
 */
public class BlockOffScreenEvent implements IEventHandler {

	private double BLOCK_APPEARANCE_RATE_MULTIPLIER = Resources.getDouble("BLOCK_APPEARANCE_RATE_MULTIPLIER");
	private double BLOCK_APPEARANCE_RATE_MULTIPLIER_INCREMENT = Resources.getDouble("BLOCK_APPEARANCE_RATE_MULTIPLIER_INCREMENT");
	private PlayGameState playState;
	NormalBlock blockToBeRemoved;

	/**
	 * Sets the gamestate for this event
	 * @param gamestate
	 */
	public BlockOffScreenEvent(PlayGameState gamestate) {
		playState = gamestate;
	}

	@Override
	/**
	 * Becomes true if the block has reached the top of the screen and is ready to be removed.
	 */
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
	/**
	 * removes the block that has been designated to be removed.
	 */
	public void actionPerformed() {
		BLOCK_APPEARANCE_RATE_MULTIPLIER += BLOCK_APPEARANCE_RATE_MULTIPLIER_INCREMENT;
		playState.getGroup("normalBlocks").remove(blockToBeRemoved);
	}

}

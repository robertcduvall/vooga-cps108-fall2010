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

	private PlayGameState playState;
	NormalBlock blockToBeRemoved;

	public BlockOffScreenEvent(PlayGameState gamestate) {
		playState = gamestate;
	}

	@Override
	public boolean isTriggered() {
		for (Sprite s : playState.getGroup("normalBlocks").getSprites()) {
			
			if (s!=null && !s.isOnScreen()) {
				blockToBeRemoved = (NormalBlock) s;
				return true;
			}
		}
		return false;
	}

	@Override
	public void actionPerformed() {
		System.out.println(playState.getGroup("normalBlocks").getSize());
		playState.getGroup("normalBlocks").remove(blockToBeRemoved);
	}

}

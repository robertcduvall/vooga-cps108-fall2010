package vooga.games.jumper.events;

import com.golden.gamedev.object.Sprite;

import vooga.engine.event.IEventHandler;
import vooga.games.jumper.sprites.NormalBlock;
import vooga.games.jumper.states.PlayGameState;

public class BlockHitsRoofEvent implements IEventHandler {

	private PlayGameState playState;
	NormalBlock blockToBeRemoved;

	public BlockHitsRoofEvent(PlayGameState gamestate) {
		playState = gamestate;
	}

	@Override
	public boolean isTriggered() {
		for (Sprite s : playState.getGroup("normalBlocks").getSprites()) {
			if (s.getY() < 0) {
				blockToBeRemoved = (NormalBlock) s;
				return true;
			}
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void actionPerformed() {
		// TODO Auto-generated method stub
		playState.getGroup("normalBlocks").remove(blockToBeRemoved);
	}

}

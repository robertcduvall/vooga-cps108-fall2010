package vooga.games.doodlejump.rules;

import vooga.engine.core.BetterSprite;
import vooga.engine.event.IEventHandler;
import vooga.games.doodlejump.DoodleSprite;
import vooga.games.doodlejump.states.PlayState;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class LevelWonEvent implements IEventHandler{
	private DoodleSprite doodle;
	private PlayState playState;
	
	public LevelWonEvent(DoodleSprite doodle, PlayState playState) {
		this.doodle = doodle;
		this.playState = playState;
	}

	@Override
	public void actionPerformed() {
		playState.nextLevel();
	}

	@Override
	public boolean isTriggered() {
		return doodle.isLevelComplete();
	}
}

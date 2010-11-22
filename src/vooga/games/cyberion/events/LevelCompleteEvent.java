package vooga.games.cyberion.events;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.event.IEventHandler;
import vooga.games.cyberion.DropThis;
import vooga.games.cyberion.states.PlayState;

public class LevelCompleteEvent implements IEventHandler {

	private DropThis myGame;
	private PlayState playState;
	private SpriteGroup spriteGroup;

	public LevelCompleteEvent(DropThis game, PlayState state, SpriteGroup group) {
		myGame = game;
		playState = state;
		spriteGroup = group;
	}

	@Override
	public boolean isTriggered() {
		for (Sprite tempSprite : spriteGroup.getSprites()) {
			if (tempSprite != null && tempSprite.isActive())
				return false;
		}
		return true;
	}

	@Override
	public void actionPerformed() {
		PlayState newState = playState.nextLevel();
		myGame.setAsPlayGameState(newState);
		myGame.setPlayState();
	}
}

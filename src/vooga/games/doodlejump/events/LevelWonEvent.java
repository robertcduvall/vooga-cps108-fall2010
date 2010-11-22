package vooga.games.doodlejump.events;

import vooga.engine.core.BetterSprite;
import vooga.engine.event.IEventHandler;
import vooga.games.doodlejump.BlahThis;
import vooga.games.doodlejump.DoodleSprite;
import vooga.games.doodlejump.states.PlayState;

import vooga.engine.core.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class LevelWonEvent implements IEventHandler{
	private DoodleSprite doodle;
	private PlayState playState;
	private BlahThis game;
	
	public LevelWonEvent(DoodleSprite doodle, PlayState playState, BlahThis game) {
		this.doodle = doodle;
		this.playState = playState;
		this.game = game;
	}

	@Override
	public void actionPerformed() {
		if (playState.getLevelManager().getCurrentLevel()!=4) {
			doodle.reset();
			PlayField newField = playState.getLevelManager().loadNextLevel();
			playState.removeEverything();
			playState.setField(newField);
			playState.setDoodle((DoodleSprite) newField.getGroup("doodleGroup").getSprites()[0]);
			playState.initControls();
			playState.initEvents();
			//game.setAsPlayGameState(playState);
			game.getGameStateManager().switchTo(playState);
		}
	}

	@Override
	public boolean isTriggered() {
		return doodle.isLevelComplete();
	}
}

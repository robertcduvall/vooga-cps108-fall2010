package vooga.games.doodlejump.events;

import vooga.engine.core.BetterSprite;
import vooga.engine.event.IEventHandler;
import vooga.games.doodlejump.Blah;
import vooga.games.doodlejump.DoodleSprite;
import vooga.games.doodlejump.states.PlayState;

import vooga.engine.core.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

/**
 * The LevelWonEvent class implements IEventHandler and describes what happens
 * when a Level is Won.
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class LevelWonEvent implements IEventHandler {

	private static final int INVALID_LEVEL = 4;
	private static final int FIRST_SPRITE_GROUP = 0;
	private static final String DOODLE_GROUP_STRING = "doodleGroup";
	private DoodleSprite doodle;
	private PlayState playState;
	private Blah game;

	public LevelWonEvent(DoodleSprite doodle, PlayState playState, Blah game) {
		this.doodle = doodle;
		this.playState = playState;
		this.game = game;
	}

	@Override
	public void actionPerformed() {
		if (playState.getLevelManager().getCurrentLevel() != INVALID_LEVEL) {
			doodle.reset();
			PlayField newField = playState.getLevelManager().loadNextLevel();
			playState.removeEverything();
			playState.setField(newField);
			playState.setDoodle((DoodleSprite) newField.getGroup(
					DOODLE_GROUP_STRING).getSprites()[FIRST_SPRITE_GROUP]);
			playState.initControls();
			playState.initEvents();
			// game.setAsPlayGameState(playState);
			game.getGameStateManager().switchTo(playState);
		}
	}

	@Override
	public boolean isTriggered() {
		return doodle.isLevelComplete();
	}
}

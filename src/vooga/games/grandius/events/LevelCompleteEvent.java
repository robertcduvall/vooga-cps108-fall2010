package vooga.games.grandius.events;

import com.golden.gamedev.object.Sprite;

import vooga.engine.core.BetterSprite;
import vooga.engine.core.PlayField;
import vooga.engine.event.IEventHandler;
import vooga.engine.factory.LevelManager;
import vooga.games.grandius.DropThis;
import vooga.games.grandius.Player;
import vooga.games.grandius.states.PlayState;

public class LevelCompleteEvent implements IEventHandler{
	private DropThis grandius;
	private PlayState playState;
	
	public LevelCompleteEvent(DropThis grandius, PlayState playState){
		this.grandius = grandius;
		this.playState = playState;
	}

	@Override
	public boolean isTriggered() {
		for (Sprite s: playState.getGroup("enemyGroup").getSprites()) {
			if (s!=null && s.isActive())
				return false;
		}
		return true;
	}

	@Override
	public void actionPerformed() {
		PlayField newField = playState.getLevelManager().loadNextLevel();
		playState.removeEverything();
		playState.setField(newField);
		playState.setPlayer((Player) newField.getGroup("playerGroup").getSprites()[0]);
		playState.initControls();
		playState.initEvents();
		grandius.setAsPlayGameState(playState);
	}
}

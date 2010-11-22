package vooga.games.grandius.events;

import vooga.engine.core.PlayField;
import vooga.engine.event.IEventHandler;
import vooga.games.grandius.DropThis;
import vooga.games.grandius.sprites.Player;
import vooga.games.grandius.states.PlayState;

import com.golden.gamedev.object.Sprite;

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
		if (playState.getLevelManager().getCurrentLevel()!=2) {
			PlayField newField = playState.getLevelManager().loadNextLevel();
			playState.removeEverything();
			playState.setField(newField);
			playState.setPlayer((Player) newField.getGroup("playerGroup").getSprites()[0]);
			playState.initControls();
			playState.initEvents();
			grandius.setAsPlayGameState(playState);
			grandius.getGameStateManager().switchTo(grandius.getGameStateManager().getGameState(4)); //index of 5 = LevelCompleteState...apparently (backwards?)
		} else {
			grandius.getGameStateManager().switchTo(grandius.getGameStateManager().getGameState(3)); //index of 4 = GameCompleteState...apparently (backwards?)
		}
	}
}
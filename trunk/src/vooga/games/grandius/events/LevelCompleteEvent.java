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
	private PlayState playState;
	
	public LevelCompleteEvent(PlayState playState){
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
		LevelManager levelManager =  playState.getLevelManager();
		//if(levelManager.getCurrentLevel()!=2){
		PlayField newField = playState.getLevelManager().loadNextLevel();
		playState.getField().clearPlayField();
		playState.setField(newField);
		//}
	}

}

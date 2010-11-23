package vooga.games.cyberion.events;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.core.PlayField;
import vooga.engine.event.IEventHandler;
import vooga.engine.factory.LevelManager;
import vooga.games.cyberion.DropThis;
import vooga.games.cyberion.states.PlayState;
import vooga.games.grandius.sprites.Player;

public class LevelCompleteEvent implements IEventHandler {

	private DropThis cyberion;
	private PlayState playState;
	private LevelManager levelmanager;

	public LevelCompleteEvent(DropThis cyberion, LevelManager levelmanager,
			PlayState playState) {
		this.cyberion = cyberion;
		this.levelmanager = levelmanager;
		this.playState = playState;
	}

	@Override
	public boolean isTriggered() {
		for (Sprite s : playState.getGroup("enemyGroup").getSprites()) {
			if (s != null && s.isActive())
				return false;
		}
		return true;
	}

	@Override
	public void actionPerformed() {
		cyberion.getGameStateManager().switchTo(
				cyberion.getGameStateManager().getGameState(3));
//		System.out.println(cyberion.getGameStateManager().getGameState(2));
	}

}

package vooga.games.cyberion.rules;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.level.Rule;
import vooga.engine.overlay.Stat;
import vooga.engine.state.GameStateManager;
import vooga.engine.core.BetterSprite;
import vooga.games.cyberion.states.GameOverState;
import vooga.games.cyberion.states.MenuState;

public class Dead implements Rule {
	GameStateManager gameStateManager;
	GameOverState gameOverState;

	public Dead() {
		super();
	}

	public Dead(GameStateManager manager) {
		gameStateManager = manager;

	}

	public void setGameStateManager(GameStateManager manager, GameOverState state) {
		gameStateManager = manager;
		gameOverState = state;
	}

	@Override
	public void enforce(SpriteGroup... groups) {
//		gameStateManager.switchTo(gameOverState);
	}

	@Override
	public boolean isSatisfied(SpriteGroup... groups) {

//		BetterSprite tempSprite = (BetterSprite) groups[0].getSprites()[0];
//		int tempInt = (Integer) tempSprite.getStat("livesStat").getStat();
//		if (tempInt == 0) {
//			return true;
//		}
		return false;
	}

}

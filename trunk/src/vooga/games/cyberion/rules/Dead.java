package vooga.games.cyberion.rules;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.level.Rule;
import vooga.engine.overlay.Stat;
import vooga.engine.state.GameStateManager;
import vooga.engine.core.BetterSprite;
import vooga.games.cyberion.states.MenuState;

public class Dead implements Rule {
	GameStateManager gameStateManager;
	MenuState menuState;

	public Dead() {
		super();
	}

	public Dead(GameStateManager manager) {
		gameStateManager = manager;

	}

	public void setGameStateManager(GameStateManager manager, MenuState state) {
		gameStateManager = manager;
		menuState = state;
	}

	@Override
	public void enforce(SpriteGroup... groups) {
		gameStateManager.switchTo(menuState);
	}

	@Override
	public boolean isSatisfied(SpriteGroup... groups) {

		BetterSprite tempSprite = (BetterSprite) groups[0].getSprites()[0];
		int tempInt = (Integer) tempSprite.getStat("livesStat").getStat();
		if (tempInt == 0) {
			return true;
		}
		return false;
	}

}

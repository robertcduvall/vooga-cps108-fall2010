package vooga.games.cyberion.rules;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.level.Rule;
import vooga.engine.overlay.Stat;
import vooga.engine.state.GameStateManager;
import vooga.engine.core.BetterSprite;

public class Dead implements Rule {
	GameStateManager gameStateManager;

	public Dead() {

	}

	public Dead(GameStateManager manager) {
		gameStateManager = manager;

	}

	@Override
	public void enforce(SpriteGroup... groups) {
		System.out.println("gg");
		
		gameStateManager.activateOnly(gameStateManager.getGameState(4));
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

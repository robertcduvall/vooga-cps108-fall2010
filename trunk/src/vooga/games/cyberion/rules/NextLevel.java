package vooga.games.cyberion.rules;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.level.Rule;
import vooga.engine.overlay.Stat;
import vooga.engine.state.GameStateManager;
import vooga.engine.core.BetterSprite;

public class NextLevel implements Rule {

	private GameStateManager gameStateManager;

	//
	// public Dead(GameStateManager gameStateManager) {
	// this.gameStateManager = gameStateManager;
	// }

	public void setGameStateManager(GameStateManager gameStateManager) {
		this.gameStateManager = gameStateManager;
	}

	@Override
	public void enforce(SpriteGroup... groups) {
		// end game
		System.out.println("NEXT LEVEL!");
	}

	@Override
	public boolean isSatisfied(SpriteGroup... groups) {
		// TODO Auto-generated method stub
		for (Sprite tempSprite : groups[0].getSprites()) {
			if (tempSprite != null && tempSprite.isActive())
				return false;
		}
		return true;
	}

}

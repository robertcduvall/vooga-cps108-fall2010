package vooga.games.grandius.rules;

import vooga.engine.core.BetterSprite;
import vooga.engine.level.Rule;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class GameWonRule implements Rule{

	public GameWonRule() {
		
	}
	
	@Override
	public void enforce(SpriteGroup... groups) {
		System.out.println("You win");		
	}

	@Override
	public boolean isSatisfied(SpriteGroup ...groups) {
		SpriteGroup group = groups[0];
		for (Sprite s: group.getSprites()) {
			if (s!=null && s.isActive())
				return false;
		}
		return true;
	}

}

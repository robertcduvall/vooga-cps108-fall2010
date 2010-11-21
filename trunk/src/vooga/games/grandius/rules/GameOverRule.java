package vooga.games.grandius.rules;

import vooga.engine.core.BetterSprite;
import vooga.engine.level.Rule;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class GameOverRule implements Rule{

	public GameOverRule() {
		
	}
	
	@Override
	public void enforce(SpriteGroup... groups) {
		System.out.println("You lose");		
	}

	@Override
	public boolean isSatisfied(SpriteGroup ...groups) {
		SpriteGroup group = groups[0];
		for (Sprite s: group.getSprites()) {
			if (s!=null && s.isActive() && !(((BetterSprite)s).getStat("livesStat").getStat().equals(new Integer(0))))
				return false;
		}
		return true;
	}

}
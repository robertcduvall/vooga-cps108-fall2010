package vooga.games.doodlejump.rules;

import vooga.engine.core.BetterSprite;
import vooga.engine.level.Rule;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class LevelWonRule implements Rule{
	
	public LevelWonRule() {
		
	}
	
	@Override
	public void enforce(SpriteGroup... groups) {
		System.out.println("You lose");		
	}

	@Override
	public boolean isSatisfied(SpriteGroup ...groups) {
		SpriteGroup group = groups[0];
		for (Sprite s: group.getSprites()) {
			//TODO: check to see if doodleSprite's Score Stat = 1000
		}
		return true;
	}
}

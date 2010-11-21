package vooga.games.doodlejump.rules;

import vooga.engine.core.BetterSprite;
import vooga.engine.level.Rule;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class RocketBoostRule implements Rule{
	
	public RocketBoostRule() {
		
	}
	
	@Override
	public void enforce(SpriteGroup... groups) {
		System.out.println("You Won!");		
	}

	@Override
	public boolean isSatisfied(SpriteGroup ...groups) {
		SpriteGroup group = groups[0];
		for (Sprite s: group.getSprites()) {
			//TODO: Check to see if collision group is true, set doodle stat to true
		}
		return true;
	}
}
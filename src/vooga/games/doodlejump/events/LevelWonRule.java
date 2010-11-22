package vooga.games.doodlejump.events;

import vooga.engine.core.BetterSprite;
import vooga.engine.level.Rule;
import vooga.games.doodlejump.DoodleSprite;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class LevelWonRule implements Rule{
	
	public LevelWonRule() {
		
	}
	
	@Override
	public void enforce(SpriteGroup... groups) {
		//System.out.println("Level Complete");
		
	}

	@Override
	public boolean isSatisfied(SpriteGroup ...groups) {

		for (SpriteGroup group : groups) {
			if (group.getName().equals("doodleGroup")) {
				for (Sprite sprite : group.getSprites()) {
					return ((DoodleSprite) sprite).isLevelComplete();
				}
			}
		}
		return false;
	}
}

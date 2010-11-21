package vooga.games.galaxyinvaders;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.level.Rule;

public class LevelSwitchRule implements Rule{

	@Override
	public void enforce(SpriteGroup... groups) {
		DropThis.switchLevel();
	}

	@Override
	public boolean isSatisfied(SpriteGroup... groups) {
		return groups[0].getActiveSprite()!=null;
	}
	
	

}

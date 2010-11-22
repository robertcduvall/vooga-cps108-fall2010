package vooga.games.jumper.rules;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.level.Rule;

public class InfiniteBlocks implements Rule {

	
	@Override
	public void enforce(SpriteGroup... groups) {
		
	}

	@Override
	public boolean isSatisfied(SpriteGroup... groups) {
		return true;
	}
	
	

}

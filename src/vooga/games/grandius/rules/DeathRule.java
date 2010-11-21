package vooga.games.grandius.rules;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.level.Rule;

public class DeathRule implements Rule{

	@Override
	public void enforce(SpriteGroup... groups) {
	
		System.out.println("YES! RULE WOKRS!!!");		
	}

	@Override
	public boolean isSatisfied(SpriteGroup... groups) {

		return true;
	}

}

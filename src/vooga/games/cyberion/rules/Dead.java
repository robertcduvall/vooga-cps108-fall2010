package vooga.games.cyberion.rules;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.level.Rule;
import vooga.engine.overlay.Stat;
import vooga.engine.core.BetterSprite;

public class Dead implements Rule{

	@Override
	public void enforce(SpriteGroup... groups) {
		
	}

	@Override
	public boolean isSatisfied(SpriteGroup... groups) {
		
		BetterSprite tempSprite = (BetterSprite) groups[0].getSprites()[0];
		int tempInt = (Integer) tempSprite.getStat("livesStat").getStat();
		if (tempInt==0){
			return true;
		}
		return false;
	}
	
	

}

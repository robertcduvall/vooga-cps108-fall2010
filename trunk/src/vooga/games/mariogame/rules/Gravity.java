package vooga.games.mariogame.rules;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.level.Rule;

/**
 * Rule that applies gravity to the sprite groups listed in the level.xml document
 * 
 * @author Cameron McCallie
 *
 */
public class Gravity implements Rule{

	
	@Override
	public void enforce(SpriteGroup... groups) {
		for (SpriteGroup group : groups){
			applyGravity(group);
		}
	}

	@Override
	public boolean isSatisfied(SpriteGroup... groups) {
		return true;
	}
	
	private void applyGravity(SpriteGroup group) {
		for(Sprite s: group.getSprites()){
			if (s!=null)
				s.setVerticalSpeed(1.5);
		}	
	}

}

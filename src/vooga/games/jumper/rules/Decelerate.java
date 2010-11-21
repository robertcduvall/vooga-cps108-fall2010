package vooga.games.jumper.rules;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.level.Rule;
import vooga.engine.resource.Resources;

/**
 * Decelerate the doodle
 * 
 * @author Brian
 */
public class Decelerate implements Rule{

	private double DECELERATION_FACTOR = Resources.getDouble("DECELERATION_FACTOR");
	
	@Override
	public void enforce(SpriteGroup... groups) {
		for (SpriteGroup group: groups){
			decelerate(group);
		}
	}

	@Override
	public boolean isSatisfied(SpriteGroup... groups) {
		return true;
	}
	
	private void decelerate(SpriteGroup group) {
		for(Sprite s: group.getSprites()){
			if (s!=null)
				s.setY(s.getHorizontalSpeed() - DECELERATION_FACTOR);
		}
		
	}

}

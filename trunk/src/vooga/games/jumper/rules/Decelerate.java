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
			if (s!=null){
				double horizontalSpeed = s.getHorizontalSpeed();
				if (horizontalSpeed > 0)
					s.setHorizontalSpeed(s.getHorizontalSpeed() - DECELERATION_FACTOR);					
				if (horizontalSpeed < 0)
					s.setHorizontalSpeed(s.getHorizontalSpeed() + DECELERATION_FACTOR);
				if (horizontalSpeed > -1 || horizontalSpeed < 1)
					s.setHorizontalSpeed(0);
			}
		}
	}

}

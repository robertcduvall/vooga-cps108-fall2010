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
	/** set type SpriteGroup to be enforced by this rule.
	 * @param any number of type SpriteGroup
	 */
	public void enforce(SpriteGroup... groups) {
		for (SpriteGroup group: groups){
			decelerate(group);
		}
	}

	@Override
	/**
	 * Check to see if the rule is satisfied.
	 * @param any number of type SpriteGroup
	 * @return true if the rule is actively triggered for
	 * the SpriteGroup or SpriteGroups.
	 */
	public boolean isSatisfied(SpriteGroup... groups) {
		return true;
	}
	
	/**
	 * Action triggered by the rule.  Slows the speed of the
	 * input SpriteGroup
	 * @param group
	 */
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

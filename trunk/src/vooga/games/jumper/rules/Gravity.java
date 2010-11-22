package vooga.games.jumper.rules;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.level.Rule;
import vooga.engine.resource.Resources;

/**
 * Apply gravity to the doodle
 * 
 * @author Brian
 */
public class Gravity implements Rule {

	private double MAX_SPEED = Resources.getDouble("MAX_SPEED");
	private double GRAVITY = Resources.getDouble("GRAVITY");
	
	/** set type SpriteGroup to be enforced by this rule.
	 * @param any number of type SpriteGroup
	 */

	@Override
	public void enforce(SpriteGroup... groups) {
		for (SpriteGroup group : groups) {
			applyGravity(group);
		}
	}

	/**
	 * Check to see if the rule is satisfied.
	 * 
	 * @param any number of type SpriteGroup
	 * @return always returns true because gravity is consistently present
	 */

	@Override
	public boolean isSatisfied(SpriteGroup... groups) {
		return true;
	}

	/**
	 * Applies gravity to sprites in the spritegroup
	 * 
	 * @param type
	 *            SpriteGroup
	 * 
	 */

	private void applyGravity(SpriteGroup group) {

		for (Sprite s : group.getSprites()) {

			if (s != null && s.getVerticalSpeed() < MAX_SPEED) {
				s.setVerticalSpeed(s.getVerticalSpeed() + GRAVITY);
			}
		}

	}
}

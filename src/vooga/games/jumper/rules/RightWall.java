package vooga.games.jumper.rules;

import com.golden.gamedev.object.SpriteGroup;

import com.golden.gamedev.object.Sprite;

import vooga.engine.level.Rule;
import vooga.engine.resource.Resources;

/**
 * Allow DoodleSprite to move through the right wall
 * 
 * @author Cody
 */
public class RightWall implements Rule {
	
	private final int LEFT_WALL_POSITION = 0;
	private final int RIGHT_WALL_POSITION = Resources.getInt("gameWidth");
	
	/** set type SpriteGroup to be enforced by this rule.
	 * @param any number of type SpriteGroup
	 */


	@Override
	public void enforce(SpriteGroup... groups) {
		for (SpriteGroup group: groups) {
			reposition(group);
		}
	}
	
	/**
	 * Sets the sprites' x value to left wall
	 * 
	 * @param type SpriteGroup
	 * 
	 */

	public void reposition(SpriteGroup groups) {
		for(Sprite sprite: groups.getSprites()){
			if (sprite!=null)
				sprite.setX(LEFT_WALL_POSITION - sprite.getWidth());
		}
		
	}
	
	/**
	 * Check to see if the rule is satisfied.
	 * 
	 * @param any number of type SpriteGroup
	 * @return true if sprite from sprites hit the right wall
	 */

	@Override
	public boolean isSatisfied(SpriteGroup... groups) {
		for (SpriteGroup group : groups) {
			for (Sprite s : group.getSprites()) {
				if (s!=null && s.getX() > RIGHT_WALL_POSITION) {
					return true;
				}
			}
		}
		
		return false;
	}

}

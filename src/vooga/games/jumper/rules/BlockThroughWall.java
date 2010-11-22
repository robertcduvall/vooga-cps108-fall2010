package vooga.games.jumper.rules;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.level.Rule;
import vooga.engine.resource.Resources;

public class BlockThroughWall implements Rule{

	protected int THIS_WALL_POSITION;
	protected int OTHER_WALL_POSITION; 

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
	 * Sets the sprites' x value to other wall
	 * @param type SpriteGroup
	 */

	public void reposition(SpriteGroup groups) {
		for(Sprite sprite: groups.getSprites()){
			if (sprite!=null)
				sprite.setX(OTHER_WALL_POSITION);
		}
		
	}
	
	/**
	 * Check to see if the rule is satisfied.
	 * 
	 * @param any number of type SpriteGroup
	 * @return true if sprite from sprites hit this wall
	 */

	@Override
	public boolean isSatisfied(SpriteGroup... groups) {
		for (SpriteGroup group : groups) {
			for (Sprite s : group.getSprites()) {
				if (s!=null && (s.getX() + s.getWidth()) < THIS_WALL_POSITION) {
					return true;
				}
			}
		}
		
		return false;
	}

}

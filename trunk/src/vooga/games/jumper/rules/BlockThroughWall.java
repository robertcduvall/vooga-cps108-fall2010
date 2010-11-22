package vooga.games.jumper.rules;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.level.Rule;
import vooga.engine.resource.Resources;


/**
 * Superclass for RightWall and LeftWall.  Contains all the functionality
 * to send blocks to the other wall if they go through a side wall.
 * RightWall and LeftWall should be phased out, they only contain two 
 * fields.
 * @author Devsno
 *
 */
public class BlockThroughWall implements Rule{


	protected int RIGHT_WALL_POSITION = Resources.getInt("gameWidth");
	protected int LEFT_WALL_POSITION = 0; 

	protected int THIS_WALL_POSITION = RIGHT_WALL_POSITION;
	protected int OTHER_WALL_POSITION = 0; 
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

	/*
	 * Needs to be changed to work with both wall locations.
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
	 * @return true if sprite from sprites hit EITHER wall.
	 */

	@Override
	public boolean isSatisfied(SpriteGroup... groups) {
		for (SpriteGroup group : groups) {
			for (Sprite s : group.getSprites()) {
				if (	s!=null && 
						(((s.getX() + s.getWidth()) < LEFT_WALL_POSITION ) || (s.getX() > RIGHT_WALL_POSITION))) {
					return true;
				}
			}
		}
		
		return false;
	}

}

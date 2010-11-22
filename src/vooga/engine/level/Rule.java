package vooga.engine.level;

import com.golden.gamedev.object.SpriteGroup;

/**
 * This interface allows Rules to be created for games.  The developer can add sprite groups
 * to apply the rule to and check to see if the rule is satisfied for specific sprite groups.
 * 
 * The point of a rule is to call an action when certain criteria are met. The action called
 * must be defined by the developer.
 * @author Devon Townsend
 *
 */
public interface Rule {
	
	/**
	 * add SpriteGroups that this Rule applies to
	 * @param groups
	 */
	public void enforce(SpriteGroup...groups);
	
	/**
	 * 
	 * @param SpriteGroup... groups
	 * @return whether the rule is involved for the given SpriteGroups.  This
	 * does not mean that the sprite groups are enforced by the rule, rather
	 * that the rule is activated. 
	 */
	public boolean isSatisfied(SpriteGroup...groups);
}

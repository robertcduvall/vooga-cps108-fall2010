package vooga.games.jumper.rules;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.level.Rule;
import vooga.engine.resource.Resources;

/**
 * Bounce BlockSprite off wall if it touches the side
 * 
 * @author Brian
 */
public class WallBounce implements Rule{

	private int GAME_WIDTH = Resources.getInt("gameWidth");
	
	/** set type SpriteGroup to be enforced by this rule.
	 * @param any number of type SpriteGroup
	 */
	
	@Override
	public void enforce(SpriteGroup... groups) {
		for (SpriteGroup group: groups){
			adjustForWall(group);
		}
	}
	
	/**
	 * Check to see if the rule is satisfied.
	 * 
	 * @param any number of type SpriteGroup
	 * @return true if sprite from sprite group hits any wall
	 */

	@Override
	public boolean isSatisfied(SpriteGroup... groups) {
		for (SpriteGroup group: groups){
			for (Sprite s: group.getSprites()) {
	            if (s!=null && (s.getY() > GAME_WIDTH ||  s.getY() < 0)){
	                return true;
	            }
	        }
		}
        return false;
	}
	
	/**
	 * Sets the sprites' x velocity to reverse
	 * 
	 * @param type SpriteGroup
	 * 
	 */
	
	private void adjustForWall(SpriteGroup group) {
		for(Sprite s: group.getSprites()){
			if (s!=null){
				s.setHorizontalSpeed(s.getHorizontalSpeed() * -1);
			}
		}
		
	}

}

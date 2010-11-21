package vooga.games.jumper.rules;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.level.Rule;
import vooga.engine.resource.Resources;

/**
 * Make sure the doodle doesn't fall out of the screen from below
 * 
 * @author Brian
 */
public class Floor implements Rule{

	private int FLOOR_HEIGHT = Resources.getInt("gameHeight") - Resources.getInt("distanceFromFloorToBottom");
	
	@Override
	public void enforce(SpriteGroup... groups) {
		for (SpriteGroup group: groups){
			adjustForFloor(group);
		}
	}

	@Override
	public boolean isSatisfied(SpriteGroup... groups) {
		for (SpriteGroup group: groups){
			for (Sprite s: group.getSprites()) {
	            if (s!=null && s.getY() > FLOOR_HEIGHT){
	                return true;
	            }
	        }
		}
        return false;
	}
	
	private void adjustForFloor(SpriteGroup group) {
		for(Sprite s: group.getSprites()){
			if (s!=null)
				s.setY(FLOOR_HEIGHT);
		}
		
	}

}

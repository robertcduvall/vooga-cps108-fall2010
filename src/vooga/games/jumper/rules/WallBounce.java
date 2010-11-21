package vooga.games.jumper.rules;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.level.Rule;
import vooga.engine.resource.Resources;

/**
 * Bounce blocks off walls
 * 
 * @author Brian
 */
public class WallBounce implements Rule{

	private int GAME_WIDTH = Resources.getInt("gameWidth");
	
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
	            if (s!=null && (s.getY() > GAME_WIDTH ||  s.getY() < 0)){
	                return true;
	            }
	        }
		}
        return false;
	}
	
	private void adjustForFloor(SpriteGroup group) {
		for(Sprite s: group.getSprites()){
			if (s!=null)
				s.setHorizontalSpeed(s.getHorizontalSpeed() * -1);
		}
		
	}

}

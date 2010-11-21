package vooga.games.jumper.rules;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.level.Rule;

public class Floor implements Rule{

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
	            if (s!=null && s.getY() > 500){
	                return true;
	            }
	        }
		}
        return false;
	}
	
	private void adjustForFloor(SpriteGroup group) {
		for(Sprite s: group.getSprites()){
			if (s!=null)
				s.setY(500);
		}
		
	}

}

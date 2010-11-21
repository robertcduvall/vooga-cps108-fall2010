package vooga.games.jumper.rules;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.level.Rule;

public class Gravity implements Rule{

	@Override
	public void enforce(SpriteGroup... groups) {
		for (SpriteGroup group: groups){
			applyGravity(group);
		}
		System.out.println("gravity");
	}

	private void applyGravity(SpriteGroup group) {
		for(Sprite s: group.getSprites()){
			if (s!=null)
				s.setVerticalSpeed(2);
		}
		
	}

	@Override
	public boolean isSatisfied(SpriteGroup... groups) {
		return true;
	}

}

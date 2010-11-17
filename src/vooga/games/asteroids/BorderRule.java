package vooga.games.asteroids;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import vooga.engine.level.Rule;

public class BorderRule implements Rule{

	private SpriteGroup group;
	
	@Override
	public void enforce() {
		for (Sprite sprite : group.getSprites()){
			if (sprite.getX()>=800)
				sprite.setX(0);
			else if (sprite.getX()<=0)
				sprite.setX(800);
			else if (sprite.getY()>=600)
				sprite.setY(0);
			else if (sprite.getY()<=0)
				sprite.setY(600);
		}
	}
	

}

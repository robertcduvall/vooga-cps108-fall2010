package vooga.games.asteroids;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import vooga.engine.level.Rule;

public class BorderRule implements Rule {
	//TODO remove hard-coded resolution
	
	@Override
	public void enforce(SpriteGroup... groups) {
		for (SpriteGroup g : groups) {
			for (Sprite sprite : g.getSprites()) {
				if (sprite.getX() >= 800)
					sprite.setX(0);
				else if (sprite.getX() <= 0)
					sprite.setX(800);
				else if (sprite.getY() >= 600)
					sprite.setY(0);
				else if (sprite.getY() <= 0)
					sprite.setY(600);
			}
		}
	}

	@Override
	public boolean isSatisfied(SpriteGroup... groups) {
		for (SpriteGroup g : groups) {
			for (Sprite sprite : g.getSprites()) {
				if (sprite.getX() >= 800 || sprite.getX() <= 0
						|| sprite.getY() >= 600 || sprite.getY() <= 0)
					return false;
			}
		}
		return true;
	}

}

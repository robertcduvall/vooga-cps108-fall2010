package vooga.engine.level;

import com.golden.gamedev.object.SpriteGroup;

public interface Rule {
	public void enforce(SpriteGroup...groups);
	
	public boolean isSatisfied(SpriteGroup...groups);
}

package vooga.engine.level;

import com.golden.gamedev.object.SpriteGroup;

public interface RuleInterface {

	public boolean checkRule(SpriteGroup...groups);
	public void enforce(SpriteGroup...groups);
	
}

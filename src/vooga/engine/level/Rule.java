package vooga.engine.level;

import com.golden.gamedev.object.SpriteGroup;

/**
 * 
 * @author Yijia Mu, Cameron McCallie, John Kline
 *
 */

public interface Rule {

	public boolean checkRule(SpriteGroup...groups);
	public void enforceRule(SpriteGroup...groups);
	
	
}

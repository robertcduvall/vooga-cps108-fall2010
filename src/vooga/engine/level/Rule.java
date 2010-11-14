package vooga.engine.level;

import com.golden.gamedev.object.SpriteGroup;

/**
 * 
 * @author Yijia Mu, Cameron McCallie, John Kline
 *
 */

public interface Rule {

	public boolean checkRule(SpriteGroup group1, SpriteGroup group2);
	public void enforceRule(SpriteGroup group1, SpriteGroup group2);
	
	
}

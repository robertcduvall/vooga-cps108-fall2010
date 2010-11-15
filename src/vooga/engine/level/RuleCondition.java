package vooga.engine.level;

import java.util.List;
import vooga.engine.core.*;

public abstract class RuleCondition {

	/**
	 * This class provides a framework to add conditions to rules
	 */
	List<GameEntitySprite> spritesAffected;
	
	
	
	/**
	 * constructor
	 */
	public RuleCondition () {
		
	}
	
	/**
	 * adds a single GameEntitySprite to the List spritesAffected by the rule.
	 * @param ges
	 */
	public void addSpriteAffected (GameEntitySprite ges) {
		spritesAffected.add(ges);
	}
	
	
	
	
	
	
}

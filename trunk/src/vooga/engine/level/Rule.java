package vooga.engine.level;

import com.golden.gamedev.object.SpriteGroup;

/**
 * This class is the super class for the rules to be defined for each level. There are some basic abstract methods for
 * the rules, and also some useful non-abstract methods that I think should be helpful for rules, but don't necessarily 
 * need to be implemented. 
 * @author Jiaqi Yan
 *
 */
public abstract class Rule {
	public Rule() {
	}
	
	/**
	 * Checks if the rule is satisfied
	 * @return
	 */
	public boolean ruleSatisfied() {
		return true;
	}
	/**
	 * Checks if the rule is satisfied for given SpriteGroups
	 * @param group
	 * @return
	 */
	public abstract boolean ruleSatisfied(SpriteGroup... group);
	
	/**
	 * What should happen after the rule is satisfied. 
	 */
	public void enforceRule() {
	}
	
	/**
	 * 
	 * @param group
	 */
	public abstract void enforceRule(SpriteGroup... group);

	public boolean LevelFail() {
		return false;
	}

	public boolean LevelProceed() {
		return false;
	}

	public void setLevelOverCondition() {
	}

	public void setLevelProceeedCondition() {
	}

	/**
	 * Override this method when you need to change the specific rule.
	 */
	public void updateRule() {
	}

	public void autoUpdateBetweenLevels() {
	}
	
	public void autoUpdateWithinLevel(){
	}
	
	public void autoUpdateUnderCondition(){
	}
	
	public void setUpdateCondition(){
	}
	
	public void setGameOverCondition() {
	}
	public void setGameWonCondition(){
	}

	public boolean GameLost() {
		return false;
	}

	public boolean GameWon() {
		return false;
	}
}

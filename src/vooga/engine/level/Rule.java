package vooga.engine.level;

import com.golden.gamedev.object.SpriteGroup;

/**
 * This class is the super class for the rules to be defined for each level. There are some basic abstract methods for
 * the rules, and also a lot of useful non-abstract methods that I think should be helpful for rules, but don't 
 * necessarily need to be implemented. 
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
	 * What should happpen according to whether the rule is satisfied. 
	 */
	public void enforceRule(boolean ruleSatisfied) {
	}
	
	/**
	 * What should happpen according to whether the rule is satisfied.
	 * @param group
	 */
	public abstract void enforceRule(boolean ruleSatisfied, SpriteGroup... group);
	/**
	 * Whether the level should fail 
	 * @return
	 */
	public boolean LevelFail() {
		return false;
	}
	/**
	 * Whether the level should proceed
	 * @return
	 */
	public boolean LevelProceed() {
		return false;
	}
	/**
	 * Set the condition for Level to fail
	 */
	public void setLevelOverCondition() {
	}

	/**
	 * Set the condition for the level to proceed
	 */
	public void setLevelProceeedCondition() {
	}
	
	/**
	 * Auto-update the rule when level proceed
	 */
	public void autoUpdateBetweenLevels() {
	}
	
	/**
	 * Auto-update the rule within the level (each frame/update)
	 */
	public void autoUpdateWithinLevel(){
	}
	
	/**
	 * Auto-update the rule when certain condition is met
	 */
	public void autoUpdateUnderCondition(){
	}
	
	/**
	 * The condition for the rule to auto-update
	 */
	public void setUpdateCondition(){
	}
	/**
	 * Set the condition for the game over
	 */
	public void setGameOverCondition() {
	}
	
	/**
	 * Set the condition for the game win 
	 */
	public void setGameWonCondition(){
	}
	
	/**
	 * Whether the game is lost
	 * @return
	 */
	public boolean GameLost() {
		return false;
	}
	
	/**
	 * Whether the game is won
	 * @return
	 */
	public boolean GameWon() {
		return false;
	}
}

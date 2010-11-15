package vooga.engine.level;

import com.golden.gamedev.object.SpriteGroup;

public abstract class Rule {
	
	public Rule() {
	}

	public boolean ruleSatisfied() {
		return true;
	}

	public abstract boolean ruleSatisfied(SpriteGroup... group);

	public void enforceRule() {
	}

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

	public boolean GameLost() {
		return false;
	}

	public boolean GameWon() {
		return false;
	}
}

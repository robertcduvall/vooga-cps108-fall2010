package vooga.engine.level;

import com.golden.gamedev.object.SpriteGroup;

public abstract class Rule {
	//private Condition[] conditions;
	
	public Rule(){
	}	
	
	public boolean ruleSatisfied(){
		return true;
	}
	
	public abstract boolean ruleSatisfied(SpriteGroup...group);
	
	public void enforceRule(){
	}
	
	public abstract void enforceRule(SpriteGroup...group);
	
	public abstract void checkRule();
	
	public abstract void setGoals();
	
	public abstract boolean LevelFail();
	
	public abstract boolean LevelProceed();
	
	public abstract void setLevelOverCondition();
	
	public abstract void setLevelProceeedCondition();
	
	/**
	 * Override this method when you need to change the specific rule.
	 */
	public void changeRule(){
		
	}
	
	public void setGameOverCondition(){
		
	}
	

}

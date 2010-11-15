package vooga.engine.level;

import com.golden.gamedev.object.SpriteGroup;

public abstract class Rule {
	private Condition[] conditions;
	
	public Rule(){
	}	
	
	public abstract void checkRules();

	public abstract void enforce();
	
	public abstract void setGoals();
	
	public void changeRule(){
	}
	
	public void addGroup(SpriteGroup g){
		
	}
	
	
}

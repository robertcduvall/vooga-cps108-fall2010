package vooga.engine.level;

import vooga.engine.core.*;
import java.util.*;
import com.golden.gamedev.object.SpriteGroup;

/**
 * I think it's unnecessary to reinitialize the Rule Collection class for each level. Hence we need some methods for 
 * adding/removing/changing the rules 
 * @author Jiaqi Yan
 *
 */

public class RuleCollection extends Playfield{
	HashMap<String,Rule> ruleMap = new HashMap<String,Rule>();
	
	
	public void addRule(String s,Rule r){
		ruleMap.put(s,r);
	}
	public void deleteRule(String s){
		ruleMap.remove(s);
	}
	public void changeRule(String s){
		ruleMap.get(s).changeRule();
	}
	
	//public void addSpriteGroup(SpriteGroup group){
	//	this.addGroup(group);	
	//}
	
	public void enforceRules(){
		for(Rule r:ruleMap.values()){
			r.enforce();
		}
	}
}

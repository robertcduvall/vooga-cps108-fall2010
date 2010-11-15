package vooga.engine.level;

import java.util.HashMap;
/**
 * Initializes the rules and passes them between levels so that the rules don't need to be reinialized every time
 * a new level is created.  
 * @author Jiaqi Yan
 *
 */
public class RulesCollection {
	private HashMap<String,Rule> ruleMap;
	public void addRule(String n,Rule r){
		ruleMap.put(n,r);
	}
	public void deleteRule(String s){
		ruleMap.remove(s);
	}
	public void deleteRule(Rule r){
		ruleMap.remove(r);
	}
	public void updateRule(String n,Rule r){
		ruleMap.put(n, r);
	}
	public void autoUpdateBetweenLevels(String n){
		ruleMap.get(n).autoUpdateBetweenLevels();
	}
	public HashMap<String,Rule> getRules(){
		return ruleMap;
	}
}

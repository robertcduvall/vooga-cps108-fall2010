package vooga.engine.level;

import java.util.HashMap;

import com.golden.gamedev.object.SpriteGroup;

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
	public void autoUpdateRule(String n){
		ruleMap.get(n).autoUpdateRule();
	}
	public HashMap<String,Rule> getRules(){
		return ruleMap;
	}
}

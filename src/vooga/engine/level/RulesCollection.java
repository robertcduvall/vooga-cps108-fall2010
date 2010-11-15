package vooga.engine.level;

import java.util.HashMap;

/**
 * Initializes the rules and passes them between levels so that the rules don't
 * need to be reinialized every time a new level is created.
 * 
 * @author Jiaqi Yan
 * 
 */
public class RulesCollection {
	private HashMap<String, Rule> ruleMap;

	/**
	 * add a rule to the collection
	 * 
	 * @param n
	 * @param r
	 */
	public void addRule(String n, Rule r) {
		ruleMap.put(n, r);
	}

	/**
	 * delete a rule from the collection
	 * 
	 * @param s
	 */
	public void deleteRule(String s) {
		ruleMap.remove(s);
	}

	/**
	 * delete a rule from the collection
	 * 
	 * @param r
	 */
	public void deleteRule(Rule r) {
		ruleMap.remove(r);
	}

	/**
	 * update an existing rule
	 * 
	 * @param n
	 * @param r
	 */
	public void updateRule(String n, Rule r) {
		ruleMap.put(n, r);
	}

	/**
	 * auto update the rules when level proceeds
	 * 
	 * @param n
	 */
	public void autoUpdateBetweenLevels(String n) {
		ruleMap.get(n).autoUpdateBetweenLevels();
	}

	/**
	 * return the map of the rules
	 * 
	 * @return
	 */
	public HashMap<String, Rule> getRules() {
		return ruleMap;
	}
}

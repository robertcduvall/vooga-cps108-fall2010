package vooga.engine.level;

import java.util.*;

import vooga.engine.overlay.OverlayTracker;
import vooga.engine.resource.Resources;
import vooga.engine.event.*;


import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.PlayField;

/**
 * This class is a PlayField for the level that has all the rules built in, and
 * overrides the update method to handle the updating of all the rules. It also
 * decides whether the level/game should proceed or whether it's failed.
 * <xmp>
 * 
 *</xmp>
 * @author Jiaqi Yan
 * 
 */
public class LevelField extends PlayField {
	private HashMap<String, Rule> ruleMap = new HashMap<String, Rule>();
	private HashMap<Rule, SpriteGroup[]> spriteGroupMap = new HashMap<Rule, SpriteGroup[]>();
	private HashMap<Rule, Boolean> activatedRule = new HashMap<Rule, Boolean>();
	private String myMusic;
	private boolean LevelFailed;
	private boolean LevelProceed;
	private boolean GameLost;
	private boolean GameWon;
	private GameWonConditions gameWonConditions;
	private GameLostConditions gameLostConditions;
	private LevelProceedConditions levelProceedConditions;
	private LevelLostConditions levelLostConditions;
	
	private EventPool eventPool;
	
	
	

	public LevelField() {
		super();
	}

	public LevelField(Background background) {
		super(background);
	}

	/**
	 * Initializes the rules of the current level from the specified
	 * RulesCollection class.
	 * 
	 * @param rc
	 */
	public void initializeRules(RulesCollection rc) {
		ruleMap = rc.getRules();
	}
	
	public void initializeEvents(EventPool eventPool){
		
	}
	
	public void addEvent(IEventHandler event){
		eventPool.addEvent(event);
	}
	public void removeEvent(IEventHandler event){
		eventPool.removeEvent(event);
	}
	
	
	/**
	 * Initialize the rules from the specified rule map.
	 * 
	 * @param rm
	 */
	public void initializeRules(HashMap<String, Rule> rm) {
		ruleMap = rm;
	}

	/**
	 * Initialize the conditions related to the game/level state
	 * 
	 * @param gamewon
	 * @param gameLost
	 * @param levelProceed
	 * @param levelLost
	 */
	public void initializeConditions(GameWonConditions gamewon,
			GameLostConditions gameLost, LevelProceedConditions levelProceed,
			LevelLostConditions levelLost) {
		gameWonConditions = gamewon;
		gameLostConditions = gameLost;
		levelProceedConditions = levelProceed;
		levelLostConditions = levelLost;
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		actOnRules();
		checkLevelConditions();
	}

	/**
	 * Get all the SpriteGroups the rules are specifying
	 * 
	 * @return
	 */
	public ArrayList<SpriteGroup> getRuleSpriteGroups() {
		ArrayList<SpriteGroup> c = new ArrayList<SpriteGroup>();
		for (SpriteGroup[] g : spriteGroupMap.values()) {
			for (int i = 0; i < g.length; i++) {
				c.add(g[i]);
			}
		}
		return c;
	}

	/**
	 * Get the SpriteGroups related to a specified rule.
	 * 
	 * @param r
	 * @return
	 */
	public SpriteGroup[] getRuleSpriteGroup(Rule r) {
		return spriteGroupMap.get(r);
	}

	/**
	 * check for the conditions that decide the state of the level
	 */
	public void checkLevelConditions() {
		for (Rule r : ruleMap.values()) {
			if (r.LevelFail())
				LevelFailed = true;
			if (r.LevelProceed())
				LevelProceed = true;
			if (r.GameLost())
				GameLost = true;
			if (r.GameWon())
				GameWon = true;
		}
		if (gameWonConditions.GameWon())
			GameWon = true;
		if (gameLostConditions.GameLost())
			GameLost = true;
		if (levelProceedConditions.LevelProceed())
			LevelProceed = true;
		if (levelLostConditions.LevelLost())
			LevelFailed = true;
	}

	/**
	 * Whether the level is lost
	 * 
	 * @return
	 */
	public boolean LevelIsLost() {
		return LevelFailed;
	}

	/**
	 * Whether the level is won
	 * 
	 * @return
	 */
	public boolean LevelShouldProceed() {
		return LevelProceed;
	}

	/**
	 * Whether the game is won
	 * 
	 * @return
	 */
	public boolean GameWon() {
		return GameWon;
	}

	/**
	 * Whether the game is lost
	 * 
	 * @return
	 */
	public boolean GameOver() {
		return GameLost;
	}

	/**
	 * return the levelField for the next level and consequenctly updates the
	 * rules
	 * 
	 * @return
	 */
	public LevelField getNextLevelField() {
		LevelField nextField = new LevelField();
		HashMap<String, Rule> nextRuleMap = new HashMap<String, Rule>();
		nextRuleMap = ruleMap;
		for (Rule r : nextRuleMap.values()) {
			r.autoUpdateBetweenLevels();
		}
		nextField.initializeRules(nextRuleMap);
		return nextField;
	}

	/**
	 * Adds OverlayTracker to the playfield.
	 * 
	 * @param overlayTracker
	 */
	/*public void addOverlayTracker(OverlayTracker overlayTracker) {
		addOverlayGroups(overlayTracker.getOverlayGroup("s"));
	}
	
	public void addOverlayGroup(OverlayTracker String s){
		this.addGroup(getOverlayGroup(s));
	}*/

	/**
	 * Adds every SpriteGroup from the OverlayTracker to the playfield.
	 * 
	 * @param overlayGroups
	 */
	private void addOverlayGroups(List<SpriteGroup> overlayGroups) {
		for (SpriteGroup overlay : overlayGroups) {
			this.addGroup(overlay);
		}
	}

	/**
	 * Gives the current PlayField music
	 * 
	 * @param key
	 */
	private void addMusic(String key) {
		myMusic = Resources.getSound(key);
	}

	/**
	 * add a spriteGroup to a rule
	 * 
	 * @param r
	 * @param g
	 */
	public void addSpriteGroup(Rule r, SpriteGroup g) {
		SpriteGroup[] group = spriteGroupMap.get(r);
		SpriteGroup[] newGroup = new SpriteGroup[group.length];
		for (int i = 0; i < group.length; i++) {
			newGroup[i] = group[i];
		}
		newGroup[group.length] = g;
		spriteGroupMap.put(r, newGroup);
	}

	/**
	 * This method checks if a Rule is applied
	 */
	private void actOnRules() {
		for (Rule rule : ruleMap.values()) {
			SpriteGroup[] obedients = spriteGroupMap.get(rule);
			if (activatedRule.get(rule)) {
				rule.enforceRule(rule.ruleSatisfied(obedients), obedients);
			}
		}
	}

	/**
	 * Activate a rule for this specific level
	 * 
	 * @param r
	 */
	public void activateRule(String s) {
		activatedRule.put(ruleMap.get(s), true);
	}

	/**
	 * Activate a rule for this specific level
	 * 
	 * @param r
	 */
	public void activaterule(Rule r) {
		activatedRule.put(r, true);
	}

	/**
	 * Deactivate a rule for this specific level
	 * 
	 * @param r
	 */
	public void deactivateRule(String s) {
		activatedRule.put(ruleMap.get(s), false);
	}

	/**
	 * Deactivate a rule for this specific level
	 * 
	 * @param r
	 */
	public void deactivateRule(Rule r) {
		activatedRule.put(r, true);
	}

	/**
	 * add a rule to the current level, together with its associated
	 * 
	 * @param rulename
	 * @param rule
	 * @param obedients
	 */
	public void addRule(String rulename, Rule rule, SpriteGroup[] obedients) {
		ruleMap.put(rulename, rule);
		spriteGroupMap.put(rule, obedients);
		activatedRule.put(rule, false);
	}

	/**
	 * Add a rule to the current level
	 * 
	 * @param n
	 * @param r
	 */
	public void addRule(String n, Rule r) {
		ruleMap.put(n, r);
		spriteGroupMap.put(r, new SpriteGroup[0]);
		activatedRule.put(r, false);
	}
}

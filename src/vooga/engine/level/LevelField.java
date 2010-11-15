package vooga.engine.level;

import java.util.*;

import vooga.engine.overlay.OverlayTracker;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.PlayField;
/**
 * 
 * @author Jiaqi Yan
 *
 */
public class LevelField extends PlayField{
	private HashMap<String,Rule> ruleMap = new HashMap<String, Rule>();
	private HashMap<Rule,SpriteGroup[]> spriteGroupMap = new HashMap<Rule,SpriteGroup[]>();
	private HashMap<Rule,Boolean> activatedRule = new HashMap<Rule,Boolean>();
	private String myMusic;
	private boolean LevelFailed;
	private boolean LevelProceed;
	private boolean GameLost;
	private boolean GameWon;
	
	public LevelField(){
		super();
	}
	public LevelField(Background background){
		super(background);
	}
	
	public void initializeRules(RulesCollection rc){
		ruleMap = rc.getRules();
	}
	
	public void initializeRules(HashMap<String,Rule> rm){
		ruleMap = rm;
	}
	
	
	
	@Override
	public void update(long elapsedTime)
	{
		super.update(elapsedTime);
		actOnRules();
	}
	
	public ArrayList<SpriteGroup> getRuleSpriteGroups(){
		ArrayList<SpriteGroup> c = new ArrayList<SpriteGroup>();
		for(SpriteGroup[] g: spriteGroupMap.values()){
			for(int i=0;i<g.length;i++){
				c.add(g[i]);
			}
		}
		return c;
	}
	
	public void checkLevelConditions(){
		for (Rule r: ruleMap.values()){
			LevelFailed = r.LevelFail();
			LevelProceed = r.LevelProceed();
			GameLost = r.GameLost();
			GameWon = r.GameWon();
		}
	}
	
	public boolean LevelIsLost(){
		return LevelFailed;
	}
	
	public boolean LevelShouldProceed(){
		return LevelProceed;
	}
	
	public boolean GameWon(){
		return  GameWon;
	}
	
	public boolean GameOver(){
		return GameLost;
	}
	
	public LevelField getNextLevelField(){
		LevelField nextField = new LevelField();
		HashMap<String,Rule> nextRuleMap = new HashMap<String,Rule>();
		nextRuleMap = ruleMap;
		for(Rule r:nextRuleMap.values()){
			r.autoUpdateBetweenLevels();
		}
		nextField.initializeRules(nextRuleMap);
		return nextField;
	}
	
	
	/**
	 * Adds OverlayTracker to the playfield.
	 * @param overlayTracker
	 */
	public void addOverlayTracker(OverlayTracker overlayTracker){
		addOverlayGroups(overlayTracker.getOverlayGroups());
	}

	/**
	 * Adds every SpriteGroup from the OverlayTracker to the playfield.
	 * @param overlayGroups
	 */
	private void addOverlayGroups(List<SpriteGroup> overlayGroups) {
		for(SpriteGroup overlay : overlayGroups){
			this.addGroup(overlay);
		}
	}
	
	/**
	 * Gives the current PlayField music
	 * @param key
	 */
	private void addMusic(String key) {
		myMusic = Resources.getSound(key);
	}
	
	public void addSpriteGroup(Rule r,SpriteGroup g){
		SpriteGroup[] group = spriteGroupMap.get(r);
		SpriteGroup[] newGroup = new SpriteGroup[group.length];
		for(int i=0;i<group.length;i++){
			newGroup[i]=group[i];
		}
		newGroup[group.length] = g;
		spriteGroupMap.put(r, newGroup);
	}
	
	/**
	 * This method checks if a Rule is applied 
	 */
	private void actOnRules() {		
		for (Rule rule:ruleMap.values()){
			SpriteGroup[] obedients = spriteGroupMap.get(rule);
			if(activatedRule.get(rule))
			{
				rule.enforceRule(rule.ruleSatisfied(obedients),obedients);
			}
		}
	}
	
	public void activateRule(Rule r){
		activatedRule.put(r, true);
	}
	public void deactivateRule(Rule r){
		activatedRule.put(r, false);
	}
	
	public void addRule(String rulename, Rule rule, SpriteGroup[] obedients)
	{
		ruleMap.put(rulename, rule);
		spriteGroupMap.put(rule, obedients);
	}
	public void addRule(String n,Rule r){
		ruleMap.put(n,r);
	}
}

package vooga.engine.level;

import java.util.HashMap;
import java.util.List;

import vooga.engine.overlay.OverlayTracker;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.PlayField;

public class LevelField extends PlayField{
	HashMap<String,Rule> ruleMap;
	HashMap<Rule,SpriteGroup[]> spriteGroupMap;
	private String myMusic;
	
	public LevelField(Background background){
		super(background);
		ruleMap = new HashMap<String, Rule>();
		spriteGroupMap = new HashMap<Rule, SpriteGroup[]>();
	}
	public LevelField(){
		super();
	}
	
	@Override
	public void update(long elapsedTime)
	{
		super.update(elapsedTime);
		actOnRules();
		
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
		for(String key: ruleMap.keySet())
		{
			Rule rule = ruleMap.get(key);
			SpriteGroup[] obedients = spriteGroupMap.get(rule);
	
			if(rule.ruleSatisfied(obedients))
			{
				rule.enforceRule(obedients);
			}
			
			
		}
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

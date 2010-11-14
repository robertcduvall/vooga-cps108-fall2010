package vooga.engine.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;


import vooga.engine.level.Rule;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;

/**
 * Extension of the Golden T Playfield to allow addition of OverlayTracker to the Playfield.
 * Example:
 * <pre>
 * <code>
 * VoogaPlayfield playfield = new VoogaPlayfield();
 * OverlayTracker track = OverlayCreator.createOverlays(String xmlFileLocation);
 * playfield.addOverlayTracker(track);
 * </pre>
 * </code>
 * @author Bhawana , Cameron , Derek, Jimmy
 *
 */
public class VoogaPlayField extends PlayField {
	
	private Map<String, Rule> ruleBook;
	private Map<Rule, SpriteGroup[]> ruleMap; 
	private String myMusic;
	
	public VoogaPlayField(Background background){
		super(background);
		ruleBook = new HashMap<String, Rule>();
		ruleMap = new HashMap<Rule, SpriteGroup[]>();
	}
	
	@Override
	public void update(long elapsedTime)
	{
		super.update(elapsedTime);
		actOnRules();
		
	}
	
	/**
	 * This method checks if a Rule is applied 
	 */
	private void actOnRules() {
		
		for(String key: ruleBook.keySet())
		{
			Rule rule = ruleBook.get(key);
			SpriteGroup[] obedients = ruleMap.get(rule);
			
			if(rule.checkRule(obedients))
			{
				rule.enforceRule(obedients);
			}
		}
	}

	public VoogaPlayField() {
		super();
	}
	
	/**
	 * This method adds a Rule the palyfield.
	 * @param rulename
	 * @param rule
	 */
	public void addRule(String rulename, Rule rule, SpriteGroup[] obedients)
	{
		ruleBook.put(rulename, rule);
		ruleMap.put(rule, obedients);
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
	

	
}

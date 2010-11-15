package vooga.engine.core;

import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import vooga.engine.level.Rule;
import vooga.engine.level.RuleInterface;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.background.ImageBackground;

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
	
	private Map<String, RuleInterface> myRuleBook;
	private Map<RuleInterface, SpriteGroup[]> ruleMap; 
	private String myMusic;
	private Collection<Background> myBackgrounds;
	
	public VoogaPlayField(Background background){
		super(background);
		myRuleBook = new HashMap<String, RuleInterface>();
		ruleMap = new HashMap<RuleInterface, SpriteGroup[]>();
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
		
		for(String key: myRuleBook.keySet())
		{
			RuleInterface rule = myRuleBook.get(key);
			SpriteGroup[] obedients = ruleMap.get(rule);
			
			if(rule.checkRule(obedients))
			{
				rule.enforce(obedients);
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
	public void addRule(String rulename, RuleInterface rule, SpriteGroup[] obedients)
	{
		myRuleBook.put(rulename, rule);
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
	
	/**
	 * Add an image background to the current Collection. To make a particular background active, use the setBackground method.
	 */
	private void addImageBackground(String key){
		Background currentBackground = new ImageBackground(Resources.getImage(key));
		myBackgrounds.add(currentBackground);
	}
	
	/**
	 * Add a color background to the current Collection.
	 */
	private void addColorBackground(Color color){
		myBackgrounds.add(new ColorBackground(color));
	}

	
}

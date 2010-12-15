package vooga.engine.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import vooga.engine.event.EventPool;
import vooga.engine.event.IEventHandler;

import vooga.engine.control.Control;
import vooga.engine.event.EventPool;
import vooga.engine.level.Rule;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.background.ImageBackground;

/**
 * Extension of the Golden T Playfield to allow addition of OverlayTracker to the Playfield.
 * Example:
 * <pre>
 * <code>
 * Playfield playfield = new Playfield();
 * OverlayTracker track = OverlayCreator.createOverlays(String xmlFileLocation);
 * playfield.addOverlayTracker(track);
 * </pre>
 * </code>
 * @author Bhawana , Cameron , Derek, Jimmy
 *
 */
public class PlayField extends com.golden.gamedev.object.PlayField {


	private Map<String, Rule> myRuleBook;
	private Map<Rule, SpriteGroup[]> myRuleMap; 
	private EventPool myEventPool;	
	private String myMusic;
	private List<Background> myBackgrounds;
	private List<String> myMusics;
	private Map<String, Control> myControls;
	private OverlayTracker myTracker;


	public PlayField(Background background){
		this();
	}

	public PlayField() {
		super();
		myRuleBook = new HashMap<String, Rule>();
		myRuleMap = new HashMap<Rule, SpriteGroup[]>();
		myControls = new HashMap<String, Control>();
		myEventPool = new EventPool();
		myMusics = new ArrayList<String>();
		myBackgrounds = new ArrayList<Background>();
	}


	@Override
	public void update(long elapsedTime)
	{
		super.update(elapsedTime);
		actOnRules();
		myEventPool.checkEvents();

		updateControls();
		myEventPool.checkEvents();

	}

	/**
	 * This method checks if a Rule is applied. 
	 */
	private void actOnRules() {

		for(String key: myRuleBook.keySet())
		{

			Rule rule = myRuleBook.get(key);
			SpriteGroup[] obedients = myRuleMap.get(rule);

			if(rule.isSatisfied(obedients))
			{
				rule.enforce(obedients);
			}
		}
	}



	private void updateControls(){
		for(String key : myControls.keySet()){
			myControls.get(key).update();
		}
	}

	/**
	 * This method adds a Rule the playfield.
	 * @param rulename
	 * @param rule
	 */
	public void addRule(String rulename, Rule rule, SpriteGroup[] obedients)
	{
		myRuleBook.put(rulename, rule);
		myRuleMap.put(rule, obedients);
	}

	public void addEvent(IEventHandler event)
	{
		myEventPool.addEvent(event);

	}
	/**
	 * Adds OverlayTracker to the playfield.
	 * And adds all SpriteGroups from the tracker to the playfield.
	 * @param overlayTracker
	 */
	public void addOverlayTracker(OverlayTracker overlayTracker){
		myTracker = overlayTracker;
		for(String overlayKey: overlayTracker.spriteGroupKeySet()){
			this.addGroup(overlayTracker.getOverlayGroup(overlayKey));
		}
	}

	public OverlayTracker getOverlayTracker(){
		return myTracker;
	}
	
	public Rule getRule(String ruleName){
		return myRuleBook.get(ruleName);		
	}


	/**
	 * Returns the desired music based on the index the user provides.
	 */
	public String getMusic(int index){
		return myMusics.get(index);
	}


	/**
	 * Add an image background to the current Collection. To make a particular background active, use the setBackground method.
	 */
	public void addImageBackground(String key){
		Background currentBackground = new ImageBackground(Resources.getImage(key));
		myBackgrounds.add(currentBackground);
	}

	/**
	 * Add a color background to the current Collection.
	 */
	public void addColorBackground(Color color){
		myBackgrounds.add(new ColorBackground(color));
	}

	/**
	 * Returns the desired background based on the index the user provides.
	 */
	public Background getBackground(int index){
		return myBackgrounds.get(index);
	}

	/**
	 * Makes the background given by the index active in the current playfield.
	 */
	public void setBackground(int index){
		super.setBackground(myBackgrounds.get(index));
	}

	/**
	 * Add a control which will be updated everytime the 
	 * playfield is updated.
	 * @param key key of the Control object
	 * @param control Control to be updated with the PlayField
	 */
	public void addControl(String key, Control control){
		myControls.put(key, control);
	}

	/**
	 * Get control object.
	 * @param key is key of the Control object
	 */
	public Control getControl(String key){
		return myControls.get(key);
	}
	
	/**
	 * Remove control object.
	 * @param key is key of the Control object
	 */
	public void removeControl(String key){
		myControls.remove(key);
	}
	
	/**
	 * Here's to adding a music string
	 * @param musicname
	 */
	public void addMusic(String musicname)
	{
		myMusics.add(musicname);
	}

	public void addEventPool(EventPool eventPool){
		myEventPool = eventPool;
	}


}

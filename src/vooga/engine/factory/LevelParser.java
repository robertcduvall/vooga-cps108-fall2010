package vooga.engine.factory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//import vooga.engine.control.Control;
import vooga.engine.core.Game;
import vooga.engine.core.Sprite;
import vooga.engine.core.VoogaPlayField;
import vooga.engine.overlay.OverlayCreatorTemp;
import vooga.engine.overlay.OverlayTrackerTemp;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.SpriteGroup;

public class LevelParser implements LevelFactory{

	private static final int FIRST_IMAGE = 0;
	private static String gameClassPath;
	private static Game currentGame;
	private static VoogaPlayField myVoogaPlayField;
	private static OverlayTrackerTemp overlayTracker;
	private static Map<String, SpriteGroup> spriteGroupMap;
	
	@Override
	public VoogaPlayField getPlayfield(String filepath, Game currentgame) {
	
		currentGame = currentgame;
		myVoogaPlayField = new VoogaPlayField();
		spriteGroupMap = new HashMap<String, SpriteGroup>();
		createLevelPlayfield(filepath);
		return myVoogaPlayField;
	}

	/**
	 * Creates a VoogaPlayField and stores it in the field myVoogaPlayField.
	 */
	private void createLevelPlayfield(String xmlLevelFile)
	{
		File file = new File(xmlLevelFile);
		DocumentBuilderFactory documentfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = documentfactory.newDocumentBuilder();
			Document xmlDocument = builder.parse(file);
			processLevel(xmlDocument); // This nests into specific cases and processes a level
		} 
		catch (Exception e) {
			throw LevelException.LEVEL_PARSING_EXCEPTION;
		}
	}	

	/**
	 * Returns the value for a given tag from a given Node.
	 * 
	 * @param node the node to gather information from
	 * @param tag the name of the tag which should be gathered
	 * @return a String of the appropriate value
	 */
	private static String getXMLValue(Node node, String tag)
	{
		Element element = (Element) node;
		return element.getElementsByTagName(tag).item(0).getChildNodes().item(0).getNodeValue();
	}

	/**
	 * Processes a Level XML file.
	 */
	private void processLevel(Document xmlDocument)
	{
		Element level = (Element) xmlDocument.getFirstChild(); 
		gameClassPath = level.getAttribute("gameclasspath");
		String xmlOverlayPath = level.getAttribute("xmloverlaypath");
		overlayTracker = OverlayCreatorTemp.createOverlays(xmlOverlayPath);
		
		Node levelObjectsSection = xmlDocument.getElementsByTagName("LevelObjects").item(0);
        NodeList listOfLevelObjects = levelObjectsSection.getChildNodes();
        processLevelObjects(listOfLevelObjects);
		Node levelRulesSection = xmlDocument.getElementsByTagName("LevelRules").item(0);
		NodeList listOfLevelRules = levelRulesSection.getChildNodes();
		processLevelRules(listOfLevelRules);		
	}
	
	/**
	 * Processes a Level's LevelObjects.
	 */
	private void processLevelObjects(NodeList listOfLevelObjects){
		int length = listOfLevelObjects.getLength();
		for (int i = 0; i < length; i++) {
        	Node node = listOfLevelObjects.item(i);
        	if (isElement(node)) {
            	NodeList spriteGroupList = node.getChildNodes();
            	processSpriteGroups(spriteGroupList);
        	}
        }
	}
	
	/**
	 * Processes a Level's LevelRules.
	 */
	private void processLevelRules(NodeList listOfLevelRules){
		int length = listOfLevelRules.getLength();
        for (int i = 0; i < length; i++) {
        	Node node = listOfLevelRules.item(i);
        	if (isElement(node) && node.getNodeName().equals("CollisionGroup")) {
            	NodeList collisionGroupList = node.getChildNodes();
            	processCollisionGroups(collisionGroupList);
            	
        	} else if (isElement(node) && node.getNodeName().equals("Rule")) {
        		NodeList rulesList = node.getChildNodes();
            	processRules(rulesList);
        	}
        }
	}
	
	/**
	 * Processes the SpriteGroups within the LevelObjects section.
	 */
	private void processSpriteGroups(NodeList spriteGroupsList) {

		for(int i = 0; i < spriteGroupsList.getLength(); i++)
		{
			if (isElement(spriteGroupsList.item(i)))
			{
				Element spritegroup = (Element) spriteGroupsList.item(i);
				String groupname = spritegroup.getAttribute("name");

				SpriteGroup newspritegroup = new SpriteGroup(groupname);
				NodeList spriteslist = spritegroup.getChildNodes();

				processSprites(spriteslist, spritegroup);
				myVoogaPlayField.addGroup(newspritegroup);
			}
		}
	}

	/**
	 * Processes the Sprites within a SpriteGroup.
	 */
	private void processSprites(NodeList spritesList, Element spriteGroup) {
		for(int i = 0; i < spritesList.getLength(); i++)
		{
			if(isElement(spritesList.item(i)))
			{
				Element sprite = (Element) spritesList.item(i);
				Sprite newsprite = new Sprite();
				
				//process Images
				NodeList visualsList = sprite.getElementsByTagName("visual");
				processVisuals(visualsList, newsprite);
				
				//process Stats
				NodeList statsList = sprite.getElementsByTagName("stat");
				processStats(statsList, newsprite);
				
				//process Controls
				NodeList controlsList = sprite.getElementsByTagName("control");
				processControls(controlsList, newsprite);
				
				//process locations/velocities
				double x = Double.parseDouble(sprite.getAttribute("x"));
				double y = Double.parseDouble(sprite.getAttribute("y"));
				double vx = Double.parseDouble(sprite.getAttribute("vx"));
				double vy = Double.parseDouble(sprite.getAttribute("vy"));
						
				newsprite.setLocation(x, y);
				newsprite.setHorizontalSpeed(vx);
				newsprite.setVerticalSpeed(vy);
					
			}
		}
	}

	/**
	 * Processes Visuals within a Sprite.
	 */
	private void processVisuals(NodeList visualsList, Sprite newSprite) {
		for(int imageLocation = 0; imageLocation < visualsList.getLength(); imageLocation++)
		{
			if (isElement(visualsList.item(imageLocation)))
			{
				//getAnimation
				Element visualElement = (Element) visualsList.item(imageLocation);
				String visualName = visualElement.getAttribute("name");
				BufferedImage[] visual = Resources.getVisual(visualName);
				
				newSprite.addAnimatedSprite(visualName, visual);
				
				if(imageLocation == FIRST_IMAGE)
				{
					newSprite.setToCurrentSprite(visualName);
				}
			}
		}
	}
	
	/**
	 * Processes Stats within a Sprite. 
	 */
	private void processStats(NodeList statsList, Sprite newSprite) {

		for(int i = 0; i < statsList.getLength(); i++)
		{
			if (isElement(statsList.item(i)))
			{
				Element statElement = (Element) statsList.item(i);
				String statName = statElement.getAttribute("name");
				Stat<?> stat = overlayTracker.getStat(statName);
				newSprite.setStat(statName, stat);
			}
		}
	}
	
	/**
	 * Processes Controls within a Sprite.
	 */
	private void processControls(NodeList controlsList, Sprite newSprite) {
		for(int i = 0; i < controlsList.getLength(); i++)
		{
			if (isElement(controlsList.item(i)))
			{	
			}
		}	
	}
	
	private void processCollisionGroups(NodeList collisionGroupList) {
    	for (int i = 0; i < collisionGroupList.getLength(); i++) {
    		Node node = collisionGroupList.item(i);
    		if (isElement(node)) {
    			Element collisionGroupElement = (Element) node;
    			String subclassName = collisionGroupElement.getAttribute("subclass");
    			NodeList spriteGroupList = collisionGroupElement.getElementsByTagName("SpriteGroup");
    			String spriteGroupName0 = ((Element) spriteGroupList.item(0)).getAttribute("name");
    			String spriteGroupName1 = ((Element) spriteGroupList.item(1)).getAttribute("name");
    			myVoogaPlayField.addCollisionGroup(spriteGroupMap.get(spriteGroupName0), 
    											   spriteGroupMap.get(spriteGroupName1),
    											   ((CollisionManager)createSubclass(subclassName)));
    		}
    	}
	}
	
	private Object createSubclass(String subclassName) {
		Class<?> subclass;
		try {
			subclass = Class.forName(subclassName);
			return subclass.newInstance();
		} catch (Throwable t) {
			System.out.println("Subclass instance creation error");
		}
		return null;
	}

	private void processRules(NodeList spritegrouplist) {
		
	}
	
//  Is this method still needed?
//	@Override
//	public VoogaPlayField getPlayfield(String filePath) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	private static boolean isElement(Node node){
		return (node.getNodeType() == Node.ELEMENT_NODE);
	}
}

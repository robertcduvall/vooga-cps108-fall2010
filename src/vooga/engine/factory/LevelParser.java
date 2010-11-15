package vooga.engine.factory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vooga.engine.core.Game;
import vooga.engine.core.Sprite;
import vooga.engine.core.VoogaPlayField;
import vooga.engine.overlay.OverlayCreatorTemp;
import vooga.engine.overlay.OverlayTrackerTemp;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;
import vooga.engine.util.XMLDocumentCreator;
import vooga.engine.util.XMLFileParser;

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
	public VoogaPlayField getPlayfield(String filepath, Game currentGame) {
		currentGame = currentGame;
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
		try {
			XMLDocumentCreator xmlCreator = new XMLFileParser(xmlLevelFile);
	        Document xmlDocument = xmlCreator.getDocument();
			processLevel(xmlDocument); // This nests into specific cases and processes a level
		} 
		catch (Exception e) {
			throw LevelException.LEVEL_PARSING_EXCEPTION;
		}
	}	

	/**
	 * Processes a Level XML file.
	 */
	private void processLevel(Document xmlDocument)
	{
		Element level = (Element) xmlDocument.getFirstChild(); 
		//gameClassPath = level.getAttribute("gameclasspath");
		String xmlOverlayPath = level.getAttribute("xmloverlaypath");
		overlayTracker = OverlayCreatorTemp.createOverlays(xmlOverlayPath);
		
		Node spriteGroupsSection = xmlDocument.getElementsByTagName("SpriteGroups").item(0);
        NodeList listOfSpriteGroups = spriteGroupsSection.getChildNodes();
        processSpriteGroups(listOfSpriteGroups);
//		Node levelRulesSection = xmlDocument.getElementsByTagName("LevelRules").item(0);
//		NodeList listOfLevelRules = levelRulesSection.getChildNodes();
//		processLevelRules(listOfLevelRules);
        Node collisionGroupsSection = xmlDocument.getElementsByTagName("CollisionGroups").item(0);
		NodeList listOfCollisionGroups = collisionGroupsSection.getChildNodes();
		processCollisionGroups(listOfCollisionGroups);
	}
	
//	/**
//	 * Processes a Level's LevelRules.
//	 */
//	private void processLevelRules(NodeList listOfLevelRules){
//		int length = listOfLevelRules.getLength();
//        for (int i = 0; i < length; i++) {
//        	Node node = listOfLevelRules.item(i);
//        	if (isElement(node) && node.getNodeName().equals("CollisionGroup")) {
//            	NodeList collisionGroupList = node.getChildNodes();
//            	processCollisionGroups(collisionGroupList);
//            	
//        	} else if (isElement(node) && node.getNodeName().equals("Rule")) {
//        		NodeList rulesList = node.getChildNodes();
//            	processRules(rulesList);
//        	}
//        }
//	}
	
	/**
	 * Processes the SpriteGroups within the LevelObjects section.
	 */
	private void processSpriteGroups(NodeList spriteGroupsList) {

		for(int i = 0; i < spriteGroupsList.getLength(); i++)
		{
			if (isElement(spriteGroupsList.item(i)))
			{
				Element spriteGroup = (Element) spriteGroupsList.item(i);
				String groupName = spriteGroup.getAttribute("name");

				SpriteGroup newSpriteGroup = new SpriteGroup(groupName);
				NodeList spritesList = spriteGroup.getChildNodes();

				processSprites(spritesList, newSpriteGroup);
				myVoogaPlayField.addGroup(newSpriteGroup);
			}
		}
	}

	/**
	 * Processes the Sprites within a SpriteGroup.
	 */
	private void processSprites(NodeList spritesList, SpriteGroup spriteGroup) {
		for(int i = 0; i < spritesList.getLength(); i++)
		{
			if(isElement(spritesList.item(i)))
			{
				Element sprite = (Element) spritesList.item(i);
				Sprite newsprite = new Sprite();
				
				NodeList visualsList = sprite.getElementsByTagName("Visual");
				processVisuals(visualsList, newsprite);
				
				NodeList statsList = sprite.getElementsByTagName("Stat");
				processStats(statsList, newsprite);
				
				NodeList controlsList = sprite.getElementsByTagName("Control");
				processControls(controlsList, newsprite);
				
				double x = Double.parseDouble(sprite.getAttribute("x"));
				double y = Double.parseDouble(sprite.getAttribute("y"));
				double vx = Double.parseDouble(sprite.getAttribute("vx"));
				double vy = Double.parseDouble(sprite.getAttribute("vy"));
						
				newsprite.setLocation(x, y);
				newsprite.setHorizontalSpeed(vx);
				newsprite.setVerticalSpeed(vy);	
				spriteGroup.add(newsprite);
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
	
	/**
	 * Processes the CollisionGroups.
	 */
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
    											   ((CollisionManager)createCollisionSubclass(subclassName, currentGame)));
    		}
    	}
	}
	
	private Object createCollisionSubclass(String subclassName, Game param) {
		Class<?> subclass;
		try {
			subclass = Class.forName(subclassName);
			Constructor<?> ct = subclass.getConstructor(Game.class);
			return ct.newInstance(param);
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

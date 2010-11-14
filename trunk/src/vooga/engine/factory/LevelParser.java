package vooga.engine.factory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vooga.engine.core.Game;
import vooga.engine.core.VoogaPlayField;

import vooga.engine.core.Sprite;
import vooga.engine.overlay.OverlayCreatorTemp;
import vooga.engine.overlay.OverlayTrackerTemp;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;

public class LevelParser implements LevelFactory{

	private static final int FIRST_IMAGE = 0;
	private static String gameClassPath;
	private static Game currentGame;
	private static OverlayTrackerTemp overlayTracker;
	private static Map<String, SpriteGroup> spriteGroupMap;
	private static VoogaPlayField voogaPlayField;
	

	@Override
	public VoogaPlayField getPlayfield(String filepath, Game currentgame) {
	
		currentGame = currentgame;
		spriteGroupMap = new HashMap<String, SpriteGroup>();
		voogaPlayField = new VoogaPlayField();
		createLevelPlayfield(filepath);
		return voogaPlayField;
	}

	private void createLevelPlayfield(String xmlLevelFile)
	{
		File file = new File(xmlLevelFile);
		DocumentBuilderFactory documentfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = documentfactory.newDocumentBuilder();
			Document xmlDocument = builder.parse(file);
			processLevel(xmlDocument); // This should nest into specific cases and process a level
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
	 * 
	 * @param listOfLevelRules
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
	 * 
	 * @param listOfLevelObjects
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
				voogaPlayField.addGroup(newspritegroup);
			}
		}
	}

	
	private void processSprites(NodeList spriteslist, Element spritegroup) {
		
		
		for(int i = 0; i < spriteslist.getLength(); i++)
		{
			if(isElement(spriteslist.item(i)))
			{
				Element sprite = (Element) spriteslist.item(i);
				Sprite newsprite = new Sprite();
				
				//process Images
				NodeList visualslist = sprite.getElementsByTagName("visual");
				processVisuals(visualslist, newsprite);
				
				
				//process Stats
				NodeList statslist = sprite.getElementsByTagName("stat");
				processStats(statslist, newsprite);
				
				
				//process Controls
				NodeList controlslist = sprite.getElementsByTagName("control");
				processControls(controlslist, newsprite);
				
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

	private void processControls(NodeList controlslist, Sprite newsprite) {
		
		for(int i = 0; i < controlslist.getLength(); i++)
		{
			if (isElement(controlslist.item(i)))
			{
				
				
				
				
				
				
			}
		}
			
	}

	private void processStats(NodeList statslist, Sprite newsprite) {

		for(int i = 0; i < statslist.getLength(); i++)
		{
			if (isElement(statslist.item(i)))
			{
				Element statelement = (Element) statslist.item(i);
				String statname = statelement.getAttribute("name");
				Stat<?> stat = overlayTracker.getStat(statname);
				newsprite.setStat(statname, stat);
			}
		}
	}
	

	private void processVisuals(NodeList visualslist, Sprite newsprite) {
		
		for(int imagelocation = 0; imagelocation < visualslist.getLength(); imagelocation++)
		{
			if (isElement(visualslist.item(imagelocation)))
			{
				//getAnimation
				Element visual = (Element) visualslist.item(imagelocation);
				String imagename = visual.getAttribute("name");
				BufferedImage[] images = Resources.getAnimation(imagename);
				
				newsprite.addAnimatedsprite(imagename, images);
				
				if(imagelocation == FIRST_IMAGE)
				{
					newsprite.setToCurrentSprite(imagename);
				}
			}
		}
	}

	private static boolean isElement(Node node){
		return (node.getNodeType() == Node.ELEMENT_NODE);
	}

	private void processCollisionGroups(NodeList collisiongrouplist) {
		
		
		
		
		
		
		
		
		
		
	}

	private void processRules(NodeList spritegrouplist) {
		// TODO Auto-generated method stub

	}

	/**
	 * Utility method for loadResourcesXMLFile - loads a Node's Elements and returns them in a List.
	 * @param doc
	 * @param sectionTag
	 * @return
	 */
	private static List<Element> loadSectionElements(Document doc, String sectionTag) {
    	Node section = doc.getElementsByTagName(sectionTag).item(0);
    	NodeList nodeList = section.getChildNodes();
    	int length = nodeList.getLength();
    	ArrayList<Element> elementList = new ArrayList<Element>();
    	for (int i = 0; i < length; i++) {
    		Node node = nodeList.item(i);
    		if (node.getNodeType() == Node.ELEMENT_NODE) {
    			Element element = (Element) node;
    			elementList.add(element);
    		}
    	}
    	return elementList;
    }

	@Override
	public VoogaPlayField getPlayfield(String filePath) {
		// TODO Auto-generated method stub
		return null;
	}
}

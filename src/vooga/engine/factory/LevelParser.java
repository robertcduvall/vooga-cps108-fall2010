package vooga.engine.factory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vooga.engine.core.Game;
import vooga.engine.core.BetterSprite;
import vooga.engine.core.PlayField;
import vooga.engine.event.IEventHandler;
import vooga.engine.level.Rule;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;
import vooga.engine.util.XMLDocumentCreator;
import vooga.engine.util.XMLFileParser;

import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.collision.CollisionGroup;

/**
 * LevelParser
 * 
 * @author Derek, Cameron, Jimmy, John
 *
 */

public class LevelParser implements LevelFactory{

	private static final int FIRST_IMAGE = 0;
	private String gameClassPath;
	private Game currentGame;
	private PlayField voogaPlayField;
	private OverlayTracker overlayTracker;
	private Map<String, SpriteGroup> spriteGroups;
	
	@Override
	public PlayField getPlayfield(String filepath, Game game) {
		this.currentGame = game;
		voogaPlayField = new PlayField();
		createLevelPlayfield(filepath);
		return voogaPlayField;
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
			e.printStackTrace();
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
		overlayTracker = OverlayCreator.createOverlays(xmlOverlayPath); //Getting a "prolog" error from this line.

		//		Node levelRulesSection = xmlDocument.getElementsByTagName("LevelRules").item(0);
		//		NodeList listOfLevelRules = levelRulesSection.getChildNodes();
		//		processLevelRules(listOfLevelRules);

		Node spriteGroupsSection = xmlDocument.getElementsByTagName("SpriteGroups").item(0);
		NodeList listOfSpriteGroups = spriteGroupsSection.getChildNodes();
		processSpriteGroups(listOfSpriteGroups);

		//process Events
		Node eventGroupsSection = xmlDocument.getElementsByTagName("Events").item(0);
		if(eventGroupsSection!=null) {
			NodeList listOfEventGroups = eventGroupsSection.getChildNodes();
			processEventGroups(listOfEventGroups);
		}

		//process Rules
		Node ruleGroupsSection = xmlDocument.getElementsByTagName("Rules").item(0);
		if(ruleGroupsSection!=null) {
			NodeList listOfRuleGroups = ruleGroupsSection.getChildNodes();
			processRuleGroups(listOfRuleGroups);
		}

		Node collisionGroupsSection = xmlDocument.getElementsByTagName("CollisionGroups").item(0);
		if(collisionGroupsSection!=null) {
			NodeList listOfCollisionGroups = collisionGroupsSection.getChildNodes();
			processCollisionGroups(listOfCollisionGroups);
		}

		Node backgroundGroupsSection = xmlDocument.getElementsByTagName("Background").item(0);
		if(backgroundGroupsSection!=null) {
			NodeList listOfBackgrounds = backgroundGroupsSection.getChildNodes();
			processBackground(listOfBackgrounds);
		}

		Node musicGroupSection = xmlDocument.getElementsByTagName("Music").item(0);
		if(musicGroupSection!=null) {
			NodeList listOfMusic = musicGroupSection.getChildNodes();
			processMusic(listOfMusic);
		}

		Node mapSection = xmlDocument.getElementsByTagName("Map").item(0);
		if(mapSection.hasChildNodes())
		{
			NodeList listOfMaps = mapSection.getChildNodes();
			processMap(listOfMaps);
		}
	}

	private void processRuleGroups(NodeList ruleGroupsList) {

		for(int i = 0; i < ruleGroupsList.getLength(); i++)
		{
			if (isElement(ruleGroupsList.item(i)))
			{
				Element rule = (Element) ruleGroupsList.item(i);
				String rulename = rule.getAttribute("name");
				String implementor = rule.getAttribute("class");
				
				try {
					Rule ruleinstance = (Rule) Class.forName(implementor).newInstance();
					NodeList spriteGroupsList = rule.getChildNodes();
					SpriteGroup[] ruleObedients = getSpriteGroups(spriteGroupsList);
					
					voogaPlayField.addRule(rulename, ruleinstance, ruleObedients);
				}
				catch(Exception e)
				{
					throw LevelException.RULE_NOT_FOUND_EXCEPTION;
				}
			}
		}
	}

	private SpriteGroup[] getSpriteGroups(NodeList spriteGroupsList) {
		
		SpriteGroup[] list = new SpriteGroup[spriteGroupsList.getLength()];
		
		for(int i = 0; i < spriteGroupsList.getLength(); i++)
		{
			Element spritegroup = (Element) spriteGroupsList.item(i);
			String groupname = spritegroup.getAttribute("name");
			SpriteGroup currentGroup = voogaPlayField.getGroup(groupname);
			list[i] = currentGroup;
		}
		
		return list;
	}

	private void processEventGroups(NodeList eventGroupsList) {
		
		for(int i = 0; i < eventGroupsList.getLength(); i++)
		{
			if (isElement(eventGroupsList.item(i)))
			{
				Element event = (Element) eventGroupsList.item(i);
				String implementor = event.getAttribute("class");
				
				try {
					IEventHandler eventinstance = (IEventHandler) 
													Class.forName(implementor).newInstance();
					voogaPlayField.addEvent(eventinstance);
				}
				catch(Exception e)
				{
					throw LevelException.EVENT_NOT_FOUND_EXCEPTION;
				}
			}
		}
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

				processSprite(spritesList, newSpriteGroup);
				voogaPlayField.addGroup(newSpriteGroup);
			}
		}
	}

	/**
	 * Processes the Sprites within a SpriteGroup.
	 */
	public void processSprite(NodeList spritesList, SpriteGroup group) {
		BetterSprite newSprite = null;
		for(int i = 0; i < spritesList.getLength(); i++)
		{
			if (isElement(spritesList.item(i))) {
				Element currentElement = (Element) spritesList.item(i);
				if (currentElement.getTagName().equals("Sprite")) {
					String className = currentElement.getAttribute("class");
					try {
						Class userSprite = Class.forName(className);
						Constructor classConstructor = userSprite.getConstructor();
						if (!Boolean.parseBoolean(currentElement.getAttribute("random"))) {
							double x = Double.parseDouble(currentElement.getAttribute("x"));
							double y = Double.parseDouble(currentElement.getAttribute("y"));
							double vx = Double.parseDouble(currentElement.getAttribute("vx"));
							double vy = Double.parseDouble(currentElement.getAttribute("vy"));
			
							int quantity = Integer.parseInt(currentElement.getAttribute("quantity"));
							for(int j = 0; j < quantity; j++) {
								BetterSprite spriteToAdd = (BetterSprite)classConstructor.newInstance();
								spriteToAdd.setLocation(x, y);
								spriteToAdd.setHorizontalSpeed(vx);
								spriteToAdd.setVerticalSpeed(vy);	
								group.add(spriteToAdd);
							}
						} else if (Boolean.parseBoolean(currentElement.getAttribute("random"))) {
							double xMin = Double.parseDouble(currentElement.getAttribute("xMin"));
							double yMin = Double.parseDouble(currentElement.getAttribute("yMin"));
							double vxMin = Double.parseDouble(currentElement.getAttribute("vxMin"));
							double vyMin = Double.parseDouble(currentElement.getAttribute("vyMin"));
							double xMax = Double.parseDouble(currentElement.getAttribute("xMax"));
							double yMax = Double.parseDouble(currentElement.getAttribute("yMax"));
							double vxMax = Double.parseDouble(currentElement.getAttribute("vxMax"));
							double vyMax = Double.parseDouble(currentElement.getAttribute("vyMax"));
							
							int quantity = Integer.parseInt(currentElement.getAttribute("quantity"));
							for(int j = 0; j < quantity; j++) {
								BetterSprite spriteToAdd = (BetterSprite)classConstructor.newInstance();
								spriteToAdd.setLocation(Math.random()*(xMax-xMin) + xMin,
													  Math.random()*(yMax-yMin) + yMin);
								spriteToAdd.setHorizontalSpeed(Math.random()*(vxMax-vxMin) + vxMin);
								spriteToAdd.setVerticalSpeed(Math.random()*(vyMax-vyMin) + vyMin);	
								group.add(spriteToAdd);
							}
						}
					} catch (Throwable e){
						e.printStackTrace();
					}
				}
			}
		}
	}

	private void processMap(NodeList listOfMaps) {
		MapReader reader;
		for(int i = 0; i < listOfMaps.getLength(); i++)
		{
			Node currentNode = listOfMaps.item(i);

			if(isElement(currentNode))
			{
				String path = Resources.getString(((Element) currentNode).getAttribute("name"));
				reader = new MapReader(path, voogaPlayField);
				NodeList listOfAssociations = currentNode.getChildNodes();
				for(int j = 0; j < listOfAssociations.getLength(); j++)
				{
					Node association = listOfAssociations.item(j);
					if(isElement(association)) {
						Element associationElement = (Element) listOfAssociations.item(j);
	
						String key = associationElement.getAttribute("char");
						String value = associationElement.getAttribute("object");
						String image = associationElement.getAttribute("image");
						reader.addAssociation(key, value, image);
					}
				}
				voogaPlayField = reader.processMap();
			}
		}
	}

	//process Stats
	//process Control


	/**
	 * Processes Visuals within a Sprite.
	 */
	public void processVisual(NodeList visualsList, BetterSprite newSprite) {
		for(int imageLocation = 0; imageLocation < visualsList.getLength(); imageLocation++)
		{
			if (isElement(visualsList.item(imageLocation)))
			{
				Element visualElement = (Element) visualsList.item(imageLocation);
				String visualName = visualElement.getAttribute("name");
				BufferedImage[] visual = Resources.getVisual(visualName);

				newSprite.addAnimatedImages(visualName, visual);

				if(imageLocation == FIRST_IMAGE)
				{
					newSprite.setAsRenderedSprite(visualName);
				}
			}
		}
	}

	/**
	 * Processes the background
	 */
	private void processBackground(NodeList backgrounds) {
		for (int i = 0; i < backgrounds.getLength(); i++) {
			if (isElement(backgrounds.item(i))) {
				Element bgElement = (Element) backgrounds.item(i);
				voogaPlayField.addImageBackground(bgElement
						.getAttribute("path"));
			}
		}
	}

	/**
	 * Processes the background
	 */
	private void processMusic(NodeList musics) {
		for (int i = 0; i < musics.getLength(); i++) {
			if (isElement(musics.item(i))) {
				Element musicElements = (Element) musics.item(i);
				// voogaPlayField.addMusic(musicElements.getAttribute("name"));
				if (isElement(musics.item(i))) {
					Element musicElement = (Element) musics.item(i);
					voogaPlayField.addMusic(musicElements.getAttribute("name"));
				}
			}
		}
	}

	/**
	 * Processes Stats within a Sprite. 
	 */
	private void processStats(NodeList statsList, BetterSprite newSprite) {

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
	private void processControls(NodeList controlsList, BetterSprite newSprite) {
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
				voogaPlayField.addCollisionGroup(voogaPlayField.getGroup(spriteGroupName0), 
						voogaPlayField.getGroup(spriteGroupName1),
						((CollisionManager)createCollisionSubclass(subclassName)));

				voogaPlayField.addCollisionGroup(voogaPlayField.getGroup(spriteGroupName0), 
						voogaPlayField.getGroup(spriteGroupName1),
						((CollisionManager)createCollisionSubclass(subclassName)));

			}
		}
	}

	private Object createCollisionSubclass(String subclassName) {
		Class<?> subclass;
		try {
			subclass = Class.forName(subclassName);
			Constructor<?> ct = subclass.getConstructor();
			return ct.newInstance();
		} catch (Throwable t) {
			System.out.println("Subclass instance creation error");
		}
		return null;
	}

	
	public static boolean isElement(Node node){
		return (node.getNodeType() == Node.ELEMENT_NODE);
	}


	public Game getGame(){
		return currentGame;
	}
	/*
	private BetterSprite processX(String classname, NamedNodeMap attributes)
	{
		try
		{
			Class<?> op = this.getClass();
			String methodname = "process" + classname;
			Method m = op.getMethod(methodname, NamedNodeMap.class);
			Object result = m.invoke(attributes);
			return (BetterSprite) result;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
			// compiler is :)
			return null;
		}
	}*/
}

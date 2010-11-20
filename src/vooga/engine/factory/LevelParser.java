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
		for(int i = 0; i < spritesList.getLength(); i++)
		{
			if (isElement(spritesList.item(i))) {
				Element currentNode = (Element) spritesList.item(i);
				if (currentNode.getNodeName().equals("RegularSprite")) {
					processRegularSprite((Element)currentNode, group);
				} else if (currentNode.getNodeName().equals("SpawnedSprite")) {
					processSpawnedSprite((Element)currentNode, group);
				}
				else
				{
					/*BetterSprite result = processX(currentNode.getTagName(), currentNode.getAttributes());
					NodeList visualsList = currentNode.getElementsByTagName("Visual");
					group.add(result);
					processVisual(visualsList, result);*/
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
				String path = ((Element) currentNode).getAttribute("name");
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
	 * Adds a regular, location-specified Sprite to the given SpriteGroup.
	 */
	public void processRegularSprite(Element spriteElement, SpriteGroup group) {
		BetterSprite newSprite = null;
		String spriteName = spriteElement.getAttribute("name");
		try {
			Class userSprite = Class.forName(spriteName);
			Class parameterTypes[] = new Class[1];
			parameterTypes[0] = String.class;
			Constructor classConstructor = userSprite.getConstructor(parameterTypes);
			Object arglist[] = new Object[1];
			arglist[0] = new String(spriteName);
			Object returnObject = classConstructor.newInstance(spriteName);
			newSprite = (BetterSprite)returnObject;
		} catch (Throwable e){
			e.printStackTrace();
		}

//		NodeList visualsList = spriteElement.getElementsByTagName("Visual");
//		processVisual(visualsList, newSprite);
//		NodeList statsList = spriteElement.getElementsByTagName("Stat");
//		processStats(statsList, newSprite);
//		NodeList controlsList = spriteElement.getElementsByTagName("Control");
//		processControls(controlsList, newSprite);

		double x = Double.parseDouble(spriteElement.getAttribute("x"));
		double y = Double.parseDouble(spriteElement.getAttribute("y"));
		double vx = Double.parseDouble(spriteElement.getAttribute("vx"));
		double vy = Double.parseDouble(spriteElement.getAttribute("vy"));

		int quantity = Integer.parseInt(spriteElement.getAttribute("quantity"));
		for(int j = 0; j < quantity; j++) {
			newSprite.setLocation(x, y);
			newSprite.setHorizontalSpeed(vx);
			newSprite.setVerticalSpeed(vy);	
			group.add(newSprite);
		}
	}

	/**
	 * Adds or "spawns" [quantity] Sprites with random locations and
	 * velocities to the given SpriteGroup.
	 */
	private void processSpawnedSprite(Element spriteElement, SpriteGroup group) {		
		NodeList visualsList = spriteElement.getElementsByTagName("Visual");
		NodeList statsList = spriteElement.getElementsByTagName("Stat");
		NodeList controlsList = spriteElement.getElementsByTagName("Control");

		double xRange = Double.parseDouble(spriteElement.getAttribute("xRange"));
		double yRange = Double.parseDouble(spriteElement.getAttribute("yRange"));
		double vxRange = Double.parseDouble(spriteElement.getAttribute("vxRange"));
		double vyRange = Double.parseDouble(spriteElement.getAttribute("vyRange"));

		int quantity = Integer.parseInt(spriteElement.getAttribute("quantity"));
		for(int j = 0; j < quantity; j++) {
			BetterSprite newSprite = new BetterSprite();
			newSprite.setLocation(Math.random()*xRange, Math.random()*yRange);
			newSprite.setHorizontalSpeed(Math.random()*vxRange);
			newSprite.setVerticalSpeed(Math.random()*vyRange);	
			processVisual(visualsList, newSprite);
			processStats(statsList, newSprite);
			processControls(controlsList, newSprite);
			group.add(newSprite);
		}
	}
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
						.getAttribute("name"));
				if (isElement(backgrounds.item(i))) {
					Element bgElementNode = (Element) backgrounds.item(i);
					voogaPlayField.addImageBackground(bgElementNode
							.getAttribute("name"));
				}
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

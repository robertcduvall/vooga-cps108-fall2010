package vooga.engine.factory;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vooga.engine.control.Control;
import vooga.engine.core.BetterSprite;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.level.Rule;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;
import vooga.engine.util.XMLDocumentCreator;
import vooga.engine.util.XMLFileParser;

import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.SpriteGroup;

/**
 * The LevelParser class can be used to process a LevelXML file and turn it into a PlayField.
 * The LevelParser handles SpriteGroups, CollisionGroups, Backgrounds,
 * Music, Maps, and Events.
 * 
 * @author Derek, Cameron, Jimmy, John
 *
 */

public class LevelParser implements LevelFactory{

	public static final int FIRST_IMAGE = 0;
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
		OverlayCreator.setGame(currentGame);
		if(!xmlOverlayPath.equals("")){
			overlayTracker = OverlayCreator.createOverlays(xmlOverlayPath); //Getting a "prolog" error from this line.
		}

		Node spriteGroupsSection = xmlDocument.getElementsByTagName("SpriteGroups").item(0);
		if(spriteGroupsSection !=null){
			NodeList listOfSpriteGroups = spriteGroupsSection.getChildNodes();
			processSpriteGroups(listOfSpriteGroups);
		}

//		Node eventGroupsSection = xmlDocument.getElementsByTagName("Events").item(0);
//		if(eventGroupsSection!=null) {
//			NodeList listOfEventGroups = eventGroupsSection.getChildNodes();
//			processEvents(listOfEventGroups);
//		}

		Node ruleGroupsSection = xmlDocument.getElementsByTagName("Rules").item(0);
		if(ruleGroupsSection!=null) {
			NodeList listOfRuleGroups = ruleGroupsSection.getChildNodes();
			processRules(listOfRuleGroups);
		}

		Node backgroundGroupsSection = xmlDocument.getElementsByTagName("Backgrounds").item(0);
		if(backgroundGroupsSection!=null) {
			NodeList listOfBackgrounds = backgroundGroupsSection.getChildNodes();
			processBackgrounds(listOfBackgrounds);
		}

		Node musicGroupSection = xmlDocument.getElementsByTagName("Music").item(0);
		if(musicGroupSection!=null) {
			NodeList listOfMusic = musicGroupSection.getChildNodes();
			processMusic(listOfMusic);
		}

		Node mapSection = xmlDocument.getElementsByTagName("Map").item(0);
		if(mapSection != null && mapSection.hasChildNodes())
		{
			NodeList listOfMaps = mapSection.getChildNodes();
			processMap(listOfMaps);
		}
		
		Node collisionGroupsSection = xmlDocument.getElementsByTagName("CollisionGroups").item(0);
		if(collisionGroupsSection!=null) {
			NodeList listOfCollisionGroups = collisionGroupsSection.getChildNodes();
			processCollisionGroups(listOfCollisionGroups);
		}
		
		if(overlayTracker!=null){
			voogaPlayField.addOverlayTracker(overlayTracker);
		}
	}

	private void processRules(NodeList rulesList) {

		for(int i = 0; i < rulesList.getLength(); i++)
		{
			if (isElement(rulesList.item(i)))
			{
				Element rule = (Element) rulesList.item(i);
				String rulename = rule.getAttribute("name");
				String implementor = rule.getAttribute("class");
				
				try {
					Class<?> ruleClass = Class.forName(implementor);
					Constructor<?> ct = ruleClass.getConstructor();
					Rule ruleinstance = (Rule) ct.newInstance();
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
			if (isElement(spriteGroupsList.item(i))) {
				Element spritegroup = (Element) spriteGroupsList.item(i);
				String groupname = spritegroup.getAttribute("name");
				SpriteGroup currentGroup = voogaPlayField.getGroup(groupname);
				list[i] = currentGroup;
			}
		}
		ArrayList<SpriteGroup> newList = new ArrayList<SpriteGroup>();
		for (SpriteGroup sg: list) {
			if (sg!=null)
				newList.add(sg);
		}
		SpriteGroup[] newArray = new SpriteGroup[newList.size()];
		newList.toArray(newArray);
		return newArray;
	}

//	private void processEvents(NodeList eventList) {
//		
//		for(int i = 0; i < eventList.getLength(); i++)
//		{
//			if (isElement(eventList.item(i)))
//			{
//				Element event = (Element) eventList.item(i);
//				String implementor = event.getAttribute("class");
//				
//				try {
//					IEventHandler eventinstance = (IEventHandler) 
//													Class.forName(implementor).newInstance();
//					voogaPlayField.addEvent(eventinstance);
//				}
//				catch(Exception e)
//				{
//					throw LevelException.EVENT_NOT_FOUND_EXCEPTION;
//				}
//			}
//		}
//	}

	/**
	 * Processes the SpriteGroups within the LevelObjects section.
	 */
	private void processSpriteGroups(NodeList spriteGroupsList) {

		if(spriteGroupsList == null){
			return;
		}
		for(int i = 0; i < spriteGroupsList.getLength(); i++)
		{
			if (isElement(spriteGroupsList.item(i)))
			{
				Element spriteGroup = (Element) spriteGroupsList.item(i);
				String groupName = spriteGroup.getAttribute("name");

				SpriteGroup newSpriteGroup = new SpriteGroup(groupName);
				NodeList spritesList = spriteGroup.getElementsByTagName("Sprite");
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
				Element currentElement = (Element) spritesList.item(i);
				if (currentElement.getTagName().equals("Sprite")) {
					String className = currentElement.getAttribute("class");
					try {
						Class<?> userSprite = Class.forName(className);
						Constructor<?> classConstructor = userSprite.getConstructor();
						if (!Boolean.parseBoolean(currentElement.getAttribute("random"))) {
							double x = Double.parseDouble(currentElement.getAttribute("x"));
							double y = Double.parseDouble(currentElement.getAttribute("y"));
							double vx = Double.parseDouble(currentElement.getAttribute("vx"));
							double vy = Double.parseDouble(currentElement.getAttribute("vy"));
			
							int quantity = Integer.parseInt(currentElement.getAttribute("quantity"));
							for(int j = 0; j < quantity; j++) {
								BetterSprite spriteToAdd = (BetterSprite)classConstructor.newInstance();
								
								NodeList listOfVisuals = currentElement.getElementsByTagName("Visual");
								processVisual(listOfVisuals, spriteToAdd);
								
								NodeList listOfControls = currentElement.getElementsByTagName("Control");
								processControl(listOfControls, spriteToAdd);
								
								NodeList listOfStats = currentElement.getElementsByTagName("Stat");
								processStat(listOfStats, spriteToAdd);
								
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
								
								NodeList listOfVisuals = currentElement.getElementsByTagName("Visual");
								processVisual(listOfVisuals, spriteToAdd);
								
								NodeList listOfControls = currentElement.getElementsByTagName("Control");
								processControl(listOfControls, spriteToAdd);
								
								NodeList listOfStats = currentElement.getElementsByTagName("Stat");
								processStat(listOfStats, spriteToAdd);
								
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

	private void processStat(NodeList listOfStats, BetterSprite spriteToAdd) {
		
		for(int statCount = 0; statCount < listOfStats.getLength(); statCount++)
		{
			if (isElement(listOfStats.item(statCount)))
			{
				Element statElement = (Element) listOfStats.item(statCount);
				String statName = statElement.getAttribute("name");
				Stat<?> stat = overlayTracker.getStat(statName);
				
				spriteToAdd.setStat(statName, stat);
			}
		}	
	}

	private void processControl(NodeList listOfControls, BetterSprite spriteToAdd) {
	
		for(int controlCount = 0; controlCount < listOfControls.getLength(); controlCount++)
		{
			if (isElement(listOfControls.item(controlCount)))
			{
				Element controlElement = (Element) listOfControls.item(controlCount);
				
				String controlName = controlElement.getAttribute("name");
				String controlsubclassname=controlElement.getAttribute("controlsubclass");
				String objectclassname = controlElement.getAttribute("class");
				String objectmethodname = controlElement.getAttribute("methodname");
				int controlkey = Integer.parseInt(controlElement.getAttribute("controlkey"));
				NodeList methodArguments = controlElement.getElementsByTagName("MethodArgument");
				Class<?>[] paramTypes = getParamTypes(methodArguments);
				try {
					Class controlClass = Class.forName(controlsubclassname);
					Constructor<Control> ct = controlClass.getConstructor(Object.class, Game.class);
					Control newControl = ct.newInstance(spriteToAdd, currentGame);
					newControl.addInput(controlkey, objectmethodname, objectclassname, paramTypes);
				} catch (Exception e) {
					throw LevelException.CONTROL_PARSING_EXCEPTION;
				}	
			}
		}
		
	}
	
	private Class[] getParamTypes(NodeList list) {
		Class[] returnArray = new Class[list.getLength()];
		for (int i = 0; i < list.getLength(); i++) {
			try {
				returnArray[i] = Class.forName(((Element)list.item(i)).getAttribute("class"));
			} catch (Throwable e) {
				throw LevelException.CONTROL_PARSING_EXCEPTION_CLASS_NOT_FOUND;
			}
		}
		return returnArray;
	}

	private void processMap(NodeList listOfMaps) {
		MapReader reader;
		for(int i = 0; i < listOfMaps.getLength(); i++)
		{
			Node currentNode = listOfMaps.item(i);

			if(isElement(currentNode))
			{
				String pathName = ((Element) currentNode).getAttribute("name");
				String path = pathName.equals("") ? ((Element) currentNode)
						.getAttribute("path") : Resources.getString(pathName);
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
	private void processBackgrounds(NodeList backgrounds) {
		for (int i = 0; i < backgrounds.getLength(); i++) {
			if (isElement(backgrounds.item(i))) {
				Element bgElement = (Element) backgrounds.item(i);
				String bgName = bgElement.getAttribute("name");
				if (!bgName.equals(""))
					voogaPlayField.addImageBackground(bgElement
							.getAttribute("name"));
				else
					voogaPlayField.addColorBackground(new Color(Integer
							.parseInt(bgElement.getAttribute("color"), 16)));
			}
		}
		voogaPlayField.setBackground(0);
	}

	/**
	 * Processes the music
	 */
	private void processMusic(NodeList musics) {
		for (int i = 0; i < musics.getLength(); i++) {
			if (isElement(musics.item(i))) {
				Element musicElement = (Element) musics.item(i);
				voogaPlayField.addMusic(musicElement.getAttribute("name"));
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
			}
		}
	}

	/**
	 * Utility method that is used by processCollisionGroups().
	 */
	private Object createCollisionSubclass(String subclassName) {
		Class<?> subclass;
		try {
			subclass = Class.forName(subclassName);
			Constructor<?> ct = subclass.getConstructor(Game.class);
			return ct.newInstance(this.currentGame);
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
	
	public OverlayTracker getOverlayTracker(){
		return overlayTracker;
	}
}
